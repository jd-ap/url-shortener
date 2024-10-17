package com.hexagonal.poc.shorturl.infrastructure;

import com.hexagonal.poc.shorturl.application.ShortenerService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShortenerResource {

    private final ShortenerService shortenerService;

    @GET
    @Path("")
    void ping() {
        System.out.println("ping");
    }
}
