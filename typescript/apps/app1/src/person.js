"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Person = void 0;
class Person {
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
    introduce(e) {
        if (e) {
            e.innerHTML = 'My name is ' + this.name + '. My age is ' + this.age + 'years old. Nice to meet you!';
        }
    }
}
exports.Person = Person;
