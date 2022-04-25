package main.composite.arithmeticexpression.concurent;

public class Division extends Operation{

    public Division(Expression expOne, Expression expTwo) {
        super(expOne, expTwo);
    }

    @Override
    public long evaluateConcurently(long threshold) {
        final ExpressionTask e2 = new ExpressionTask(expressions.get(1), threshold);
        e2.fork();
        final ExpressionTask e1 = new ExpressionTask(expressions.get(0), threshold);
        return e1.compute() / e2.join();
    }

    @Override
    public long evaluate() {
        return expressions.get(0).evaluate() / expressions.get(1).evaluate();
    }
}
