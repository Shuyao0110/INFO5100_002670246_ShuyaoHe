package org.example;

import org.example.factorypattern.Animal;
import org.example.factorypattern.AnimalFactory;
import org.example.observerPattern.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Singleton Pattern verification
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        // the output is true, because obj1 and obj2 is the same instance
        System.out.println(obj1 == obj2);

        // Factory Pattern verification
        AnimalFactory factory = new AnimalFactory();
        Animal dog = factory.createAnimal("Dog");
        Animal cat = factory.createAnimal("Cat");
        dog.speak();  // 输出 "Woof!"
        cat.speak();  // 输出 "Meow!"

        // Observer Pattern verification
        Subject subject = new Subject();
        Observer observer1 = new ConcreteObserver();
        Observer observer2 = new ConcreteObserver();
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.notifyObservers("Hello Observers!");
    }
    }
