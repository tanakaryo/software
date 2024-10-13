package com.example.ms;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SVARestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from SVA server.";
    }
}
