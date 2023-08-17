package com.eazylogg.backend;

import com.eazylogg.backend.controllers.VeiculoController;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.models.Veiculo;
import com.eazylogg.backend.services.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTest {

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

    @Test
    void salvarVeiculo(){
        when(veiculoService.salvarVeiculo(veiculoTest)).thenReturn(veiculoTest);
        var response = assertDoesNotThrow(() -> veiculoController.salvar(veiculoTest));
        assertNotNull(response);
        assertEquals(veiculoTest, response);
        System.out.println("Passou no teste");
    }
}
