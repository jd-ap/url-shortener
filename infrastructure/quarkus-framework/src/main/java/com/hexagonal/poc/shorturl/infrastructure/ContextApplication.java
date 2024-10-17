package com.hexagonal.poc.shorturl.infrastructure;

import com.hexagonal.poc.shorturl.application.ShortenerService;
import com.hexagonal.poc.shorturl.application.ShortenerServiceImpl;
import com.hexagonal.poc.shorturl.domain.AllShortened;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.TransactionScoped;

@ApplicationScoped
public class ContextApplication {

    @ApplicationScoped
    public ShortenerService shortenerService(AllShortened allShortened) {
        return ShortenerServiceImpl.newInstance(allShortened);
    }

    @TransactionScoped
    public ShortenerResource shortenerResource(ShortenerService shortenerService) {
        return new ShortenerResource(shortenerService);
    }
}
