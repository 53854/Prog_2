package main.concurrent.producer;

import main.concurrent.consumer.Consumer;

import java.util.HashSet;
import java.util.Set;

public abstract class Producer implements Runnable {

    private final int numberOfEventsToEmmit;
    private final Set<Consumer> consumers;

    protected Producer(int numberOfEventsToEmmit){
        this.numberOfEventsToEmmit = numberOfEventsToEmmit;
        this.consumers = new HashSet<>();
    }

    /**
     * Attaches a Consumer to this Producer.
     * @param consumer receives update notifications
     * until deregister is called for this consumer.
     */
    public void register(final Consumer consumer){
        this.consumers.add(consumer);
    }

    /**
     * Detaches a Consumer from this Producer.
     * @param consumer does no longer receive update notifications
     * until register is called for this consumer.
     */
    public void deregister(Consumer consumer){
        this.consumers.remove(consumer);
    }

    /**
     * Sends events in form of an Integer to all currently registered Consumers.
     */
    protected void notify(Integer value){
        this.consumers.forEach(c -> c.update(value));
    }

    /**
     * Generates an Integer. The strategy how this is done is defined in the respective subclasses.
     */
    protected abstract Integer generateValue();

    @Override
    public void run() {
        for (int i = 0; i < this.numberOfEventsToEmmit; i++) {
            notify(this.generateValue());
        }
    }
}