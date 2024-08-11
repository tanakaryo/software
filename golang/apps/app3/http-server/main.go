package main

import (
	"fmt"
	"net/http"
)

type WelcomeHandler struct{}
type HelloHandler struct{}

func (wh *WelcomeHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "welcome to my channel")
}

func (hh *HelloHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	fmt.Fprint(w, "Hello,world")
}

func main() {

	wh := WelcomeHandler{}
	hh := HelloHandler{}

	server := http.Server{
		Addr:    ":8080",
		Handler: nil,
	}

	http.Handle("/welcome", &wh)
	http.Handle("/hello", &hh)

	server.ListenAndServe()
}
