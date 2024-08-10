package main

import (
	"fmt"
	"server-example/pkg/handler"
)

func main() {
	user1 := handler.User{Name: "Fred", Age: 33}
	fmt.Println(user1.GetMessage("Hello,Fred"))

	user2 := handler.User{Name: "David", Age: 52}
	fmt.Println(user2.GetMessage("Nice to Meet you"))
}
