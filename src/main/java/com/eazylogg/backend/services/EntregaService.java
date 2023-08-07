package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.models.enums.EntregaStatus;
import com.eazylogg.backend.models.enums.PagamentoStatus;
import com.eazylogg.backend.repositories.EntregaRepository;
import com.eazylogg.backend.repositories.PagamentoRepository;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private EmailService emailService;

    public Entrega getEntrega(Long id){
        return entregaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Entrega não encontrado!"));
    }

    public List<Entrega> getListaEntregas(){
        return entregaRepository.findAll();
    }

    public Entrega salvarEntrega(Entrega entrega){

        LocalDate dataAtual = LocalDate.now();

        entrega.setDataEntrega(dataAtual);
        entrega.setStatus(EntregaStatus.SOLICITADO);
        entrega.getPagamento().setStatus(PagamentoStatus.PENDENTE);
        entrega.getPagamento().setEntrega(entrega);

        pagamentoRepository.save(entrega.getPagamento());
        //emailService.sendConfirmationEmail(entrega);
        return entregaRepository.save(entrega);
    }

    public void atualizarEntrega(Long id, Entrega entrega){
        entregaRepository.findById(id).map(obj -> {
            entrega.setId(obj.getId());
            return entregaRepository.save(entrega);
        }).orElseThrow(() -> new ObjectNotFoundException("Entrega não encontrado!"));
    }

    public void deletarEntrega(Long id) {
        getEntrega(id);
        try{
            entregaRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não foi possivel deletar a entrega: Item Ativo.");
        }
    }

    public void getComprovantePdf(HttpServletResponse response, Long id) throws IOException {

        Entrega entrega  = getEntrega(id);

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        document.add(new Paragraph("EazyLogg", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, Color.BLACK)));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        Paragraph paragraph = new Paragraph("Resumo", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(14);

        Paragraph paragraph2 = new Paragraph("Entrega cód: " + entrega.getId(), fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph3 = new Paragraph("Remetente: " + entrega.getEnderecoColetaId().getUsuario().getNome(), fontParagraph);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph4 = new Paragraph("Destinatário: " + entrega.getEnderecoEntregaId().getUsuario().getNome(), fontParagraph);
        paragraph4.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph5 = new Paragraph("Data de coleta: " + entrega.getDataColeta(), fontParagraph);
        paragraph5.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph6 = new Paragraph("Pagamento: " + entrega.getPagamento().getStatus(), fontParagraph);
        paragraph6.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        document.add(paragraph5);
        document.add(paragraph6);

        float columnWidth[] = {50f, 10f, 20f, 20f, 20f};



        Paragraph paragraph7 = new Paragraph("Valor Total: R$ " + entrega.getValorTotal(), FontFactory.getFont(FontFactory.HELVETICA, 18, Color.BLACK));
        paragraph7.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(paragraph7);
        document.close();
    }

}
