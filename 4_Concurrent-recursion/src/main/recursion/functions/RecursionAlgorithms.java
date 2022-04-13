package main.recursion.functions;

public class RecursionAlgorithms {

	/**
	 * Naive implementation of the fibonacci function. Do not use with not positive n.
	 * f(4) = f(3) + f(2) = f(2) + f(1) + 1 = 1 + 1 + 1 = 3
	 * Complexity O(2^n).
	 */
	public static long fib(final long n) {
		if (n <= 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}

	/*
	 * TODO: See 4-concurrent-recursion/resources folder for further exercises.
	 */
}
