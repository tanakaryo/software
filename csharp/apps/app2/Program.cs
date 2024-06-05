using System;

namespace ConsoleApp {
    internal class Program {
        public static void Main(string[] args) {
            BusinessPerson bp = new BusinessPerson("satoshi", 14, "Japan", "Student");
            bp.sayHello();
        }
    }
}