package main

import (
	"fmt"
	"log"
	"mub"
	"net/http"
	"sub"
)

func main() {
	b := 1 != 2
	println(b)

	// AND
	var num int = 7
	c := num > 3 && 3 > 1
	println(c)
	// OR
	d := num > 10 || 0 < num
	println(d)

	LibFunc()

	mub.CallMub()
	sub.SubFunc()

	http.HandleFunc("/hello", helloHandler)
	fmt.Println("Server Start Up......")
	log.Fatal(http.ListenAndServe("localhost:8081", nil))
}
