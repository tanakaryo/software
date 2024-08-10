package handler

import "testing"

func TestUser_GetMessage(t *testing.T) {
	type fields struct {
		Name string
		Age  int
	}
	type args struct {
		message string
	}
	tests := []struct {
		name   string
		fields fields
		args   args
		want   string
	}{
		{"Test1", fields{Name: "Johnson", Age: 32}, args{"Nice to meet you."}, "Johnson(32) san, Nice to meet you."},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			u := &User{
				Name: tt.fields.Name,
				Age:  tt.fields.Age,
			}
			if got := u.GetMessage(tt.args.message); got != tt.want {
				t.Errorf("User.GetMessage() = %v, want %v", got, tt.want)
			}
		})
	}
}
