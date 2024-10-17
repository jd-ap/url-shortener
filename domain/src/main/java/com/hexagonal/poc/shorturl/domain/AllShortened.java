package com.hexagonal.poc.shorturl.domain;

import java.util.Optional;

public interface AllShortened {

    void save(Shortened shortened);

    Optional<Shortened> withKey(String key) throws PersistenceException;

    Optional<Shortened> withResource(String oResource) throws PersistenceException;
}
