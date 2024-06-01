import abc

class Person(metaclass=abc.ABCMeta):
    @abc.abstractmethod
    def sayHello(self) -> None:
        raise NotImplementedError()
