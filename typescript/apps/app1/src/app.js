"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const person_1 = require("./person");
var e = document.getElementById('output');
var firstmeet = new person_1.Person('Hiroshi Fujiwara', 45);
firstmeet.introduce(e);
