package dao

import (
	"errors"
	"reflect"
	"testing"
	"testing/iotest"
)

func TestFileLoadInDataFail(t *testing.T) {
	type args struct {
		path string
	}
	tests := []struct {
		name    string
		args    args
		want    []byte
		wantErr bool
	}{
		{"Test3", args{"/Users/user/Documents/github/software/golang/apps/app2/server-example/resources/tests/testnil.txt"}, nil, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			r := iotest.ErrReader(errors.New("Hello,error"))
			got, err := FileLoadInData(tt.args.path)

			if err == nil {
				t.Fatal(got, r)
			}
		})
	}
}

func TestFileLoadInDataSuccess(t *testing.T) {
	type args struct {
		path string
	}
	tests := []struct {
		name    string
		args    args
		want    []byte
		wantErr bool
	}{
		{"Test1", args{"/Users/user/Documents/github/software/golang/apps/app2/server-example/resources/tests/testout.txt"}, []byte("Hello,world"), false},
		{"Test2", args{"/Users/user/Documents/github/software/golang/apps/app2/server-example/resources/tests/testnotexist.txt"}, nil, true},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			got, err := FileLoadInData(tt.args.path)
			if (err != nil) != tt.wantErr {
				t.Errorf("FileLoadInData() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
			if !reflect.DeepEqual(got, tt.want) {
				t.Errorf("FileLoadInData() = %v, want %v", got, tt.want)
			}
		})
	}
}
