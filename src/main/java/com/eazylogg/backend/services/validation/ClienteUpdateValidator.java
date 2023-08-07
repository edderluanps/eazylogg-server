package com.eazylogg.backend.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.eazylogg.backend.controllers.exceptions.FieldMessage;
import com.eazylogg.backend.models.Usuario;
import com.eazylogg.backend.models.dto.UsuarioDTO;
import com.eazylogg.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, UsuarioDTO>{

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void initializable(ClienteUpdate cli){

    }

    @Override
    public boolean isValid(UsuarioDTO usuarioDTO, ConstraintValidatorContext context){

        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Usuario usuario = usuarioRepository.findByEmail(usuarioDTO.getEmail());

        if(usuario != null && !usuario.getId().equals(uriId)){
            list.add(new FieldMessage("email", "email já existente"));
        }

        for (FieldMessage e : list){

            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName());

        }
        return list.isEmpty();
    }

}