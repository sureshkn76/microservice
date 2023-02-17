package com.poc;

import java.util.stream.Stream;

public class RecursionStream {
	
	public static void main(String[] args) {
		fibanociSeries();
	}

	public static void fibanociSeries() {
		// Generating fibonacci numbers of a given length
		Stream.iterate(new int[] { 0, 1 }, a -> {
			System.out.println("a: "+a.toString()+" ; a[0]: "+a[0]+" ; a[1]: "+a[1]);
		    int next = a[0] + a[1];
		    a[0] = a[1];
		    a[1] = next;
		    return a;
		}).limit(15).map(a -> a[0]).forEach(System.out::println);	
		
		// Generating fibonacci numbers of a given length
		Stream.iterate(new int[] { 0, 1 }, a -> {
			System.out.println("a: "+a.toString()+" ; a[0]: "+a[0]+" ; a[1]: "+a[1]);
		    int next = a[0] + a[1];
		    a[0] = a[1];
		    a[1] = next;
		    return a;
		}).limit(15).map(a -> a[0]).forEach(System.out::println);	
	}
}
