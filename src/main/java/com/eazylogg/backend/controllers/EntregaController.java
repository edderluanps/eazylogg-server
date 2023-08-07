package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.services.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/comprovante/{id}")
    public void getComprovantePdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyy - hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.entregaService.getComprovantePdf(response, id);
    }

}
