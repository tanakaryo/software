package handler

import (
	"fmt"
)

type User struct {
	Name string
	Age  int
}

func (u *User) GetMessage(message string) string {
	return fmt.Sprintf("%s(%d) san, %s", u.Name, u.Age, message)
}
