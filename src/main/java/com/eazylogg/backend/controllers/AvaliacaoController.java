package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Avaliacao;
import com.eazylogg.backend.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eazylogg/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Avaliacao> getAll(){
        return avaliacaoService.getListaAvaliacao();
    }

    @GetMapping("/{id}")
    public Avaliacao getById(@PathVariable Long id){
        return avaliacaoService.getAvaliacao(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Avaliacao salvar(@RequestBody @Validated Avaliacao avaliacao){
        return avaliacaoService.salvarAvaliacao(avaliacao);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Avaliacao avaliacao){
        avaliacaoService.atualizarAvaliacao(id, avaliacao);
    }
}
