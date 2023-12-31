package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Endereco;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.models.dto.UsuarioDTO;
import com.eazylogg.backend.models.dto.UsuarioNewDTO;
import com.eazylogg.backend.models.enums.TipoCliente;
import com.eazylogg.backend.models.enums.TipoPerfil;
import com.eazylogg.backend.repositories.AvaliacaoRepository;
import com.eazylogg.backend.repositories.UsuarioRepository;
import com.eazylogg.backend.security.UserSS;
import com.eazylogg.backend.services.exceptions.AuthorizationException;
import com.eazylogg.backend.services.exceptions.DataIntegrityException;
import com.eazylogg.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder bpe;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Usuario buscarUsuarioPorId(Long id){
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(TipoPerfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        return usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }

    public UsuarioDTO usuarioToDto(Long id){
        Usuario user = usuarioRepository.getById(id);
        UsuarioDTO userDto = new UsuarioDTO(user);
        return userDto;
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        Usuario novoUsuario = buscarUsuarioPorId(usuario.getId());
        atualizarNovoUsuario(novoUsuario, usuario);
        return usuarioRepository.save(novoUsuario);
    }

    public void deletarUsuario(Long id) {
        buscarUsuarioPorId(id);
        try{
            usuarioRepository.deleteById(id);
        }catch(DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não foi possivel deletar o Usuário: Usuário Ativo.");
        }
    }

    public Usuario fromDTO(UsuarioDTO usuarioDTO) {
        return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail(),
                null, null, null, null, null, null, null, true);
    }

    public Usuario fromDTO(UsuarioNewDTO usuarioDTO) {

        LocalDate dataAtual = LocalDate.now();

        Usuario usuario = new Usuario(null  , usuarioDTO.getNome(), usuarioDTO.getDescricao(), usuarioDTO.getCpfOuCnpj(),
                usuarioDTO.getDataNascimento(), dataAtual, TipoCliente.toEnum(usuarioDTO.getTipoCliente()), usuarioDTO.getCategoria(), usuarioDTO.getEmail(), bpe.encode(usuarioDTO.getSenha()), true);

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

    private void atualizarNovoUsuario(Usuario usuarioNovo, Usuario usuario) {
        usuarioNovo.setNome(usuario.getNome());
        usuarioNovo.setEmail(usuario.getEmail());
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        UserSS user = UserService.authenticated();
        if (user == null || !user.hasRole(TipoPerfil.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Acesso negado");
        }

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new ObjectNotFoundException("Usuário não encontrado!");
        }
        return usuario;
    }

    public List<Usuario> pesquisarUsuarioEntregador(String pesquisa, String categoria){
        return usuarioRepository.pesquisar(pesquisa, categoria);
    }

    public List<Usuario> listaUsuarioEntragador(String categoria){
        return usuarioRepository.listarUsuariosEntregadores(categoria);
    }

    public Page<Usuario> usuarioPage(String categoria, Integer page, Integer pageRows, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, pageRows, Sort.Direction.valueOf(direction), orderBy);
        return usuarioRepository.filtrarUsuariosPorCategoria(categoria, pageRequest);
    }
}