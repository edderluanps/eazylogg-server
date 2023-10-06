package com.eazylogg.backend;

import com.eazylogg.backend.controllers.EntregaController;
import com.eazylogg.backend.models.*;
import com.eazylogg.backend.services.EntregaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Testes - Entrega")
@ExtendWith(MockitoExtension.class)
public class EntregaTest {

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

    @DisplayName("Deve listar todas as entregas")
    @Test
    void deveListarTodasEntragas() throws Exception{
        when(entregaService.listarEntregas()).thenReturn(Arrays.asList());
        var response = entregaController.listarEntregas();
        assertNotNull(response);
        assertEquals(Arrays.asList(), response);
    }

    @DisplayName("Deve buscar uma entrega por ID")
    @Test
    void deveBuscarUmEntragaPorId() throws Exception{
        when(entregaService.buscarEntregaPorId(1L)).thenReturn(entregaTest);
        var response = entregaController.buscarEntregaPorId(1L);
        assertNotNull(response);
        assertEquals(entregaTest, response);
    }

    @DisplayName("Deve buscar uma entrega por usuario ID")
    @Test
    void deveBuscarUmEntragaPorUsuarioId() throws Exception{
        when(entregaService.buscarEntregaPorEntregadorId(1L)).thenReturn(Arrays.asList());
        var response = entregaController.buscarEntregaPorEntregadorId(1L);
        assertNotNull(response);
        assertEquals(Arrays.asList(), response);
    }

    @DisplayName("Deve salvar uma entrega")
    @Test
    void deveSalvarEntraga() throws Exception{
        when(entregaService.salvarEntrega(entregaTest)).thenReturn(entregaTest);
        var response = entregaController.salvarEntrega(entregaTest);
        assertNotNull(response);
        assertEquals(entregaTest, response);
    }

    @DisplayName("Deve editar uma entrega")
    @Test
    void deveEditarEntraga() throws Exception {
        when(entregaService.atualizarEntrega(1L, entregaTest)).thenReturn(entregaTest);
        var response = entregaController.atualizarEntrega(1L, entregaTest);
        assertNotNull(response);
        assertEquals(entregaTest, response);
    }

    @DisplayName("Deve deletar uma entrega")
    @Test
    void deveDeletarEntraga() throws Exception {
        doNothing().when(entregaService).deletarEntrega(1L);
        try {
            assertDoesNotThrow(() -> entregaController.deletarEntrega(1L));
        } catch (Exception e) {
            fail("Exceção: " + e.getMessage());
        }
        verify(entregaService, times(1)).deletarEntrega(anyLong());
        verifyNoMoreInteractions(entregaService);
    }

    @DisplayName("Deve buscar uma entrega por Cód. de rastreamento")
    @Test
    void devePesquisarEntregaByCodRastreamento() throws Exception{
        when(entregaService.pesquisarEntregaPorCodRastreamento("123456789")).thenReturn(Arrays.asList());
        var response = entregaController.pesquisarEntregaByCodRastreamento("123456789");
        assertNotNull(response);
        assertEquals(Arrays.asList(), response);
    }

}
