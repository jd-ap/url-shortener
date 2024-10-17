package com.hexagonal.poc.shorturl.infrastructure.entity;

import com.hexagonal.poc.shorturl.domain.ShortKey;
import com.hexagonal.poc.shorturl.domain.Shortened;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "shorten_urls")
@NamedQuery(name = "ShortenUrl.findByKey", query = "FROM ShortenUrl s WHERE s.key = :key")
@NamedQuery(name = "ShortenUrl.findByResource", query = "FROM ShortenUrl s WHERE s.resource = :resource")
@NamedQuery(name = "ShortenUrl.saveNewShort", query = "INSERT ShortenUrl s VALUE (:#{#s.key}, :#{#s.resource})")
@NamedQuery(name = "ShortenUrl.updateShort", query = "UPDATE key = :#{#s.key}, resource = :#{#s.resource} FROM ShortenUrl s ")
@NamedQuery(name = "ShortenUrl.revokeShort", query = "DELETE FROM ShortenUrl s WHERE s.key = :key")
@NamedQuery(name = "ShortenUrl.upStatisticsShort", query = """
            UPDATE s.numberTimesAccessed = s.numberTimesAccessed + 1, lastVisited = now()
            FROM ShortenUrl s WHERE s.key = :key
            """)
public class ShortenUrl {
    @Id
    private String key;

    private String resource;

    private Integer numberTimesAccessed;

    private LocalDateTime lastVisited;

    // ToDo: agregar atributos que se recuperan del resource para facilitar más filtros o un método que lo transforme a URI para fragmentarlo

    public static ShortenUrl from(Shortened shortened) {
        return ShortenUrl.builder()
                .key(shortened.getKey().get())
                .resource(shortened.getResource().toString())
                .build();
    }

    public Shortened toShortened() {
        try (var shortened = Shortened.of(resource).withKey(ShortKey.of(key))) {
            return shortened;
        } catch (Exception exception) {
            return null; //ToDo: Mejorar
        }
    }

}
