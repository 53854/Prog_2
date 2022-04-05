package main.observer.busywaiting;

public abstract class Observer implements Runnable {

    private boolean updateNeeded;

    protected Observer() {
        this.updateNeeded = false;
        new Thread(this).start();
    }

    public void updateObserver(){
        this.updateNeeded = true;
    }

    public void run(){
        while(true) this.tryUpdate();
    }

    private void tryUpdate() {
        if(this.updateNeeded){
            this.updateNeeded = false;
            this.update();
        }
    }

    protected abstract void update();

}
