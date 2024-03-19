export class Car {

    // Field variable
    modelName: string;
    type: string;
    gasoline: number;
    battery: number;

    //Constructor
    constructor(modelName: string
        , type: string
        , gasoline: number
        , battery: number) {
        this.modelName = modelName;
        this.type = type
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