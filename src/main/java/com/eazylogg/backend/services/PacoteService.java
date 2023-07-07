package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.repositories.PacoteRepository;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    public Pacote getPacote(Long id){
        return pacoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Pacote não encontrado!"));
    }

    public List<Pacote> getListaPacotes(){
        return pacoteRepository.findAll();
    }

    public Pacote salvarPacote(Pacote pacote){
        return pacoteRepository.save(pacote);
    }

    public void atualizarPacote(Long id, Pacote pacote){
        pacoteRepository.findById(id).map(obj -> {
            pacote.setId(obj.getId());
            return pacoteRepository.save(pacote);
        }).orElseThrow(() -> new ObjectNotFoundException("Pacote não encontrado!"));
    }

    public void deletarPacote(Long id) {
        getPacote(id);
        try{
            pacoteRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não foi possivel deletar o Pacote: Item Ativo.");
        }
    }

}
