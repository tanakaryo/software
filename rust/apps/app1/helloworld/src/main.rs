mod protocol;

fn main() {
    let a = protocol::Thing::new();
    println!("Hello, {:?}", a);
    let ddd = protocol::Dog::new();
    let dname: String = ddd.name;
    println!("{}", dname);
}