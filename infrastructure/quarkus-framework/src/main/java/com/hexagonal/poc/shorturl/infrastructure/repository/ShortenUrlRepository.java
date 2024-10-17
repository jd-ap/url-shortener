package com.hexagonal.poc.shorturl.infrastructure.repository;

import com.hexagonal.poc.shorturl.domain.AllShortened;
import com.hexagonal.poc.shorturl.domain.PersistenceException;
import com.hexagonal.poc.shorturl.domain.Shortened;
import com.hexagonal.poc.shorturl.infrastructure.entity.ShortenUrl;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ShortenUrlRepository implements AllShortened, PanacheRepository<ShortenUrl> {

    @Override
    public void save(Shortened shortened) {
        persist(ShortenUrl.from(shortened));
    }

    @Override
    public Optional<Shortened> withKey(String key) throws PersistenceException {
        return find("#ShortenUrl.findByKey", Parameters.with("key", key).map())
                .<ShortenUrl>singleResultOptional()
                .map(ShortenUrl::toShortened);
    }

    @Override
    public Optional<Shortened> withResource(String oResource) throws PersistenceException {
        return find("#ShortenUrl.findByResource", Parameters.with("resource", oResource).map())
                .<ShortenUrl>singleResultOptional()
                .map(ShortenUrl::toShortened);
    }

}
