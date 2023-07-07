package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Pagamento;
import com.eazylogg.backend.repositories.PagamentoRepository;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Pagamento getPagamento(Long id){
        return pagamentoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pagamento não encontrado!"));
    }

    public List<Pagamento> getListaPagamentos(){
        return pagamentoRepository.findAll();
    }

    public Pagamento salvarPagamento(Pagamento pagamento){
        return pagamentoRepository.save(pagamento);
    }

    public void atualizarPagamento(Long id, Pagamento pagamento){
        pagamentoRepository.findById(id).map(obj -> {
            pagamento.setId(obj.getId());
            return pagamentoRepository.save(pagamento);
        }).orElseThrow(() -> new ObjectNotFoundException("Pagamento não encontrado!"));
    }

    public void deletarVeiculo(Long id) {
        getPagamento(id);
        try{
            pagamentoRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não foi possivel deletar o Pagamento: Item Ativo.");
        }
    }

}
