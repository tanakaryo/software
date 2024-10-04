package myutil

func MakeArrays1(s string) []string {

	var arr [5]string
	arr[0] = "Hello"
	arr[1] = "world"
	arr[2] = "Guten"
	arr[3] = "Tag"
	arr[4] = "How Are You?"

	return arr[:]
}

func MakeArrays2() []string {
	arr := [...]string{"Java", "Golang", "Typescript", "Rust"}
	return arr[:]
}
