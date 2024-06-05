public class Person {
    private string name;
    private int age;
    private string country;

    public Person(string name, int age, string country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public void sayHello() {
        Console.WriteLine("Hello, my name is " + this.name + ".");
        Console.WriteLine("I'm " + this.age + " years old now.");
        Console.WriteLine("I'm live in " + this.country + ".");
    }
}