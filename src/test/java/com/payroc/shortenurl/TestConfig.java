package com.payroc.shortenurl;

import com.payroc.shortenurl.controllers.UrlController;
import com.payroc.shortenurl.repositories.UrlRepository;
import com.payroc.shortenurl.services.UrlService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@TestConfiguration
public class TestConfig {

    @Bean
    public UrlRepository urlRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new UrlRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public UrlService urlService(UrlRepository urlRepository) {
        return new UrlService(urlRepository);
    }

    @Bean
    public UrlController urlController (UrlService urlService) {
        return new UrlController(urlService);
    }
}
