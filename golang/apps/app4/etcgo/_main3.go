package main

func main() {
	const (
		num1 = 1 + 2
		num2
		num3
	)

	println(num1, num2, num3)
	var x int = num1 + num2 + num3

	if x == num1 {
		println("x is equivalant num1")
	} else if x == num2 {
		println("x is equivalant num2")
	} else {
		println("x is not much of them")
	}
}
