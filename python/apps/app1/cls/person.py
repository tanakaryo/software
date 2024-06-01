class Person():
    def __init__(self, name, age, country):
        self.__name = name
        self.__age = age
        self.__country = country

    def introduce(self):
        print("My name is " + self.__name + ". Nice to meet you.")
        print("I'm " + str(self.__age) + " years old.")
        print("I'm live in " + self.__country + ".")

    def getName(self):
        return self.__name

    def getAge(self):
        return self.__age

    def getCountry(self):
        return self.__country