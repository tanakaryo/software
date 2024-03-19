export class Person {
    constructor(private name:string, private age:number){}

    public introduce(e : HTMLElement | null) : void {
        if (e) {
            e.innerHTML = 'My name is ' + this.name + '. My age is ' + this.age + 'years old. Nice to meet you!'
        }
    }
}