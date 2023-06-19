package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.repositories.UsuarioRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuario(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public List<Usuario> getUsuarioLista(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void atualizar(Long id, Usuario usuario) {
        usuarioRepository.findById(id).map(obj -> {
            usuario.setId(obj.getId());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public void delete(Long id) {
        usuarioRepository.findById(id).map(obj -> {
            usuarioRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

}
