package com.mycompany.gestionproyectosacademicos.observer;

public interface Subject {

    void addObserver(IObserver observer);

    void removeObserver(IObserver observer);

    void notifyObservers();
}
