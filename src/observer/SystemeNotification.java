package observer;

import java.util.ArrayList;
import java.util.List;

public class SystemeNotification {
    private static List<Observer> observers = new ArrayList<>();

    public static void ajouterObserver(Observer observer) {
        observers.add(observer);
    }

    public void retirerObserver(Observer observer) {
        observers.remove(observer);
    }

    public static void notifierTous(String message) {
        for (Observer obs : observers) {
            obs.notifier(message);
        }
    }
}
