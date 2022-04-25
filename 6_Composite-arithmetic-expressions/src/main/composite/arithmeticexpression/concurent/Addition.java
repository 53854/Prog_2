package main.composite.arithmeticexpression.concurent;

public class Addition extends Operation{
    public Addition(Expression expOne, Expression expTwo) {
        super(expOne, expTwo);
    }

    @Override
    public long evaluateConcurently(long threshold) {
        final ExpressionTask e1 = new ExpressionTask(expressions.get(0), threshold);
        e1.fork();
        final ExpressionTask e2 = new ExpressionTask(expressions.get(1), threshold);
        return e2.compute() + e1.join();
    }

    @Override
    public long evaluate() {
        return expressions.get(0).evaluate() + expressions.get(1).evaluate();
    }
}
