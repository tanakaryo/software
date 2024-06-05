public class BusinessPerson : Person {
    private string job {set; get;}
    private string name { get; set; }
    private int age { get; set; }
    private string country { get; set; }

    public BusinessPerson(string name, int age, string country
    , string job) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.job = job;
    }

    public void sayHello() {
        Console.WriteLine("Hello, my name is " + this.name + "." );
        Console.WriteLine("I'm " + this.age + " years old.");
        Console.WriteLine("I'm work as " + this.job + " in " + this.country + " now.");
    }
}