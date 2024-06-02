package cls

import (
	"fmt"
	"strconv"
)

type Person struct {
	name    string
	age     int
	country string
}

func NewInstance(name string, age int, country string) *Person {
	p := new(Person)
	p.name = name
	p.age = age
	p.country = country

	return p
}

func (p Person) SayHello() {
	fmt.Println("Who am I?")
	fmt.Println("My name is" + p.name + ".")
	fmt.Println("I'm " + strconv.Itoa(p.age) + " years old.")
	fmt.Println("I'm live in " + p.country + ".")
}
