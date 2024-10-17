package com.hexagonal.poc.shorturl.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ShortKey {

    private final String identifier;

    public static ShortKey empty() {
        return new ShortKey(null);
    }

    public static ShortKey of(String hash) {
        return new ShortKey(hash);
    }

    public boolean isNew() {
        return null != identifier;
    }

    public String getValue() {
        return identifier;
    }
}
