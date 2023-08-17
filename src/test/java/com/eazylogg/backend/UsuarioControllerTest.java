package com.eazylogg.backend;

import com.eazylogg.backend.controllers.UsuarioController;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

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

    @Test
    void salvarUsuario(){
        when(usuarioService.salvarUsuario(usuarioTest)).thenReturn(usuarioTest);
        var response = assertDoesNotThrow(() -> usuarioService.salvarUsuario(usuarioTest));
        assertNotNull(response);
        assertEquals(usuarioTest, response);
        System.out.println("Passou no teste");
    }

}
