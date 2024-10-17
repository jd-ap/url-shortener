package com.hexagonal.poc.shorturl.infrastructure;

import com.hexagonal.poc.shorturl.application.ShortenerService;
import com.hexagonal.poc.shorturl.application.ShortenerServiceImpl;
import com.hexagonal.poc.shorturl.infrastructure.repository.ShortenUrlRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ShortenerService shortenerService(ShortenUrlRepository shortenUrlRepository) {
        return ShortenerServiceImpl.newInstance(shortenUrlRepository);
    }
}
