package com.eazylogg.backend.models;

import com.eazylogg.backend.models.enums.PagamentoStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pg_pix")
@Data
@JsonTypeName("pgPix")
public class PgPix extends Pagamento{

    private String chave;

    public PgPix(){

    }

    public PgPix(String chave, Long id, PagamentoStatus status, Entrega entrega) {
        super(id, status, entrega);
        this.chave = chave;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }
}