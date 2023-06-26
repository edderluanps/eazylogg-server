package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega getEntrega(Long id){
        return entregaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrado!"));
    }

    public List<Entrega> getListaEntregas(Long id){
        return entregaRepository.findAll();
    }

    public Entrega salvarEntrega(Entrega entrega){
        return entregaRepository.save(entrega);
    }

    public void atualizarEntrega(Long id, Entrega entrega){
        entregaRepository.findById(id).map(obj -> {
            entrega.setId(obj.getId());
            return Void.TYPE;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrado!"));
    }

    public void deletarEntrega(Long id) {
        entregaRepository.findById(id).map(obj -> {
            entregaRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrega não encontrado!"));
    }

}
