package com.poc;

public class RecursionFibonacci {

	public static void main(String args[]) {
		System.out.println(fibWithSeperateCall("Main ", 5));
//		System.out.println(fib("Main ", 7));
	}

	public static int fibWithSeperateCall(String comment, int n) {
		int firstInt;
		System.out.println("Called by "+comment+"; with N value: "+n);
		
		if(n <=1) {
			return n;	
		} else {
			firstInt = fib("(N-1) Actual N value: "+n, n-1);
			System.out.println("***************** Done with N value: "+n);
			return firstInt + fib(" ---> (N-2) Actual N value: "+n, n-2);
		}
	}

	public static int fib(String comment, int n) {
		System.out.println("Called by " + comment + "; with N value: " + n);

		if (n <= 1)
			return n;
		else
			return fib("(N-1) Actual N value: " + n, n - 1) + fib(" ---> (N-2) Actual N value: " + n, n - 2);
	}

}
