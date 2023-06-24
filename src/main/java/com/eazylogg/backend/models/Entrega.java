package com.eazylogg.backend.models;

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

    private Usuario entregadorId;

    private Pacote pacoteId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataColeta;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEntrega;

    private Endereco enderecoColetaId;

    private Endereco enderecoEntregaId;

    private String status;

    private Double valor;

    private String pagamento;

    private String obs;

    private boolean ativo;
}
