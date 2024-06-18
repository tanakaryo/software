package example.cls;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessPerson implements Person {

    private String name;
    private int age;
    private String country;

    public BusinessPerson(String name, int age, String country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    @Override
    public void sayHello() {
        System.out.println("My name is " + this.name + ".");
        System.out.println("I'm " + this.age + " years old.");
        System.out.println("I'm live in " + this.country + ".");
    }
    
}
