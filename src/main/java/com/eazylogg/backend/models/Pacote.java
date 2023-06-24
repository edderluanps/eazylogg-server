package com.eazylogg.backend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_pacote")
@Data
public class Pacote implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String porte;

    private Usuario contratanteId;

    private String cpfRecebedor;

    private String obs;

    private boolean ativo;
}
