package com.eazylogg.backend;

import com.eazylogg.backend.controllers.AvaliacaoController;
import com.eazylogg.backend.models.Avaliacao;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.services.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Testes - Avaliação")
@ExtendWith(MockitoExtension.class)
public class AvaliacaoTest {

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

    @DisplayName("Deve listar todas as avaliações")
    @Test
    void deveListarTodasAvaliacaoes() throws Exception{
        when(avaliacaoService.listaAvaliacao()).thenReturn(Arrays.asList());
        var response = avaliacaoController.listarAvaliacoes();
        assertNotNull(response);
        assertEquals(Arrays.asList(), response);
    }

    @DisplayName("Deve listar todas as avaliações por ID")
    @Test
    void deveListarTodasAvaliacaoesPorId() throws Exception{
        when(avaliacaoService.buscarAvaliacaoPorId(1L)).thenReturn(avaliacaoTest);
        var response = avaliacaoController.buscarAvaliacaoPorId(1L);
        assertNotNull(response);
        assertEquals(avaliacaoTest, response);
    }

    @DisplayName("Deve retornar a média de avaliações de um usuário")
    @Test
    void deveRetornarMediaDeAvaliacaoesDeUsuario() throws Exception{
        when(avaliacaoService.buscarMediaAvaliacaoPorUsuarioId(1L)).thenReturn(5.0);
        var response = avaliacaoController.buscarAvaliacaoPorId(1L);
        assertNotNull(response);
        assertEquals(5.0, response);
    }

    @DisplayName("Deve salvar uma avaliação")
    @Test
    void deveSalvarUmaAvaliacao() throws Exception{
        when(avaliacaoService.salvarAvaliacao(avaliacaoTest)).thenReturn(avaliacaoTest);
        var response = avaliacaoController.salvarAvaliacao(avaliacaoTest);
        assertNotNull(response);
        assertEquals(avaliacaoTest, response);
    }

    @DisplayName("Deve atualizar uma avaliação")
    @Test
    void deveAtualizarUmaAvaliacao() throws Exception{
        when(avaliacaoService.atualizarAvaliacao(1L, avaliacaoTest)).thenReturn(avaliacaoTest);
        var response = avaliacaoController.atualizarAvaliacao(1L, avaliacaoTest);
        assertEquals(avaliacaoTest, response);
    }

}
