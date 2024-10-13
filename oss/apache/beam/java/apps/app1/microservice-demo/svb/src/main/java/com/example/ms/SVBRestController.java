package com.example.ms;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;


@RestController
public class SVBRestController {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public SVBRestController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }

    @GetMapping("helloEureka")
    public String greeting() {
        ServiceInstance serviceInstance = discoveryClient.getInstances("sva").get(0);
        String svaResponse = restClient.get()
        .uri(serviceInstance.getUri() + "/hello")
        .retrieve().body(String.class);
        return svaResponse; 
    }
}
