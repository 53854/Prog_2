package main.concurrent.consumer;

import main.concurrent.producer.ConcreteProducer;

public class ConcreteConsumer extends Consumer{

	int state = 0;
	ConcreteProducer prod;
	String name;

	public ConcreteConsumer(String name){
		super();
		this.name = name;
	}

	public void setProducer(ConcreteProducer cc){
		this.prod = cc;
	}

	public Integer consumerState(){
		return state;
	}

	@Override
	protected void processValue(Integer value) {
		System.out.println(this.name + " processing:" + value);
		state += value;
		prod.setProducerState(state);
	}

	@Override
	public void run(){
		while(true) {
			try {
				if(isQueueEmpty()) return;
				final var value = queue.take();
				this.processValue(value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
