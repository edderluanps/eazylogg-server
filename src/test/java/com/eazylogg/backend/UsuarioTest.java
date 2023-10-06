package com.eazylogg.backend;

import com.eazylogg.backend.controllers.UsuarioController;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@DisplayName("Testes - Usuario")
@ExtendWith(MockitoExtension.class)
public class UsuarioTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    private Usuario usuarioTest;

    @BeforeEach
    void setup(){
        LocalDate dataAtual = LocalDate.now();

        usuarioTest = new Usuario();

        usuarioTest.setNome("Fulano da Silva");
        usuarioTest.setEmail("teste@email.com");
        usuarioTest.setSenha("123454560");
        usuarioTest.setCpfOuCnpj("52814581830");
        usuarioTest.setCategoria("Anunciante");
        usuarioTest.setDataNascimento(dataAtual);
        usuarioTest.setAtivo(true);
    }

    @DisplayName("Deve salvar um usuario")
    @Test
    void salvarUsuario() throws Exception{
        when(usuarioService.salvarUsuario(usuarioTest)).thenReturn(usuarioTest);
        var response = assertDoesNotThrow(() -> usuarioService.salvarUsuario(usuarioTest));
        assertNotNull(response);
        assertEquals(usuarioTest, response);
    }

}
