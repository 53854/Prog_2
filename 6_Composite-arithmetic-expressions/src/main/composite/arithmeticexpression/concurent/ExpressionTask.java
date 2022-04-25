package main.composite.arithmeticexpression.concurent;

import java.util.concurrent.RecursiveTask;

public class ExpressionTask extends RecursiveTask<Long> {

    final long threshold;
    final Expression e;

    public ExpressionTask(Expression e, long threshold){
        this.e = e;
        this.threshold = threshold;
    }

    @Override
    protected Long compute() {
        if(e.getDepth() <= threshold) return e.evaluate();
        return  e.evaluateConcurently(threshold);
    }
}
