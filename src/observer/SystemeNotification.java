package observer;

import java.util.ArrayList;
import java.util.List;

public class SystemeNotification {
    private List<Observer> observers = new ArrayList<>();

    public void ajouterObserver(Observer observer) {
        observers.add(observer);
    }

    public void retirerObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifierTous(String message) {
        for (Observer obs : observers) {
            obs.notifier(message);
        }
    }
}
