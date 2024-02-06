package com.micrsrvc.infr.microservice.infra.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class GreetingController {
    
    @GetMapping
    public String greeting() {
        return new String("Guten Morgen!");
    }
}
