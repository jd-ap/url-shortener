package com.hexagonal.poc.shorturl.application;

import com.hexagonal.poc.shorturl.application.control.Result;
import com.hexagonal.poc.shorturl.domain.Shortened;

public sealed interface ShortenerService permits ShortenerServiceImpl {

    Result<Shortened> createFrom(String originalResource);

    Result<Shortened> findByKey(String key);

    Result<Shortened> findByOrigin(String oResource);

}
