package main

import "fmt"

const john_name string = "john"
const (
	sara_age     int    = 15
	jeff_age     int    = 24
	jeff_address string = "newyork"
)

func main() {
	fmt.Println(john_name)
	fmt.Println(sara_age)
	fmt.Println(jeff_age)
	fmt.Println(jeff_address)
}
