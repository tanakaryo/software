from cls.person import Person

class BusinessPeron(Person):

    def __init__(self, name):
        self._name = name


    def sayHello(self):
        print("Hello, my name is " + self._name + ". Nice to meet you.")