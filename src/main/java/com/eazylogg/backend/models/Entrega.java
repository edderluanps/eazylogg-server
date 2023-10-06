package com.eazylogg.backend.models;

import com.eazylogg.backend.models.enums.EntregaStatus;
import com.eazylogg.backend.models.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_entrega")
@Data
public class Entrega implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entregador_id")
    private Usuario entregadorId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pacote_id")
    private Pacote pacoteId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataColeta;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEntrega;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "endereco_coleta_id")
    private Endereco enderecoColetaId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "entrega")
    private Pagamento pagamento;

    private Integer status;

    private Double valor;

    private Double desconto;

    private String obs;

    private String codRastreamento;

    private boolean ativo;

    public Entrega(){
    }

    public Entrega(Long id, Usuario entregadorId, Pacote pacoteId, LocalDate dataColeta, LocalDate dataEntrega, Endereco enderecoColetaId, Pagamento pagamento, EntregaStatus status, Double valor, Double desconto, String obs, String codRastreamento, boolean ativo) {
        this.id = id;
        this.entregadorId = entregadorId;
        this.pacoteId = pacoteId;
        this.dataColeta = dataColeta;
        this.dataEntrega = dataEntrega;
        this.enderecoColetaId = enderecoColetaId;
        this.pagamento = pagamento;
        this.status = (this.status == null) ? null : status.getCod();
        this.valor = valor;
        this.desconto = desconto;
        this.obs = obs;
        this.codRastreamento = codRastreamento;
        this.ativo = ativo;
    }

    public double getValorTotal() {
        double soma = 0.0;
        soma = valor - desconto;
        return soma;
    }

    public EntregaStatus getStatus() {
        return EntregaStatus.toEnum(status);
    }

    public void setStatus(EntregaStatus status) {
        this.status = status.getCod();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getEntregadorId() {
        return entregadorId;
    }

    public void setEntregadorId(Usuario entregadorId) {
        this.entregadorId = entregadorId;
    }

    public Pacote getPacoteId() {
        return pacoteId;
    }

    public void setPacoteId(Pacote pacoteId) {
        this.pacoteId = pacoteId;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Endereco getEnderecoColetaId() {
        return enderecoColetaId;
    }

    public void setEnderecoColetaId(Endereco enderecoColetaId) {
        this.enderecoColetaId = enderecoColetaId;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getCodRastreamento() {
        return codRastreamento;
    }

    public void setCodRastreamento(String codRastreamento) {
        this.codRastreamento = codRastreamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("Entrega nº: ");
        sb.append(getId());

        sb.append("Data de coleta: ");
        sb.append(getDataColeta());

        sb.append("Data estimada de entrega: ");
        sb.append(getDataEntrega());

        return toString();
    }
}
