package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.repositories.PacoteRepository;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Pacote> pesquisarPacote(String descricao){
        return pacoteRepository.pesquisa(descricao);
    }

    public List<Pacote> buscarUltimosPacotes(){
        return pacoteRepository.buscarUltimosPacotes();
    }

    public List<Pacote> filtrarPacote(String porte, String cep){
        return pacoteRepository.filtragem(porte, cep);
    }

    public Page<Pacote> pacotePage(Integer page, Integer pageRows, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, pageRows, Sort.Direction.valueOf(direction), orderBy);
        return pacoteRepository.findAll(pageRequest);
    }

    public List<Pacote> findBycontratanteId(Long id){
        return pacoteRepository.findBycontratanteId(id);
    }

}
