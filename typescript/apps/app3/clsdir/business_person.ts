import { Person } from './person.ts';

export class BusinessPerson implements Person {
    private name: string;
    private age: number;
    protected address: string;
    private country: string;
    private job: string;


    public constructor(name:string, age:number,
        address:string, country:string, job:string) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.country = country;
        this.job = job;
    }

    public introduce(): void {
        console.log("Nice to meet you.");
        console.log("My name is " + this.name +  ". I'm " + this.age + " years old.");
        console.log("I'm now live in " + this.country + ", at " + this.address + ". ");
        console.log("I'm work as " + this.job);
    } 

    public getName(): string {
        return this.name;
    }
    public getAge(): number {
        return this.age;
    }
    public getAddress(): string {
        return this.address;
    }
    public getCountry(): string {
        return this.country;
    }
}