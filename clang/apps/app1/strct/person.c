#include <stdio.h>
#include "person.h"

void sayHello(Person* p) {
    printf("Hello, My name is %s.\n", p->name);
    printf("I'm %d years old now.\n", p->age);
    printf("I'm live in %s.\n", p->country);
}