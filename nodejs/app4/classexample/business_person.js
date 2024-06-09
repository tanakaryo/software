const { Person } = require("./iface/person");

module.exports = class BusinessPerson extends Person {
    constructor(name, age, country, job) {
        super(name, age, country);
        this.job = job
    }

    sayHello() {
        super.sayHello();
        console.log(`I'm work as ${this.job}.`);
    }
}
