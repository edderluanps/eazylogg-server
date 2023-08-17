package com.eazylogg.backend;

import com.eazylogg.backend.controllers.EntregaController;
import com.eazylogg.backend.models.*;
import com.eazylogg.backend.services.EntregaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntregaControllerTest {

    @InjectMocks
    private EntregaController entregaController;

    @Mock
    private EntregaService entregaService;

    private Entrega entregaTest;

    private Usuario usuarioTest;

    private Pacote pacoteTest;

    private Endereco enderecoTest;

    private Pagamento pagamentoTest;

    @BeforeEach
    void setup(){
        LocalDate dataAtual = LocalDate.now();

        entregaTest = new Entrega();

        entregaTest.setDataColeta(dataAtual);
        entregaTest.setEnderecoColetaId(enderecoTest);
        entregaTest.setEntregadorId(usuarioTest);
        entregaTest.setPacoteId(pacoteTest);
        entregaTest.setPagamento(pagamentoTest);
        entregaTest.setValor(50.00);
        entregaTest.setDesconto(30.00);
        entregaTest.setObs(null);
        entregaTest.setAtivo(true);

    }

    @Test
    void salvarEntraga(){
        var response = assertDoesNotThrow(() -> entregaController.salvar(entregaTest));
        System.out.println("Passou no teste: " + response);
    }

    @Test
    void getEntrega(){
        when(entregaService.salvarEntrega(entregaTest)).thenReturn(entregaTest);
        var response = assertDoesNotThrow(() -> entregaController.getAll());
        assertNotNull(response);
        assertEquals(entregaTest, response);
        System.out.println("Passou no teste");
    }
}
