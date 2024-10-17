package com.hexagonal.poc.shorturl.infrastructure.repository;

import com.hexagonal.poc.shorturl.domain.AllShortened;
import com.hexagonal.poc.shorturl.domain.PersistenceException;
import com.hexagonal.poc.shorturl.domain.Shortened;
import com.hexagonal.poc.shorturl.infrastructure.entity.ShortenUrl;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ShortenUrlRepository implements AllShortened {

    private final EntityManager entityManager;

    ShortenUrlRepository(JpaContext context) {
        this.entityManager = context.getEntityManagerByManagedType(ShortenUrl.class);
    }

    @Override
    @Transactional
    public void save(Shortened shortened) {
        int urlCreated = entityManager.createNamedQuery("saveNewShort")
                .executeUpdate();
        if (1 != urlCreated) {
            // ToDo: Mejorar
            throw new RuntimeException("ToDo: solo debe crear uno, en otro caso rollback");
        }
    }

    @Override
    public Optional<Shortened> withKey(String key) throws PersistenceException {
        return Optional.ofNullable(
                entityManager.createQuery("findByKey", ShortenUrl.class)
                        .setParameter("key", key)
                        .getSingleResult()
        ).map(ShortenUrl::toShortened);
    }

    @Override
    public Optional<Shortened> withResource(String oResource) throws PersistenceException {
        return Optional.ofNullable(
                entityManager.createNamedQuery("findByResource", ShortenUrl.class)
                        .setParameter("resource", oResource)
                        .getSingleResult()
        ).map(ShortenUrl::toShortened);
    }
}
