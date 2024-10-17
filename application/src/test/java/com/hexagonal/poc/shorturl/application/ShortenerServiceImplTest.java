package com.hexagonal.poc.shorturl.application;

import com.hexagonal.poc.shorturl.domain.AllShortened;
import com.hexagonal.poc.shorturl.domain.InvalidResourceException;
import com.hexagonal.poc.shorturl.domain.PersistenceException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShortenerServiceImplTest {

    @Test
    @SneakyThrows
    void doCreateFrom_returningANewInstance() {
        AllShortened allShortened = mock(AllShortened.class);
        ShortenerService shortenerService = ShortenerServiceImpl.newInstance(allShortened);
        String original = "file:///target/aFile.ext";
        String hash = "7e085267";

        doNothing().when(allShortened).save(any());

        var result = shortenerService.createFrom(original);

        assertTrue(result.isSuccess());
        assertEquals(original, result.get().getResource().toString());
        assertTrue(result.get().getKey().isPresent());
        assertEquals(hash, result.get().getKey().get());

        verify(allShortened, times(1)).save(result.get());
    }

    @Test
    @SneakyThrows
    void doCreateFrom_throwException() {
        AllShortened allShortened = spy(AllShortened.class);
        ShortenerService shortenerService = ShortenerServiceImpl.newInstance(allShortened);

        assertTrue(shortenerService.createFrom(null).isFailure());
        assertThrows(InvalidResourceException.class, () -> shortenerService.createFrom(null).get());
        assertTrue(shortenerService.createFrom("no-valid-uri").isFailure());
        assertThrows(InvalidResourceException.class, () -> shortenerService.createFrom("no-valid-uri").get());

        //doThrow(new PersistenceException()).when(allShortened).save(any());
        //assertThrows(PersistenceException.class, () -> shortenerService.createFrom("file:///target/aFile.ext").get());
    }
}