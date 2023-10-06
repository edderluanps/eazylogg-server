package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Veiculo;
import com.eazylogg.backend.services.VeiculoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Listar veículos")
    @GetMapping
    public List<Veiculo> listarVeiculos() {
        return veiculoService.listarVeiculos();
    }

    @ApiOperation(value = "Buscar veículo por id")
    @GetMapping("/{id}")
    public Veiculo buscarVeiculoPorId(@PathVariable Long id) {
        return veiculoService.buscarVeiculoPorId(id);
    }

    @ApiOperation(value = "Buscar veículo por id")
    @GetMapping("/veiculo-usuario/{id}")
    public List<Veiculo> buscarVeiculoPorUsuarioId(@PathVariable Long id) {
        return veiculoService.buscarVeiculoPorUsuarioId(id);
    }

    @ApiOperation(value = "Cadastrar veículo")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo salvarVeiculo(@RequestBody @Validated Veiculo veiculo){
        return veiculoService.salvarVeiculo(veiculo);
    }

    @ApiOperation(value = "Atualizar veículo")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Veiculo atualizarVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo){
        return veiculoService.atualizarVeiculo(id, veiculo);
    }

    @ApiOperation(value = "Deletar veículo")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um veículo vinculado a um serviço ativo"),
            @ApiResponse(code = 400, message = "Veículo inexistente.")
    })    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarVeiculo(@PathVariable Long id){
        veiculoService.deletarVeiculo(id);
    }
}
