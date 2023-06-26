package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Pagamento;
import com.eazylogg.backend.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento getPagamento(Long id){
        return pagamentoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado!"));
    }

    public List<Pagamento> getListaPagamentos(Long id){
        return pagamentoRepository.findAll();
    }

    public Pagamento salvarPagamento(Pagamento pagamento){
        return pagamentoRepository.save(pagamento);
    }

    public void atualizarPagamento(Long id, Pagamento pagamento){
        pagamentoRepository.findById(id).map(obj -> {
            pagamento.setId(obj.getId());
            return Void.TYPE;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado!"));
    }

    public void deletarVeiculo(Long id) {
        pagamentoRepository.findById(id).map(obj -> {
            pagamentoRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagamento não encontrado!"));
    }

}
