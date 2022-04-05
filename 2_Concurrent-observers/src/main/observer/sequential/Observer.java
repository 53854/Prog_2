package main.observer.sequential;

public interface Observer {

    /**
     * Operation that is called by an Observee in order to
     * notify a change of state to an Observer.
     */
    void update();
}