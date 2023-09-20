package com.eazylogg.backend.models;

import com.eazylogg.backend.models.enums.TipoCliente;
import com.eazylogg.backend.models.enums.TipoPerfil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String descricao;

    private String cpfOuCnpj;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    private Integer tipoCliente;

    private String categoria;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> endereco = new ArrayList<>();

    private String email;

    private String senha;

    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> telefone = new HashSet<>();

    private boolean ativo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "perfis")
    private Set<Integer> perfis = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacao = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "avaliador", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacaoRef = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos = new ArrayList<>();

    public Usuario() {
        addPerfis(TipoPerfil.CLIENTE);
    }

    public Usuario(Long id, String nome, String descricao, String cpfOuCnpj, LocalDate dataNascimento, LocalDate dataCadastro, TipoCliente tipoCliente, String categoria, String email, String senha, boolean ativo) {
        this.id = id;
        addPerfis(TipoPerfil.CLIENTE);
        this.nome = nome;
        this.descricao = descricao;
        this.cpfOuCnpj = cpfOuCnpj;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getCod();
        this.categoria = categoria;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
    }

    public Set<TipoPerfil> getPerfis() {
        return perfis.stream().map(obj -> TipoPerfil.toEnum(obj)).collect(Collectors.toSet());
    }

    public void addPerfis(TipoPerfil perfil) {
        perfis.add(perfil.getCod());
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

}
