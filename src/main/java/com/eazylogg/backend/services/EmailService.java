package com.eazylogg.backend.services;

import com.eazylogg.backend.models.Entrega;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendConfirmationEmail(Entrega entrega);

    void sendEmail(SimpleMailMessage smm);


}