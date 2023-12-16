package org.example.observerPattern;

public class ConcreteObserver implements Observer{
    public void update(String message) {
        System.out.println("Received message: " + message);
    }
}
