package main.observer.lazywaiting;

public abstract class Observer implements Runnable {

    private boolean updateNeeded;

    protected Observer() {
        this.updateNeeded = false;
        new Thread(this).start();
    }

    public void updateObserver(){
        this.updateNeeded = true;                       // (1)
        synchronized(this) {
            this.notify();                              // (2)
        }
    }

    public void run(){
        while(true) this.tryUpdate();
    }

    private void tryUpdate(){
        if(!this.updateNeeded) {                        // (3)
            synchronized(this) {
                try {
                    this.wait();                        // (4)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.updateNeeded = false;
        this.update();
    }

    protected abstract void update();
}
