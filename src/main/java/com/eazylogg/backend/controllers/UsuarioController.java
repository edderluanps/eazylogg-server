package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.models.dto.UsuarioDTO;
import com.eazylogg.backend.models.dto.UsuarioNewDTO;
import com.eazylogg.backend.services.UsuarioService;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/eazylogg/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Listar usuarios")
    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> lista = usuarioService.listarUsuarios();
        List<UsuarioDTO> listaDto = lista.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return listaDto;
    }

    @ApiOperation(value = "Buscar usuario por id")
    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }

    @ApiOperation(value = "Buscar Entregador por id")
    @GetMapping("/entregador/{id}")
    public UsuarioDTO buscarUsuarioEntregadorPorId(@PathVariable Long id) {
        return usuarioService.usuarioToDto(id);
    }

    @ApiOperation(value = "Cadastrar usuario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvarUsuario(@RequestBody @Validated UsuarioNewDTO usuarioDTO){
        Usuario usuario = usuarioService.fromDTO(usuarioDTO);
        usuario = usuarioService.salvarUsuario(usuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return usuario;
    }

    @ApiOperation(value = "Atualizar usuario")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioService.fromDTO(usuarioDTO);
        usuario.setId(id);
        usuario = usuarioService.atualizarUsuario(usuario);
    }

    @ApiOperation(value = "Deletar usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um usuário vinculado a um serviço ativo"),
            @ApiResponse(code = 400, message = "Usuário inexistente.")
    })    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);
    }

    @ApiOperation(value = "Buscar usuario por email")
    @GetMapping("/email")
    public Usuario buscarUsuarioPorEmail(@RequestParam(value = "value") String email) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        return usuario;
    }

    @ApiOperation(value = "Pesquisar usuario")
    @GetMapping("/pesquisa")
    public List<Usuario> pesquisarUsuarioEntregador(
            @RequestParam(value = "pesquisa", defaultValue = "") String pesquisa,
            @RequestParam(value = "categoria", defaultValue = "") String categoria){
        return usuarioService.pesquisarUsuarioEntregador(pesquisa, categoria);
    }

    @ApiOperation(value = "Listar usuarios entregadores")
    @GetMapping("/entregadores")
    public List<Usuario> listaUsuarioEntragador(
            @RequestParam(value = "categoria", defaultValue = "") String categoria){
        return usuarioService.listaUsuarioEntragador( categoria);
    }

    @ApiOperation(value = "Paginação de usuário")
    @GetMapping("/page")
    public Page<Usuario> usuarioPage(
            @RequestParam(value = "categoria", defaultValue = "") String categoria,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        return usuarioService.usuarioPage(categoria, page, linesPerPage, orderBy, direction);
    }
}
