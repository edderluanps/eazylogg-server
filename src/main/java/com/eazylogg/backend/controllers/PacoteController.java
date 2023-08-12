package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.services.PacoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Listar pacotes")
    @GetMapping
    public List<Pacote> getAll() {
        return pacoteService.getListaPacotes();
    }

    @ApiOperation(value = "Buscar pacote por id")
    @GetMapping("/{id}")
    public Pacote getById(@PathVariable Long id){
        return pacoteService.getPacote(id);
    }

    @ApiOperation(value = "Cadastrar pacote")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pacote salvar(@RequestBody @Validated Pacote pacote){
        return pacoteService.salvarPacote(pacote);
    }

    @ApiOperation(value = "Atualizar pacote")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Pacote pacote){
        pacoteService.atualizarPacote(id, pacote);
    }

    @ApiOperation(value = "Deletar pacote")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um pacote vinculado a uma entrega ativa"),
            @ApiResponse(code = 400, message = "Pacote inexistente.")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        pacoteService.deletarPacote(id);
    }

    @ApiOperation(value = "Pesquisar pacote")
    @GetMapping("/pesquisa")
    public List<Pacote> pesquisarPacote(
            @RequestParam(value = "porte", defaultValue = "") String porte,
            @RequestParam(value = "cep", defaultValue = "") String cep){
        return pacoteService.pesquisarPacote(porte, cep);
    }

}
