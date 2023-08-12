package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Pagamento;
import com.eazylogg.backend.services.PagamentoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eazylogg/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @ApiOperation(value = "Listar pagamentos")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Pagamento> getAll() {
        return pagamentoService.getListaPagamentos();
    }

    @ApiOperation(value = "Buscar pagamento por id")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Pagamento getById(Long id){
        return pagamentoService.getPagamento(id);
    }

    @ApiOperation(value = "Cadastrar pagamento")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento salvar(@RequestBody @Validated Pagamento pagamento){
        return pagamentoService.salvarPagamento(pagamento);
    }

    @ApiOperation(value = "Atualizar pagamento")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Pagamento pagamento){
        pagamentoService.atualizarPagamento(id, pagamento);
    }

    @ApiOperation(value = "Deletar pagamento")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir um pagamento vinculado a um serviço ativo"),
            @ApiResponse(code = 400, message = "Pagamento inexistente.")
    })    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        pagamentoService.deletarVeiculo(id);
    }
}
