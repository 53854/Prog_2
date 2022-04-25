package main.composite.arithmeticexpression.concurent;

import main.composite.arithmeticexpression.Constant;

import java.util.concurrent.ForkJoinPool;

public interface Expression {

   long getDepth();

   long evaluateConcurently(long threshold);

   long evaluate();
}
