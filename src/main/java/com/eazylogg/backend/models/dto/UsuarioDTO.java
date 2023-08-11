package com.eazylogg.backend.models.dto;

import com.eazylogg.backend.models.Usuario;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Campo obrigatório")
    private String nome;

    @NotEmpty(message = "Campo obrigatório")
    @Email(message = "Email inválido")
    @Column(unique=true)
    private String email;

    private String categoria;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        categoria = usuario.getCategoria();
    }
}
