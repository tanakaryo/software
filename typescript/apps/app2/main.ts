import { Person } from './clsdir/person.ts'

const p1 = new Person('takeshi', 14, 'tokyo');
var name:string = p1.getName();

console.log("His name is " + name + ".");