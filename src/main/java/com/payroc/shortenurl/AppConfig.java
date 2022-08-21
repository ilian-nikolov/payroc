package com.payroc.shortenurl;

import com.payroc.shortenurl.repositories.UrlRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class AppConfig {
    @Bean
    public UrlRepository urlRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new UrlRepository(namedParameterJdbcTemplate);
    }

}