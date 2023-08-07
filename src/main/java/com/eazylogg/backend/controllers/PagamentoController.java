package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Pagamento;
import com.eazylogg.backend.services.PagamentoService;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Pagamento> getAll() {
        return pagamentoService.getListaPagamentos();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Pagamento getById(Long id){
        return pagamentoService.getPagamento(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pagamento salvar(@RequestBody @Validated Pagamento pagamento){
        return pagamentoService.salvarPagamento(pagamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody Pagamento pagamento){
        pagamentoService.atualizarPagamento(id, pagamento);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        pagamentoService.deletarVeiculo(id);
    }
}
