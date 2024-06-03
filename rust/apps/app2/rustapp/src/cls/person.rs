#[derive(Debug)]
pub struct Person {
    pub name: String,
    pub age: i8,
    pub country: String
}

impl Person {
    pub fn new(name:String, age: i8, country: String) -> Person {
        Person { name: name
                 , age: age
                 , country: country}
    }
}