package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Endereco;
import com.eazylogg.backend.services.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eazylogg/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @ApiOperation(value = "Listar endereços")
    @GetMapping
    public List<Endereco> getAll() {
        return enderecoService.getListaEnderecos();
    }

    @ApiOperation(value = "Buscar endereço por id")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Endereco getById(@PathVariable Long id) {
        return enderecoService.getEndereco(id);
    }

    @ApiOperation(value = "Cadastrar endereço")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salvar(@RequestBody @Validated Endereco endereco){
        return enderecoService.salvarEndereco(endereco);
    }

    @ApiOperation(value = "Atualizar endereço")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Endereco endereco){
        enderecoService.atualizarEndereco(id, endereco);
    }

    @ApiOperation(value = "Deletar endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um endereço vinculado a um usuário ativo"),
            @ApiResponse(code = 400, message = "Endereço inexistente.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        enderecoService.deletarEndereco(id);
    }
}
