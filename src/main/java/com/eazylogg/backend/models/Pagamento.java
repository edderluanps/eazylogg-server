package com.eazylogg.backend.models;

import com.eazylogg.backend.models.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pagamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class Pagamento implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDate data;

    private Integer status;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "entrega_id")
    @MapsId
    private Entrega entrega;

    public Pagamento(Long id, LocalDate data, PagamentoStatus status) {
        this.id = id;
        this.data = data;
        this.status = (this.status == null) ? null : status.getCod();
    }

    public PagamentoStatus getStatus() {
        return PagamentoStatus.toEnum(status);
    }

    public void setStatus(PagamentoStatus status) {
        this.status = status.getCod();
    }
}
