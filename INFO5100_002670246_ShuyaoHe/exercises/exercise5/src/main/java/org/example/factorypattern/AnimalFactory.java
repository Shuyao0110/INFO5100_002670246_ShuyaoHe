package org.example.factorypattern;

public class AnimalFactory {
    public Animal createAnimal(String animalType) {
        if ("Dog".equals(animalType)) {
            return new Dog();
        } else if ("Cat".equals(animalType)) {
            return new Cat();
        }
        return null;
    }
}
