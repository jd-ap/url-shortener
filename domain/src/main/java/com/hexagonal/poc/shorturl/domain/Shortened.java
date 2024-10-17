package com.hexagonal.poc.shorturl.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URI;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Shortened implements AutoCloseable {

    private ShortKey key;

    private final URI resource;

    public static Shortened of(String originalResource) throws InvalidResourceException {
        try {
            var resource = URI.create(originalResource);

            validateResource(resource);

            return new Shortened(ShortKey.empty(), resource);
        } catch (NullPointerException unused) {
            throw InvalidResourceException.valueRequired();
        }
    }

    public Shortened withKey(ShortKey key) {
        this.key = key;
        return this;
    }

    private static void validateResource(URI resource) throws InvalidResourceException {
        if (null == resource || null == resource.toString() || resource.toString().isBlank())
            throw InvalidResourceException.valueRequired();

        if (!resource.isAbsolute() || null == resource.getScheme() || resource.getScheme().isBlank())
            throw InvalidResourceException.valueMalformed();

    }

    public Optional<String> getKey() {
        return Optional.of(key.getValue());
    }

    @Override
    public void close() throws Exception {
        // implementation not yet required
    }

}
