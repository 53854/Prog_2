package main.observer.lazywaiting;

public class ConcreteObservee extends Observee {

    private int value;

    public int getValue() {
        return value;
    }

    public void changeState(final int value) {
        this.value = value;
        this.notifyObservers();
    }
}
