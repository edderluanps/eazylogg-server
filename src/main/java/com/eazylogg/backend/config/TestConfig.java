package com.eazylogg.backend.config;

import com.eazylogg.backend.services.EmailService;
import com.eazylogg.backend.services.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}