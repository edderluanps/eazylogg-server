package com.eazylogg.backend.config;

import com.eazylogg.backend.services.EmailService;
import com.eazylogg.backend.services.SMTPEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public EmailService emailService(){
        return new SMTPEmailService();
    }

}