package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("$default.sender")
    private String sender;

    @Override
    public void sendConfirmationEmail(Entrega entrega){
        SimpleMailMessage smm = simpleMailMessageFromPedido(entrega);
        sendEmail(smm);
    }

    protected SimpleMailMessage simpleMailMessageFromPedido(Entrega entrega) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(entrega.getEnderecoEntregaId().getUsuario().getEmail());
        smm.setFrom(sender);
        smm.setSubject("Entrega a caminho: Cód: " + entrega.getId());
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText(entrega.toString());
        return smm;
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPassword) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(usuario.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPassword);
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(Usuario usuario, String newPassword) {
        SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPassword);
        sendEmail(sm);
    }

}