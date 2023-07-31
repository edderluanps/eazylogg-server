package com.eazylogg.backend.config;

import com.eazylogg.backend.models.PgCartao;
import com.eazylogg.backend.models.PgPix;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PgCartao.class);
                objectMapper.registerSubtypes(PgPix.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
