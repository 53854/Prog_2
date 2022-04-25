package main.composite.arithmeticexpression.concurent;

import java.util.concurrent.ForkJoinPool;

public class Constant implements Expression{

    private final long value;

    public Constant(long value){
        this.value = value;
    }

    public long getDepth(){
        return 1;
    }

    @Override
    public long evaluateConcurently(long threshold) {
        return 0;
    }

    @Override
    public long evaluate() {
        return value;
    }
}
