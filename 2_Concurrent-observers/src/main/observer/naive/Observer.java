package main.observer.naive;

public abstract class Observer implements Runnable {

	
	public void updateObserver() {
		new Thread(this).start();
	}

	
	
	/**
	 * Operation that is called by an Observee in order to
	 * notify a change of state to an Observer.
	 */
	protected abstract void update();
	
	public void run(){
		this.update();
	}
}
