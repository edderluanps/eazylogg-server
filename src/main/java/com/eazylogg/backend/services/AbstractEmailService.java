package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
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

}