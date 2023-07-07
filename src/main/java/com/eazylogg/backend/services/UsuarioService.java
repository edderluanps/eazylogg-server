package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Endereco;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.models.dto.UsuarioDTO;
import com.eazylogg.backend.models.dto.UsuarioNewDTO;
import com.eazylogg.backend.models.enums.TipoCliente;
import com.eazylogg.backend.repositories.AvaliacaoRepository;
import com.eazylogg.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Usuario getUsuario(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public List<Usuario> getListaUsuarios(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        Usuario novoUsuario = getUsuario(usuario.getId());
        UpdateUsuario(novoUsuario, usuario);
        return usuarioRepository.save(novoUsuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.findById(id).map(obj -> {
            usuarioRepository.delete(obj);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!"));
    }

    public Usuario fromDTO(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(),
                null, null, null, null, null, true);
    }

    public Usuario fromDTO(UsuarioNewDTO usuarioDTO) {
        Usuario usuario = new Usuario(null  , usuarioDTO.getNome(), usuarioDTO.getEmail(),
                usuarioDTO.getDataNascimento(), usuarioDTO.getDataCadastro(), TipoCliente.toEnum(usuarioDTO.getTipoCliente()), usuarioDTO.getEmail(), usuarioDTO.getSenha(), true);

        Endereco endereco = new Endereco(null, usuarioDTO.getLogradouro(), usuarioDTO.getNumero(), usuarioDTO.getCep(),usuarioDTO.getComplemento(), usuarioDTO.getBairro(),
                usuarioDTO.getCidade(), usuarioDTO.getEstado(), usuarioDTO.getPais(), usuario, true);

        usuario.getEndereco().add(endereco);
        usuario.getTelefone().add(usuarioDTO.getTelefone1());
        if (usuarioDTO.getTelefone2() != null) {
            usuario.getTelefone().add(usuarioDTO.getTelefone2());
        }
        if (usuarioDTO.getTelefone3() != null) {
            usuario.getTelefone().add(usuarioDTO.getTelefone3());
        }

        return usuario;
    }

    private void UpdateUsuario(Usuario usuarioNovo, Usuario usuario) {
        usuarioNovo.setNome(usuario.getNome());
        usuarioNovo.setEmail(usuario.getEmail());
    }
}