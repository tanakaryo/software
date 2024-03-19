import { Fruits } from "./fruits";

export class Apple implements Fruits {
    name: string;
    type: string;
    season: string;
    price: number;

    constructor(name: string, type: string, season: string, price: number) {
        this.name = name;
        this.type = type;
        this.season = season;
        this.price = price;
    }

    public eat() {
        console.log("It's delicious!");
    }

}