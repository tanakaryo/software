package main

import (
	"fmt"
	"main/mypkg"
	"main/myutil"
	"reflect"
)

const (
	_ = iota
	A
	B
	C
)
const (
	A1 = iota + iota
	B1
	C1
)

func main() {
	fmt.Println("Hello")
	fmt.Println(mypkg.Hello())
	fmt.Println(mypkg.Greeting())
	mypkg.CallAgent("Good Morning")
	fmt.Println(myutil.MakeArrays1("David"))
	fmt.Println(myutil.MakeArrays2())
	fmt.Println(A)
	fmt.Println(B)
	fmt.Println(C)
	fmt.Println(A1)
	fmt.Println(B1)
	fmt.Println(C1)
	fmt.Println(reflect.TypeOf(mypkg.Hello()))
	x := uint8(10)
	fmt.Println(x)
	fmt.Println(len(myutil.MakeArrays1("Frank")))
	fmt.Println(cap(myutil.MakeArrays2()))
	array1 := myutil.MakeArrays2()
	fmt.Println(array1[0:2])
}
