class Singleton {
    static #instance: Singleton;
    static #value: string;

    private constructor() {}

    public static get instance(): Singleton {
        if (!Singleton.#instance) {
            Singleton.#instance = new Singleton();
        }

        return Singleton.#instance;
    }

    public setHello(value: string) {
        Singleton.#value = value;
    }

    public sayHello(): string {
        return Singleton.#value;
    }
}

function clientCodeSingleton() {
    const s1 = Singleton.instance;
    const s2 = Singleton.instance;

    s1.setHello("Guten morgen");

    if (s1 === s2) {
        console.log(
            'Singleton works, both variables contain the same instance.'
        );
        console.log('s1 is ' + s1.sayHello());
        console.log('s2 is ' + s2.sayHello());
    } else {
        console.log('Singleton failed, variables contain different instances.');
    }
}

clientCodeSingleton();