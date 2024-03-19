import { Car } from "./car";
import { hello, introduce, angry } from "./constants";
import { doSay } from "./func";
import { Apple } from "./apple";

var vehicle = new Car("トヨタのセダン","セダンタイプ", 50, 100);
vehicle.run();

console.log(hello);
console.log(introduce);
console.log(angry);

doSay("Typescript is static typical programming-language.")

var apple = new Apple("ourin", "blue apple", "spring", 180);
apple.eat();