package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Endereco;
import com.eazylogg.backend.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eazylogg/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> getAll() {
        return enderecoService.getListaEnderecos();
    }

    @GetMapping("/{id}")
    public Endereco getById(@PathVariable Long id) {
        return enderecoService.getEndereco(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco salvar(@RequestBody @Validated Endereco endereco){
        return enderecoService.salvarEndereco(endereco);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Endereco endereco){
        enderecoService.atualizarEndereco(id, endereco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        enderecoService.deletarEndereco(id);
    }
}
