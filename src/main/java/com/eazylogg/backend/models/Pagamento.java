package com.eazylogg.backend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pagamento")
@Data
public class Pagamento implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDate data;

    private String status;

    private String chavePix;

}
