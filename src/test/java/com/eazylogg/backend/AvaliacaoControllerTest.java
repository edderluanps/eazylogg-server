package com.eazylogg.backend;

import com.eazylogg.backend.controllers.AvaliacaoController;
import com.eazylogg.backend.models.Avaliacao;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.services.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AvaliacaoControllerTest {

    @InjectMocks
    private AvaliacaoController avaliacaoController;

    @Mock
    private AvaliacaoService avaliacaoService;

    private Avaliacao avaliacaoTest;

    private Usuario usuarioTest;
    private Usuario usuarioAvaliadaorTest;

    @BeforeEach
    void setup(){

        avaliacaoTest = new Avaliacao();
        usuarioTest = new Usuario();
        usuarioAvaliadaorTest = new Usuario();

        avaliacaoTest.setNota(4.5);
        avaliacaoTest.setAvaliador(usuarioTest);
        avaliacaoTest.setAvaliador(usuarioAvaliadaorTest);

    }

    @Test
    void salvarAvaliacao(){
        when(avaliacaoService.salvarAvaliacao(avaliacaoTest)).thenReturn(avaliacaoTest);
        var response = assertDoesNotThrow(() -> avaliacaoService.salvarAvaliacao(avaliacaoTest));
        assertNotNull(response);
        assertEquals(avaliacaoTest, response);
        System.out.println("Passou no teste");
    }
}
