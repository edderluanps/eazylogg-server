package com.eazylogg.backend.models;

import com.eazylogg.backend.models.enums.EntregaStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    private boolean ativo;

    public Entrega(){
    }

    public Entrega(Long id, Usuario entregadorId, Pacote pacoteId, LocalDate dataColeta, LocalDate dataEntrega, Endereco enderecoColetaId, Pagamento pagamento, EntregaStatus status, Double valor, Double desconto, String obs, boolean ativo) {
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
