package com.eazylogg.backend.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UsuarioNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, max = 100, message = "Campo deve conter de 5 à 100 caracteres")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOuCnpj;

    private Integer tipoCliente;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String senha;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String pais;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefone1;

    private String telefone2;

    private String telefone3;

    private boolean ativo;
}
