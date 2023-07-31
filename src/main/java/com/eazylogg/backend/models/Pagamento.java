package com.eazylogg.backend.models;

import com.eazylogg.backend.models.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class Pagamento implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private Integer status;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "entrega_id")
    @MapsId
    private Entrega entrega;

    public Pagamento(Long id, PagamentoStatus status, Entrega entrega) {
        this.id = id;
        this.entrega = entrega;
        this.status = (this.status == null) ? null : status.getCod();
    }

    public PagamentoStatus getStatus() {
        return PagamentoStatus.toEnum(status);
    }

    public void setStatus(PagamentoStatus status) {
        this.status = status.getCod();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }
}
