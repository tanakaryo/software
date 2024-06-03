#include "person.h"

#include <iostream>

using namespace std;

void Person::sayHello()
{
  std::cout << "Hello! My name is " + Person::name + "!\n";
  std::cout << "I'm " + std::to_string(Person::age) + " years old now.\n";
  std::cout << "I'm live in " + Person::country + ".\n";

}