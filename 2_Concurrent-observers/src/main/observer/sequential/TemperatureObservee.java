package main.observer.sequential;

public class TemperatureObservee extends  Observee{

    private int temperature;

    public int getTemperature(){
        return temperature;
    }

    public void changeState(final int temperature){
        this.temperature = temperature;
        this.notifyObservers();
    }
}
