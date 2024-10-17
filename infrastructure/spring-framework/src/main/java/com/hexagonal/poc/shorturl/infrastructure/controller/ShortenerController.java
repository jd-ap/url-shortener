package com.hexagonal.poc.shorturl.infrastructure.controller;

import com.hexagonal.poc.shorturl.application.ShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ShortenerController {

    private final ShortenerService shortenerService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    void ping() {
        System.out.println("ping");
    }
}
