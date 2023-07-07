package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.repositories.EntregaRepository;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega getEntrega(Long id){
        return entregaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Entrega não encontrado!"));
    }

    public List<Entrega> getListaEntregas(){
        return entregaRepository.findAll();
    }

    public Entrega salvarEntrega(Entrega entrega){
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
            throw new DataIntegrityException("Não foi possivel deletar o Endereço: Item Ativo.");
        }
    }

}
