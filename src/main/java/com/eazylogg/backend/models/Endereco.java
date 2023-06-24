package com.eazylogg.backend.models;

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

    private String estado;

    private String pais;

}
