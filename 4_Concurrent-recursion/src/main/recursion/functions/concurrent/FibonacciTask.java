package main.recursion.functions.concurrent;

import java.util.concurrent.RecursiveTask;

import main.recursion.functions.RecursionAlgorithms;

public class FibonacciTask extends RecursiveTask<Long> {

	private final long n;
	private final long threshold;

	FibonacciTask(final long n, final long threshold) {
		this.n = n;
		this.threshold = threshold;
	}

	@Override
	public Long compute() {
		if (n <= threshold) return RecursionAlgorithms.fib(n);
		final FibonacciTask f1 = new FibonacciTask(n - 1, threshold);
		f1.fork();
		final FibonacciTask f2 = new FibonacciTask(n - 2, threshold);
		return f2.compute() + f1.join();
	}
}
