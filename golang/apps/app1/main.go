package main

import (
	"fmt"
	"hellogo/cls"
)

func main() {
	fmt.Println("Hello,Golang!")

	p := cls.NewInstance("satoshi", 14, "Japan")
	p.SayHello()
}
