package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.services.PacoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eazylogg/pacote")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;

    @GetMapping
    public List<Pacote> getAll() {
        return pacoteService.getListaPacotes();
    }

    @GetMapping("/{id}")
    public Pacote getById(@PathVariable Long id){
        return pacoteService.getPacote(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pacote salvar(@RequestBody @Validated Pacote pacote){
        return pacoteService.salvarPacote(pacote);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Pacote pacote){
        pacoteService.atualizarPacote(id, pacote);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        pacoteService.deletarPacote(id);
    }

    @GetMapping("/pesquisa")
    public List<Pacote> pesquisarPacote(
            @RequestParam(value = "porte", defaultValue = "") String porte,
            @RequestParam(value = "cep", defaultValue = "") String cep){
        return pacoteService.pesquisarPacote(porte, cep);
    }

}
