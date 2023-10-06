package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Endereco;
import com.eazylogg.backend.repositories.EnderecoRepository;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco buscarEnderecoPorId(Long id){
        return enderecoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Endereco não encontrado!"));
    }

    public List<Endereco> listarEnderecos(){
        return enderecoRepository.findAll();
    }

    public Endereco salvarEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public void atualizarEndereco(Long id, Endereco endereco){
        enderecoRepository.findById(id).map(obj -> {
            endereco.setId(obj.getId());
            return enderecoRepository.save(endereco);
        }).orElseThrow(() -> new ObjectNotFoundException("Endereco não encontrado!"));
    }

    public void deletarEndereco(Long id) {
        buscarEnderecoPorId(id);
        try{
            enderecoRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não foi possivel deletar o Endereço: Item Ativo.");
        }
    }

}
