package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BootApp2Controller {

    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/service-2")
    public String getValues() {
        return "Getting Called by Service 2";
    }

    @GetMapping("/service-2-1")
    public String getValueFromService2() {
        String val = restTemplate.getForObject("http://boot-1-app:8080/service-1", String.class);
        return "Service call from service 2 to service 1 --- " + val;
    }
}
