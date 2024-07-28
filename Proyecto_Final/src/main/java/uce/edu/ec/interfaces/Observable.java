package uce.edu.ec.interfaces;

public interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notificar();
}


