package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Avaliacao;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.repositories.AvaliacaoRepository;
import com.eazylogg.backend.repositories.UsuarioRepository;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Avaliacao> getListaAvaliacao(){
        return avaliacaoRepository.findAll();
    }

    public Avaliacao getAvaliacao(Long id){
        return avaliacaoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Avaliação não encontrada!"));
    }

    public Double getMediaAvaliacao(Long id){
        Usuario user = usuarioRepository.findById(id).get();
        Double avg = avaliacaoRepository.findAvgUser(user.getId());
        System.out.println(avg);
        return avg;
    }

    public Avaliacao salvarAvaliacao(Avaliacao avaliacao){
        return avaliacaoRepository.save(avaliacao);
    }

    public void atualizarAvaliacao(Long id, Avaliacao avaliacao){
        avaliacaoRepository.findById(id).map(obj -> {
            avaliacao.setId(obj.getId());
            return avaliacaoRepository.save(avaliacao);
        }).orElseThrow(() -> new ObjectNotFoundException("Avaliação não encontrada!"));
    }
}
