package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Endereco;
import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco getEndereco(Long id){
        return enderecoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco não encontrado!"));
    }

    public List<Endereco> getListaEnderecos(){
        return enderecoRepository.findAll();
    }

    public Endereco salvarEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public void atualizarEndereco(Long id, Endereco endereco){
        enderecoRepository.findById(id).map(obj -> {
            endereco.setId(obj.getId());
            return enderecoRepository.save(endereco);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado!"));
    }

    public void deletarEndereco(Long id) {
        enderecoRepository.findById(id).map(obj -> {
            enderecoRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado!"));
    }

}
