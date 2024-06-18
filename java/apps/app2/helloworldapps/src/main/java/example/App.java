package example;

import example.cls.BusinessPerson;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        BusinessPerson p = new BusinessPerson("satoshi", 14, "Japan");
        p.sayHello();
    }
}
