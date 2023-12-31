package com.eazylogg.backend.controllers;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.services.EntregaService;
import com.eazylogg.backend.services.PDFService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @Autowired
    private PDFService pdfService;

    @ApiOperation(value = "Listar entregas")
    @GetMapping
    public List<Entrega> listarEntregas() {
        return entregaService.listarEntregas();
    }

    @ApiOperation(value = "Buscar entrega por id")
    @GetMapping("/{id}")
    public Entrega buscarEntregaPorId(@PathVariable Long id) {
        return entregaService.buscarEntregaPorId(id);
    }

    @ApiOperation(value = "Listar entregas por entregadores responsáveis")
    @GetMapping("/entregas-realizadas")
    public List<Entrega> buscarEntregaPorEntregadorId(@RequestParam(value = "entId", defaultValue = "0") Long entId) {
        return entregaService.buscarEntregaPorEntregadorId(entId);
    }

    @ApiOperation(value = "Cadastrar entrega")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega salvarEntrega(@RequestBody @Validated Entrega entrega){
        return entregaService.salvarEntrega(entrega);
    }

    @ApiOperation(value = "Atualizar entrega")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Entrega atualizarEntrega(@PathVariable Long id, @RequestBody Entrega entrega){
        return entregaService.atualizarEntrega(id, entrega);
    }

    @ApiOperation(value = "Deletar entrega")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Não é possível excluir uma entrega vinculada a um serviço ativo"),
            @ApiResponse(code = 400, message = "Entrega inexistente.")
    })    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarEntrega(@PathVariable Long id){
        entregaService.deletarEntrega(id);
    }

    @ApiOperation(value = "Imprimir comprovante de entrega")
    @GetMapping("/comprovante/{id}")
    public void gerarComprovantePdf(@PathVariable Long id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyy - hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfService.gerarComprovantePdf(response, id);
    }

    @ApiOperation(value = "Buscar entregas por código de rastreamento")
    @GetMapping("/pesquisa-rastreamento")
    public List<Entrega> pesquisarEntregaByCodRastreamento(@RequestParam(value = "codigo", defaultValue = "") String codigo) {
        return entregaService.pesquisarEntregaPorCodRastreamento(codigo);
    }

}
