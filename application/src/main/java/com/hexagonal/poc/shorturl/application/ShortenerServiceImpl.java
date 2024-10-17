package com.hexagonal.poc.shorturl.application;

import com.hexagonal.poc.shorturl.application.control.Result;
import com.hexagonal.poc.shorturl.domain.AllShortened;
import com.hexagonal.poc.shorturl.domain.ShortKey;
import com.hexagonal.poc.shorturl.domain.Shortened;
import dev.mccue.guava.hash.Hashing;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor(staticName = "newInstance")
@Log4j2
public final class ShortenerServiceImpl implements ShortenerService {
    private final AllShortened allShortened;

    @Override
    public Result<Shortened> createFrom(String originalResource) {
        return Result.ofNullable(() -> {
            try (var shortened = Shortened.of(originalResource)) {
                String hash = Hashing.adler32().hashString(originalResource, StandardCharsets.UTF_8).toString();
                shortened.withKey(ShortKey.of(hash));

                allShortened.save(shortened);

                return shortened;
            }
        });
    }

    @Override
    public Result<Shortened> findByKey(String key) {
        return Result.of(() -> allShortened.withKey(key));
    }

    @Override
    public Result<Shortened> findByOrigin(String oResource) {
        return Result.of(() -> allShortened.withResource(oResource));
    }
}
