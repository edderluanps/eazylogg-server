package com.eazylogg.backend;

import com.eazylogg.backend.controllers.PacoteController;
import com.eazylogg.backend.models.Pacote;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.services.PacoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PacoteControllerTest {

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

    @Test
    void salvarPacote(){
        when(pacoteService.salvarPacote(pacoteTest)).thenReturn(pacoteTest);
        var response = assertDoesNotThrow(() -> pacoteController.salvar(pacoteTest));
        assertNotNull(response);
        assertEquals(pacoteTest, response);
        System.out.println("Passou no teste");
    }
}
