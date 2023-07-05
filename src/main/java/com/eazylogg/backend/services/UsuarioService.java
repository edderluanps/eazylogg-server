package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Avaliacao;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.repositories.AvaliacaoRepository;
import com.eazylogg.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Usuario getUsuario(Long id){
        Double avaliacao = avaliacaoRepository.findAvgUser(id);
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setNota(avaliacao);
        return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public List<Usuario> getListaUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void atualizarUsuario(Long id, Usuario usuario) {
        usuarioRepository.findById(id).map(obj -> {
            usuario.setId(obj.getId());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.findById(id).map(obj -> {
            usuarioRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

}