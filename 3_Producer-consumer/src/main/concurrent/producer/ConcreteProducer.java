package main.concurrent.producer;

public class ConcreteProducer extends Producer{

	int state = 0;
	String name;

	public ConcreteProducer(int numberOfEventsToEmmit, String name) {
		super(numberOfEventsToEmmit);
		this.name = name;
	}

	public Integer producerState(){
		return this.state;
	}

	public void setProducerState(int state){
		this.state = state;
	}

	@Override
	protected Integer generateValue() {
		int val = (int) (Math.random() * 10);
		System.out.println(this.name + " producing: " + val);
		return val;
	}
}
