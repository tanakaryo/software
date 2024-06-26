package com.example.rest.api.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.api.model.Person;

@RestController
@RequestMapping(value="/api/person")
public class RestSvcPersonController {
    
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable int id) {
        // TODO 後ほどcoreサービスを作成する
        Person p = new Person();
        p.setId(1);
        p.setName("Satoshi Tajima");
        p.setAge(14);
        p.setCountry("Japan");
        return p;
    }
}
