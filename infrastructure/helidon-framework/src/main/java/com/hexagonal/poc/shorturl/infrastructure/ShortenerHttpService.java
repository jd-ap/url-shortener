package com.hexagonal.poc.shorturl.infrastructure;

import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;

public class ShortenerHttpService implements HttpService {

    @Override
    public void routing(HttpRules httpRules) {
        httpRules
                .get("/", (req, res) -> res.send());
    }
}
