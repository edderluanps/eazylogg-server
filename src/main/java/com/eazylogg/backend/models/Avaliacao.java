package com.eazylogg.backend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_avaliacao")
@Data
public class Avaliacao implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Double nota;

    private Usuario usuarioId;

    private Usuario usuarioRef;

}
