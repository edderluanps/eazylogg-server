package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.repositories.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    public Pacote getPacote(Long id){
        return pacoteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado!"));
    }

    public List<Pacote> getListaPacotes(Long id){
        return pacoteRepository.findAll();
    }

    public Pacote salvarPacote(Pacote pacote){
        return pacoteRepository.save(pacote);
    }

    public void atualizarPacote(Long id, Pacote pacote){
        pacoteRepository.findById(id).map(obj -> {
            pacote.setId(obj.getId());
            return Void.TYPE;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado!"));
    }

    public void deletarPacote(Long id) {
        pacoteRepository.findById(id).map(obj -> {
            pacoteRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pacote não encontrado!"));
    }

}
