package com.hexagonal.poc.shorturl.infrastructure.controller;

import com.hexagonal.poc.shorturl.application.ShortenerService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class ShortenerController {

    private ShortenerService shortenerService;

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}
