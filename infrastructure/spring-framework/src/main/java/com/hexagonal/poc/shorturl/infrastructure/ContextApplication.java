package com.hexagonal.poc.shorturl.infrastructure;

import com.hexagonal.poc.shorturl.application.ShortenerService;
import com.hexagonal.poc.shorturl.application.ShortenerServiceImpl;
import com.hexagonal.poc.shorturl.infrastructure.repository.ShortenUrlRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextApplication {

    @Bean
    public ShortenerService shortenerService(ShortenUrlRepository shortenUrlRepository) {
        return ShortenerServiceImpl.newInstance(shortenUrlRepository);
    }
}
