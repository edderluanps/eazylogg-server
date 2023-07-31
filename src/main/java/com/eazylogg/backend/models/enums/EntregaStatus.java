package com.eazylogg.backend.models.enums;

public enum EntregaStatus {

    SOLICITADO(1, "ENTREGA_SOLICITADA"),
    RECOLHIDO(2, "PACOTE_RECOLHIDO"),
    ACAMINHO(3, "PACOTE_EM_TRANSITO"),
    ENTREGUE(4, "PACOTE_ENTREGUE");

    private int cod;
    private String descricao;

    private EntregaStatus(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EntregaStatus toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (EntregaStatus status : EntregaStatus.values()) {
            if (cod.equals(status.getCod())) {
                return status;
            }

        }

        throw new IllegalArgumentException("Cód. inválido: " + cod);

    }

}