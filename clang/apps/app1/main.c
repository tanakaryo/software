#include <stdio.h>

#include "strct/person.c"

int main( int args, char **argv) {
    printf("Hello,world\n");

    Person p = {"satoshi", 14, "Japan"};
    sayHello(&p);
}