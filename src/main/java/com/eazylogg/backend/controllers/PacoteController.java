package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.services.PacoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public List<Pacote> listarPacotes() {
        return pacoteService.listarPacotes();
    }

    @ApiOperation(value = "Listar ultimos pacotes")
    @GetMapping("/ultimos-resultados")
    public List<Pacote> buscarUltimosPacotes() {
        return pacoteService.buscarUltimosPacotes();
    }

    @ApiOperation(value = "Listar pacotes por contratante")
    @GetMapping("/pacotes-contratante")
    public List<Pacote> buscarPacotePorContratanteId(@RequestParam(value = "contId", defaultValue = "0") Long id) {
        return pacoteService.buscarPacotePorContratanteId(id);
    }

    @ApiOperation(value = "Buscar pacote por id")
    @GetMapping("/{id}")
    public Pacote buscarPacotePorId(@PathVariable Long id){
        return pacoteService.buscarPacotePorId(id);
    }

    @ApiOperation(value = "Cadastrar pacote")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pacote salvarPacote(@RequestBody @Validated Pacote pacote){
        return pacoteService.salvarPacote(pacote);
    }

    @ApiOperation(value = "Atualizar pacote")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Pacote atualizarPacote(@PathVariable Long id, @RequestBody Pacote pacote){
        return pacoteService.atualizarPacote(id, pacote);
    }

    @ApiOperation(value = "Deletar pacote")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um pacote vinculado a uma entrega ativa"),
            @ApiResponse(code = 400, message = "Pacote inexistente.")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPacote(@PathVariable Long id){
        pacoteService.deletarPacote(id);
    }

    @ApiOperation(value = "Pesquisar pacote")
    @GetMapping("/pesquisa")
    public List<Pacote> pesquisarPacote(
            @RequestParam(value = "descricao", defaultValue = "") String descricao){
        return pacoteService.pesquisarPacote(descricao);
    }

    @ApiOperation(value = "Filtrar pacote")
    @GetMapping("/filtrar")
    public List<Pacote> filtrarPacote(
            @RequestParam(value = "porte", defaultValue = "") String porte,
            @RequestParam(value = "cep", defaultValue = "") String cep){
        return pacoteService.filtrarPacote(porte, cep);
    }

    @ApiOperation(value = "Paginação de pacotes")
    @GetMapping("/page")
    public Page<Pacote> pacotePage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        return pacoteService.pacotePage(page, linesPerPage, orderBy, direction);
    }

}
