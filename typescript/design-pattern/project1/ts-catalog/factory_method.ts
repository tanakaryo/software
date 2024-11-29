
abstract class Creator {
    
    public abstract factoryMethod(): Product;

    public someOperation(): string {
        const product = this.factoryMethod();

        return `Creator: The same creator's code has just worked with ${product.operation()}`;
    }
}

class ConcreteCreator1 extends Creator {
    public factoryMethod(): Product {
        return new ConcreteProduct1();
    }
}

class ConcreteCreator2 extends Creator {
    public factoryMethod(): Product {
        return new ConcreteProduct2();
    }
}

// Interface
interface Product {
    operation(): string;
}

// ConcreteClass of Product
class ConcreteProduct1 implements Product {
    public operation(): string {
        return 'This is first your product.';
    }
}

// ConcreteClass of Product
class ConcreteProduct2 implements Product {
    public operation(): string {
        return 'This is 2nd your product.'
    }
}

function clientCodeFactoryMethod(creator: Creator) {
    console.log('Client: I\'m not aware of the creator\'s class, but it still works.');
    console.log(creator.someOperation());
}

console.log('App: Launched with the ConcreteCreator1.');
clientCodeFactoryMethod(new ConcreteCreator1());
console.log('');

console.log('App: Launched with the ConcreteCreator2.');
clientCodeFactoryMethod(new ConcreteCreator2());