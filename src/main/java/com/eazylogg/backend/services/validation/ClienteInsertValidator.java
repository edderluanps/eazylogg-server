package com.eazylogg.backend.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.eazylogg.backend.controllers.exceptions.FieldMessage;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.models.dto.UsuarioNewDTO;
import com.eazylogg.backend.models.enums.TipoCliente;
import com.eazylogg.backend.repositories.UsuarioRepository;
import com.eazylogg.backend.services.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, UsuarioNewDTO> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void initialize(ClienteInsert cli) {
    }

    @Override
    public boolean isValid(UsuarioNewDTO usuarioNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (usuarioNewDTO.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(usuarioNewDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (usuarioNewDTO.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(usuarioNewDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Usuario usuario = usuarioRepository.findByEmail(usuarioNewDTO.getEmail());
        if (usuario != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}