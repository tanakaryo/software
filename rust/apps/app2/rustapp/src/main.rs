mod cls;

fn main() {
    println!("Hello, world!");
    let p1 = cls::Person::new("satoshi".to_string(), 14, "Japan".to_string());

    println!("{}", p1.name)
}
