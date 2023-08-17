package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@Service
public class PDFService {

    @Autowired
    private EntregaService entregaService;

    public void getComprovantePdf(HttpServletResponse response, Long id) throws IOException {

        Entrega entrega  = entregaService.getEntrega(id);

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

        Paragraph paragraph4 = new Paragraph("Destinatário: " + entrega.getPacoteId().getNomeDestinatario(), fontParagraph);
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

        Paragraph paragraph7 = new Paragraph("Valor da entrega: R$ " + entrega.getPacoteId().getValor(), FontFactory.getFont(FontFactory.HELVETICA, 15, Color.BLACK));
        paragraph7.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph8 = new Paragraph("Descontos: R$ " + entrega.getDesconto(), FontFactory.getFont(FontFactory.HELVETICA, 15, Color.BLACK));
        paragraph8.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph paragraph9 = new Paragraph("Valor Total: R$ " + entrega.getValorTotal(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK));
        paragraph9.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(paragraph7);
        document.add(paragraph8);
        document.add(paragraph9);
        document.close();
    }
}
