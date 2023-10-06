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
    public List<Avaliacao> listarAvaliacoes(){
        return avaliacaoService.listaAvaliacao();
    }

    @ApiOperation(value = "Buscar avaliação por id")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Avaliacao buscarAvaliacaoPorId(@PathVariable Long id){
        return avaliacaoService.buscarAvaliacaoPorId(id);
    }

    @ApiOperation(value = "Buscar média de avaliação do usuário por id")
    @GetMapping("/media/{id}")
    public Double buscarMediaAvaliacaoPorUsuarioId(@PathVariable Long id){
        return avaliacaoService.buscarMediaAvaliacaoPorUsuarioId(id);
    }

    @ApiOperation(value = "Cadastrar avaliação")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao salvarAvaliacao(@RequestBody @Validated Avaliacao avaliacao){
        return avaliacaoService.salvarAvaliacao(avaliacao);
    }

    @ApiOperation(value = "Atualizar avaliação")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Avaliacao atualizarAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao){
        return avaliacaoService.atualizarAvaliacao(id, avaliacao);
    }
}
