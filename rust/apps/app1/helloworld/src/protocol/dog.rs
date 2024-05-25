#[derive(Debug)]
pub struct  Dog {
    pub name: String
}

impl Dog {
    pub fn new() -> Dog {
        Dog { name: "dog".to_string() }
    }
}