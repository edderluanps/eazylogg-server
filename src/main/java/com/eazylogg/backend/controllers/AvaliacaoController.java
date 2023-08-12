package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Avaliacao;
import com.eazylogg.backend.services.AvaliacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eazylogg/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @ApiOperation(value = "Listar avaliações")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Avaliacao> getAll(){
        return avaliacaoService.getListaAvaliacao();
    }

    @ApiOperation(value = "Buscar avaliação por id")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Avaliacao getById(@PathVariable Long id){
        return avaliacaoService.getAvaliacao(id);
    }

    @ApiOperation(value = "Buscar média de avaliação do usuário por id")
    @GetMapping("/media/{id}")
    public Double getMediaAvaliacao(@PathVariable Long id){
        return avaliacaoService.getMediaAvaliacao(id);
    }

    @ApiOperation(value = "Cadastrar avaliação")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao salvar(@RequestBody @Validated Avaliacao avaliacao){
        return avaliacaoService.salvarAvaliacao(avaliacao);
    }

    @ApiOperation(value = "Atualizar avaliação")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Avaliacao avaliacao){
        avaliacaoService.atualizarAvaliacao(id, avaliacao);
    }
}
