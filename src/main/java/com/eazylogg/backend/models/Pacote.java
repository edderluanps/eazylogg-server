package com.eazylogg.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pacote")
@Data
public class Pacote implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String porte;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contratante_id")
    private Usuario contratanteId;

    private String nomeDestinatario;

    private String logradouroDestinatario;

    private String numeroDestinatario;

    private String cepDestinatario;

    private String complementoDestinatario;

    private String bairroDestinatario;

    private String cidadeDestinatario;

    private String estadoDestinatario;

    private String paisDestinatario;

    private String obs;

    private boolean ativo;

    public Usuario getContratanteId() {
        return contratanteId;
    }

    public void setContratanteId(Usuario contratanteId) {
        this.contratanteId = contratanteId;
    }

}
