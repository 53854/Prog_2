package main.recursion.functions;

import java.util.concurrent.ForkJoinPool;
import main.recursion.functions.concurrent.*;

public class RecursionAlgorithms {

	/**
	 * Naive implementation of the factorial function
	 * f(3) = 3*f(2) = 3*2*f(1) = 3*2*1 = 6
	 * Complexity O(1).
	 * @return factorial of n
	 */
	public static int factorial(int n){
		if (n <= 1) return n;
		return n * factorial(n - 1);
	}

	/**
	 * Naive implementation of the power function.
	 * f(2,3) = 2 ∗ f(2,2) = 2 ∗ 2 ∗ f(2,1) = 2 ∗ 2 ∗ 2 ∗ f(2,0) = 2 ∗ 2 ∗ 2 ∗ 1 = 8
	 * Complexity O(n) as recursions are equal to the exponent b.
	 * @return a to the power of b
	 */
	public static int power(int a, int b){
		if(b <= 1)return a;
		else return a * power(a,b-1);
	}


	/* Efficient implementation of power function by divide an conquer logic
	 * Time complexity O(log(n))
	*/
	public static int powerFast(int a, int b) {
		if( b == 0) return 1; // x ^ 0 = 1
		int temp = power(a, b / 2); // divide an conquer

		// if exponent is a multiple of 2 p(base, exp /2) * p(base, exp /2)
		if (b % 2 == 0) return temp*temp;
		else return a*temp*temp; // else base * p(base, exp /2) * p(base, exp /2)
	}

	/**
	 * Naive implementation of the fibonacci function. Do not use with not positive n.
	 * f(4) = f(3) + f(2) = f(2) + f(1) + 1 = 1 + 1 + 1 = 3
	 * Complexity O(2^n).
	 * @return nth fibonacci element
	 */
	public static long fib(final long n) {
		if (n <= 2) return 1;
		return fib(n - 1) + fib(n - 2);
	}

	/**
	 * Concurrent implementation of the fibonacci function. Do not use with not positive n.
	 * threshold should be n/2
	 * Complexity O(log n).
	 * @return nth fibonacci element
	 */
	public static long fibConcurrent(final long n) {
		if(n <= 0) return 0;
		else if (n <= 2) return 1;
		else if (n == 3) return 2;
		return ForkJoinPool.commonPool().invoke(new FibonacciTask(n, (int)n/2));
	}

	/**
	 * efficient implementation of the fibonacci function. Do not use with not positive n.
	 * Complexity O(n).
	 * @return nth fibonacci element
	 */
	public static long fibSmart(final long n) {
		return  subFib(n, 1,1);
	}

	static long subFib(final long n, final long f1, final long f2){
		if(n <= 2) return f2;
		else return subFib(n-1, f2, f1 + f2);
	}

}
