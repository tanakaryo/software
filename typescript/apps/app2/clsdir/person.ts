export class Person {
    name: string;
    age: number;
    address: string;

    constructor(name: string, age: number, address: string) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    private sayName(): void {
        console.log("My name is " + this.name + "!");
    }

    public getName(): string {
        this.sayName();
        return this.name;
    }
}