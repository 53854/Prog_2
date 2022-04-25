package main.composite.arithmeticexpression.concurent;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

import static java.lang.Math.max;

public abstract class Operation implements Expression {

    private final long depth;
    Expression expressionOne;
    Expression expressionTwo;
    ArrayList<Expression> expressions = new ArrayList<>();

    public Operation (Expression expOne, Expression expTwo){
        depth = max(expOne.getDepth(), expTwo.getDepth()) + 1;
        expressionOne = expOne;
        expressionTwo = expTwo;
    }

    @Override
    public long getDepth() {
        return depth;
    }
}
