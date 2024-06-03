#ifndef _PERSON_H_
#define _PERSON_H_

#include <string>

using namespace std;

class Person
{
private:
    std::string name;
    int age;
    std::string country;
public:
    Person(std::string name, int age, std::string country);
    ~Person();
    void sayHello();
};

Person::Person(std::string name, int age, std::string country)
{
    Person::name = name;
    Person::age = age;
    Person::country = country;
}

Person::~Person()
{
}

#endif // !_PERSON_H_