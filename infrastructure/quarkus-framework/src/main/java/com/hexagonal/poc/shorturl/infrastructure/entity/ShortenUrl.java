package com.hexagonal.poc.shorturl.infrastructure.entity;

import com.hexagonal.poc.shorturl.domain.AllShortened;
import com.hexagonal.poc.shorturl.domain.PersistenceException;
import com.hexagonal.poc.shorturl.domain.ShortKey;
import com.hexagonal.poc.shorturl.domain.Shortened;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Builder
@Entity
@Table(name = "SHORTEN_URLS")
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
    public String key;

    public String resource;

    public Integer numberTimesAccessed;

    public LocalDateTime lastVisited;

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
