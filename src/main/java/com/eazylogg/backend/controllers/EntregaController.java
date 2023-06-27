package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.services.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eazylogg/entrega")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @GetMapping
    public List<Entrega> getAll() {
        return entregaService.getListaEntregas();
    }

    @GetMapping("/{id}")
    public Entrega getById(@PathVariable Long id) {
        return entregaService.getEntrega(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega salvar(@RequestBody @Validated Entrega entrega){
        return entregaService.salvarEntrega(entrega);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Entrega entrega){
        entregaService.atualizarEntrega(id, entrega);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        entregaService.deletarEntrega(id);
    }
}
