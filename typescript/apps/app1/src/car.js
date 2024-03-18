"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Car = void 0;
class Car {
    //Constructor
    constructor(modelName, type, gasoline, battery) {
        this.modelName = modelName;
        this.type = type;
        this.gasoline = gasoline;
        this.battery = battery;
    }
    // Method
    run() {
        if (this.gasoline == 0) {
            console.log("gasoline is empty.");
        }
        console.log("Car is running");
        this.gasoline = this.gasoline - 1;
    }
}
exports.Car = Car;
