package com.eazylogg.backend;

import com.eazylogg.backend.controllers.PacoteController;
import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.services.PacoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacoteTest {

    @InjectMocks
    private PacoteController pacoteController;

    @Mock
    private PacoteService pacoteService;

    private Pacote pacoteTest;
    private Usuario usuarioContratanteTest;

    @BeforeEach
    void setup(){

        pacoteTest = new Pacote();

        pacoteTest.setPorte("Medio");
        pacoteTest.setValor(50.05);
        pacoteTest.setContratanteId(usuarioContratanteTest);
        pacoteTest.setNomeDestinatario("José da Silva");
        pacoteTest.setEnderecoEntrega("Rua ABC, nº 15, Centro, Recife - Pernambuco, Brasil");
        pacoteTest.setCepDestinatario("123456789");
        pacoteTest.setObs(null);
        pacoteTest.setAtivo(true);

    }

    @DisplayName("Deve listar todos os pacotes")
    @Test
    void deveListarTodosPacotes() throws Exception{
        when(pacoteService.listarPacotes()).thenReturn(Arrays.asList());
        var response = pacoteService.listarPacotes();
        assertNotNull(response);
        assertEquals(Arrays.asList(), response);
    }

    @DisplayName("Deve buscar um pacote por ID")
    @Test
    void deveBuscarUmPacotePorId() throws Exception{
        when(pacoteService.buscarPacotePorId(1L)).thenReturn(pacoteTest);
        var response = pacoteController.buscarPacotePorId(1L);
        assertNotNull(response);
        assertEquals(pacoteTest, response);
    }

    @DisplayName("Deve buscar um pacote por cotratante ID")
    @Test
    void deveBuscarPacotePorContratanteId() throws Exception{
        when(pacoteService.buscarPacotePorContratanteId(1L)).thenReturn(Arrays.asList());
        var response = pacoteController.buscarPacotePorContratanteId(1L);
        assertNotNull(response);
        assertEquals(Arrays.asList(), response);
    }

    @DisplayName("Deve salvar um pacote")
    @Test
    void deveSalvarPacote() throws Exception{
        when(pacoteService.salvarPacote(pacoteTest)).thenReturn(pacoteTest);
        var response = pacoteController.salvarPacote(pacoteTest);
        assertNotNull(response);
        assertEquals(pacoteTest, response);
    }

    @DisplayName("Deve editar um pacote")
    @Test
    void deveEditarPacote() throws Exception {
        when(pacoteService.atualizarPacote(1L, pacoteTest)).thenReturn(pacoteTest);
        var response = pacoteController.atualizarPacote(1L, pacoteTest);
        assertNotNull(response);
        assertEquals(pacoteTest, response);
    }

    @DisplayName("Deve deletar um pacote")
    @Test
    void deveDeletarPacote() throws Exception {
        doNothing().when(pacoteService).deletarPacote(1L);
        try {
            assertDoesNotThrow(() -> pacoteController.deletarPacote(1L));
        } catch (Exception e) {
            fail("Exceção: " + e.getMessage());
        }
        verify(pacoteService, times(1)).deletarPacote(anyLong());
        verifyNoMoreInteractions(pacoteService);
    }
}
