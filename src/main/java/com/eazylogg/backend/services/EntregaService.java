package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.models.enums.EntregaStatus;
import com.eazylogg.backend.models.enums.PagamentoStatus;
import com.eazylogg.backend.repositories.EntregaRepository;
import com.eazylogg.backend.repositories.PacoteRepository;
import com.eazylogg.backend.repositories.PagamentoRepository;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PacoteRepository pacoteRepository;

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

        Pacote pacote = pacoteRepository.findById(entrega.getPacoteId().getId()).get();
        entrega.setValor(pacote.getValor());

        pagamentoRepository.save(entrega.getPagamento());
        //emailService.sendConfirmationEmail(entrega);
        String codRastreamento = gerarCodigoRastreamento();
        entrega.setCodRastreamento(codRastreamento + "-" + entrega.getPacoteId().getId());
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

    public List<Entrega> findByEntregadorId(Long entId){
        return entregaRepository.findByEntregadorId(entId);
    }

    public List<Entrega> pesquisarEntregaByCodRastreamento(String codigo){
        return entregaRepository.pesquisarEntregaByCodRastreamento(codigo);
    }


    public static String gerarCodigoRastreamento() {
        String caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder codigo = new StringBuilder(8);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 8; i++) {
            int indice = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }
        return codigo.toString();
    }

}