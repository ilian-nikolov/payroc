package com.payroc.shortenurl;

import com.payroc.shortenurl.repositories.UserRepository;
import com.payroc.shortenurl.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new UserRepository(namedParameterJdbcTemplate);
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

}