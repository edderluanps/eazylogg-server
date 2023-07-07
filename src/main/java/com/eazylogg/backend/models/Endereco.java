package com.eazylogg.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_endereco")
@Data
public class Endereco implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String logradouro;

    private String numero;

    private String cep;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String pais;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private boolean ativo;

    public Endereco(Long id, String logradouro, String numero, String cep, String complemento, String bairro, String cidade, String estado, String pais, Usuario usuario, boolean ativo) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.usuario = usuario;
        this.ativo = ativo;
    }
}
