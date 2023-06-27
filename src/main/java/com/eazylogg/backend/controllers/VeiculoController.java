package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Veiculo;
import com.eazylogg.backend.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eazylogg/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public List<Veiculo> getAll() {
        return veiculoService.getListaVeiculos();
    }

    @GetMapping("/{id}")
    public Veiculo getById(@PathVariable Long id) {
        return veiculoService.getVeiculo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo salvar(@RequestBody @Validated Veiculo veiculo){
        return veiculoService.salvarVeiculo(veiculo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo){
        veiculoService.atualizarVeiculo(id, veiculo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        veiculoService.deletarVeiculo(id);
    }
}
