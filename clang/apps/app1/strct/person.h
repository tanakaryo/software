#ifndef PERSON_H
#define PERSON_H

#include <stdio.h>

typedef struct Person {
    char name[100];
    int age;
    char country[100];
} Person;

void sayHello(Person*);

#endif // !PERSON_H
#define PERSON_H
