#include <iostream>
#include "cls/person.cpp"

using namespace std;

int main() {

    Person p1("satoshi", 14, "Japan");
    p1.sayHello();
    return 0;
}