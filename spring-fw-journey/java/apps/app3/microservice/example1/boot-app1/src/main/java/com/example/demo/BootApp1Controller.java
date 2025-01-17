package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BootApp1Controller {

    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/service-1")
    public String getValues() {
        return "Getting Called by Service 1";
    }

    @GetMapping("/service-1-2")
    public String getValueFromService2() {
        String val = restTemplate.getForObject("http://boot-2-app:8080/service-2", String.class);
        return "Service call from service 1 to service 2 --- " + val;
    }
}
