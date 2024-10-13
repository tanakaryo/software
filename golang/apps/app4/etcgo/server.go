package main

import (
	"fmt"
	"log"
	"net/http"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
	hello := []byte("Hello World!!")
	_, err := w.Write(hello)
	if err != nil {
		log.Fatal(err)
	}
}

func LibFunc() {
	fmt.Println("This is server.go function.")
}
