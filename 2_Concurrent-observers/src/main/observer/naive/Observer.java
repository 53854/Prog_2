package main.observer.naive;

public abstract class Observer implements Runnable {

	/**
	 * Operation that is called by an Observee in order to
	 * notify a change of state to an Observer.
	 */
	public void updateObserver() {
		new Thread(this).start();
	}

	public void run(){
		this.update();
	}

	protected abstract void update();
}
