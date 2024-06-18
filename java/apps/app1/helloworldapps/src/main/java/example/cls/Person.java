package example.cls;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person {
    
    private String name;

    private int age;

    private String country;

    public Person(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public void sayHello() {
        System.out.println("Hello, my name is " + this.name + ".");
        System.out.println("I'm " + this.age + " years old.");
        System.out.println("I'm live in " + this.country + ".");
    }
}
