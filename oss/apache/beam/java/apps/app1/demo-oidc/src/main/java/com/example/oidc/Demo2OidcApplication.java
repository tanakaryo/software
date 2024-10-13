package com.example.oidc;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Demo2OidcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo2OidcApplication.class, args);
    }

    @GetMapping(path="/list")
    public List<String> getNames() {
        return Arrays.asList("Mazda", "Matz", "Zion");
    }
}
