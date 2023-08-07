package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
import com.eazylogg.backend.models.Usuario;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendConfirmationEmail(Entrega entrega);

    void sendEmail(SimpleMailMessage smm);

    void sendNewPasswordEmail(Usuario usuario, String newPassword);

}