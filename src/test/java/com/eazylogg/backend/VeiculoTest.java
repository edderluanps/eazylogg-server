package com.eazylogg.backend;

import com.eazylogg.backend.controllers.VeiculoController;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.models.Veiculo;
import com.eazylogg.backend.services.VeiculoService;
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

@DisplayName("Testes - veículos")
@ExtendWith(MockitoExtension.class)
public class VeiculoTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoService veiculoService;

    private Veiculo veiculoTest;

    private Usuario usuarioTest;

    @BeforeEach
    void setup(){

        veiculoTest = new Veiculo();
        usuarioTest = new Usuario();

        veiculoTest.setMarca("GM");
        veiculoTest.setModelo("Celta");
        veiculoTest.setAno("2012");
        veiculoTest.setCor("Prata");
        veiculoTest.setPorte("Pequeno");
        veiculoTest.setUsuario(usuarioTest);
        veiculoTest.setAtivo(true);
    }

    @DisplayName("Deve listar todos os veículos")
    @Test
    void deveListarTodosPacotes() throws Exception{
        when(veiculoService.listarVeiculos()).thenReturn(Arrays.asList());
        var response = veiculoService.listarVeiculos();
        assertNotNull(response);
        assertEquals(Arrays.asList(), response);
    }

    @DisplayName("Deve buscar um veículo por ID")
    @Test
    void deveBuscarUmPacotePorId() throws Exception{
        when(veiculoService.buscarVeiculoPorId(1L)).thenReturn(veiculoTest);
        var response = veiculoController.buscarVeiculoPorId(1L);
        assertNotNull(response);
        assertEquals(veiculoTest, response);
    }

    @DisplayName("Deve salvar um veículo")
    @Test
    void salvarVeiculo(){
        when(veiculoService.salvarVeiculo(veiculoTest)).thenReturn(veiculoTest);
        var response = veiculoController.salvarVeiculo(veiculoTest);
        assertNotNull(response);
        assertEquals(veiculoTest, response);
    }

    @DisplayName("Deve editar um veículo")
    @Test
    void deveEditarPacote() throws Exception {
        when(veiculoService.atualizarVeiculo(1L, veiculoTest)).thenReturn(veiculoTest);
        var response = veiculoController.atualizarVeiculo(1L, veiculoTest);
        assertNotNull(response);
        assertEquals(veiculoTest, response);
    }

    @DisplayName("Deve deletar um veículo")
    @Test
    void deveDeletarVeiculo() throws Exception {
        doNothing().when(veiculoService).deletarVeiculo(1L);
        try {
            assertDoesNotThrow(() -> veiculoController.deletarVeiculo(1L));
        } catch (Exception e) {
            fail("Exceção: " + e.getMessage());
        }
        verify(veiculoService, times(1)).deletarVeiculo(anyLong());
        verifyNoMoreInteractions(veiculoController);
    }
}
