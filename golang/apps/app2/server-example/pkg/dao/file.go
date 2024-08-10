package dao

import (
	"io"
	"os"
)

func FileLoadInData(path string) ([]byte, error) {

	file, err := os.Open(path)
	defer file.Close()
	if err != nil {
		return nil, err
	}
	bytes, err := io.ReadAll(file)
	if err != nil {
		return nil, err
	}
	return bytes, nil
}
