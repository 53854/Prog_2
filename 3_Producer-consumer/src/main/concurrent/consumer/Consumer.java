package main.concurrent.consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Consumer implements Runnable {

    protected BlockingQueue<Integer> queue;

    protected Consumer(){
        this.queue = new LinkedBlockingQueue<>();
    }

    public void update(final Integer value) {
        try {
            this.queue.put(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(true) {
            try {
                final var value = queue.take();
                this.processValue(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void processValue(final Integer value);

    public boolean isQueueEmpty() {
        return this.queue.isEmpty();
    }
}
