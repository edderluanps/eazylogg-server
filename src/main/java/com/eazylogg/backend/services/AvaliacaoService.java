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

    public List<Avaliacao> listaAvaliacao(){
        return avaliacaoRepository.findAll();
    }

    public Avaliacao buscarAvaliacaoPorId(Long id){
        return avaliacaoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Avaliação não encontrada!"));
    }

    public Double buscarMediaAvaliacaoPorUsuarioId(Long id){
        Usuario user = usuarioRepository.findById(id).get();
        Double avg = avaliacaoRepository.buscarMediaAvaliacao(user.getId());
        return avg;
    }

    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao.getAvaliador().getId() == avaliacao.getUsuario().getId()){
            throw new ObjectNotFoundException("Um usuário não pode se autoavaliar!");
        }
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao atualizarAvaliacao(Long id, Avaliacao avaliacao){
        avaliacao = buscarAvaliacaoPorId(id);
        if (avaliacao != null){
            return avaliacaoRepository.save(avaliacao);
        }else{
            throw new ObjectNotFoundException("Um usuário não pode se autoavaliar!");
        }
    }
}
