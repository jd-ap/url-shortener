# URL shortener
You are required to create a simple RESTful API that allows users to shorten long URLs. The API should provide endpoints to create, retrieve, update, and delete short URLs. It should also provide statistics on the number of times a short URL has been accessed.
````mermaid
flowchart TD
    As[Client] -->|GET short.com/abc123| Bs(Spring controller)
    Bs -->|redirect original| As
    Ah[Client] -->|GET short.com/abc123| Bh(Helidon controller)
    Bh -->|redirect original| Ah
    Am[Client] -->|GET short.com/abc123| Bm(Micronaut controller)
    Bm -->|redirect original| Am
    Aq[Client] -->|GET short.com/abc123| Bq(Quarkus controller)
    Bq -->|redirect original| Aq
    Bs <--> C(application service)
    Bh <--> C
    Bm <--> C
    Bq <--> C
    C --> D(domain data access)
    D --> E[Database]
````

## Requirements
You should create a RESTful API for a URL shortening service. The API should allow users to perform the following operations:
+ Create a new short URL
+ Retrieve an original URL from a short URL
+ Update an existing short URL
+ Delete an existing short URL
+ Get statistics on the short URL (e.g., number of times accessed)
