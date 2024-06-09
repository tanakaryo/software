 class Person {
    constructor(name, age, country) {
        this.name = name;
        this.age = age;
        this.country = country;
    }

    sayHello() {
        console.log(`Hello, my name is ${this.name}.`);
        console.log(`I'm ${this.age} years old.`);
        console.log(`I'm live in ${this.country}.`);
    }
}

module.exports = { Person };

