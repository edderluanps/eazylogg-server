package com.eazylogg.backend.models;

import com.eazylogg.backend.models.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pg_cartao")
@Data
@JsonTypeName("pgCartao")
public class PgCartao extends Pagamento{

    private int numParcelas;

    public PgCartao(){

    }

    public PgCartao(int numParcelas, Long id, PagamentoStatus status, Entrega entrega) {
        super(id, status, entrega);
        this.numParcelas = numParcelas;
    }

    public int getNumParcelas() {
        return numParcelas;
    }

    public void setNumParcelas(int numParcelas) {
        this.numParcelas = numParcelas;
    }
}
