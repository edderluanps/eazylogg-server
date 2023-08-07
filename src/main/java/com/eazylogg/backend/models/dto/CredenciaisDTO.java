package com.eazylogg.backend.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CredenciaisDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private String email;
    private String senha;

}