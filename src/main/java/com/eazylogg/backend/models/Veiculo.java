package com.eazylogg.backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_veiculo")
@Data
public class Veiculo implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String porte;

    private String placa;

    private String marca;

    private String modelo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate ano;

    private String cor;

    private Usuario usuarioId;

    private boolean ativo;
}
