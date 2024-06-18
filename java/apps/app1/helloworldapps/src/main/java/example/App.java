package example;

import example.cls.Person;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        Person p = new Person("satoshi", 14, "Japan");
        p.sayHello();
    }
}
