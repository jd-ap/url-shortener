package com.hexagonal.poc.shorturl.domain;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShortenedTest {

    @Test
    @SneakyThrows
    void doOf_returningANewInstance() {
        String origin = "file:///target/aFile.ext";

        var shortened = Shortened.of(origin);

        assertTrue(shortened.getKey().isEmpty());

        assertEquals("file", shortened.getResource().getScheme());
        assertEquals("/target/aFile.ext", shortened.getResource().getPath());
        assertEquals(origin, shortened.getResource().toString());
    }

    @Test
    @SneakyThrows
    void doOf_throwInvalidResourceException() {
        assertThrows(InvalidResourceException.class, () -> Shortened.of(null));
        assertThrows(InvalidResourceException.class, () -> Shortened.of("no-valid-uri"));
    }
}