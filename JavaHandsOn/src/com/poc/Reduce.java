package com.poc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.poc.domain.Student;
import com.poc.domain.StudentDataBase;

public class Reduce {

	public static void main(String[] args) {

//		The reduce() method iteratively apply accumulator function on the current input element.
//		Assuming # represents the accumulator function and a, b, c, d and e are stream elements:
//		    (a # b) # c  # d  # e
//		=>      (s  # c) # d  # e   [a # b = s]
//		=>           (t  # d) # e   [s # c = t]
//		=>                 (u # e)  [t # d = u]
//		=>                      z   [u # e = z]

		List<String> words = Arrays.asList("GFG", "Geeks", "for", "GeeksQuiz", "GeeksforGeeks");
		longestString(words);
		concatAllString(words);
		simpleCount();
		minMaxValue();
		findMaxNotes(StudentDataBase.getAllStudents());
		reduceWithDiffParamsForStringLength();
	}

	public static void findMaxNotes(List<Student> list) {
		Optional<Student> output = list.stream().reduce((s1, s2) -> s1.getNoteBooks() > s2.getNoteBooks() ? s1 : s2);
		System.out.print("*** Find Student with MaxNoteBooks -> " + output.get());

	}

	public static void concatAllString(List<String> words) {
		System.out.print("Concat value -> ");

		Optional<String> concatString = words.stream().reduce((word1, word2) -> word1 + "-" + word2);

		// Displaying the longest String
		concatString.ifPresent(System.out::print);
	}

	public static void longestString(List<String> strList) {
		System.out.println("List value -> " + strList);

		Optional<String> longestString = strList.stream()
				.reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2);

		// Displaying the longest String
		longestString.ifPresent(System.out::println);
	}

	public static void simpleCount() {
		List<Integer> words = Arrays.asList(1, 2, 3, 4, 5); // first it takes (1,2)-> (3,3)->(6,4)->(10,5)=
		Optional<Integer> result = words.stream().reduce(Integer::sum);
		System.out.println("Sum value -> " + result.get());

		Optional<Integer> result1 = words.stream().reduce(Integer::sum);
		System.out.println("Sum value -> " + result1.get());
	}

	public static void minMaxValue() {
		List<Integer> words = Arrays.asList(11, 22, 3, 64, 35); // first it takes (1,2)-> (3,3)->(6,4)->(10,5)=
		Optional<Integer> result = words.stream().reduce((c1, c2) -> c1 < c2 ? c1 : c2);
		System.out.println("Minimum value -> " + result.get());

		Optional<Integer> result1 = words.stream().reduce((c1, c2) -> c1 > c2 ? c1 : c2);
		System.out.println("Max value -> " + result1.get());

	}

	public static void reduceWithDiffParamsForStringLength() {
		// Find the number of characters in a string using 3 reduce params
		List<String> words = Arrays.asList("This is stream reduction example learn well".split(" "));
		// reduce with Optional<T> reduce(BinaryOperator<T> accumulator)
		int totalLength = words.stream().map(String::length).reduce(0, Integer::sum);
		// reduce with T reduce(T identity, BinaryOperator<T> accumulator)
		Optional<Integer> opt = words.stream().map(String::length).reduce(Integer::sum);
		// reduce with U reduce(U identity, BiFunction<U, ? super T, U> accumulator,
		// BinaryOperator<U> combiner)
		int totalLength2 = words.stream().reduce(0, (i, str) -> i + str.length(), Integer::sum);
		System.out.println("Length 1: " + totalLength + "; 2: " + opt.get() + " ; 3: " + totalLength2);

		// We saw the sample use of these reduction methods so let’s explore more on
		// this 3-argument reduction operation.
		// Ref: https://java-8-tips.readthedocs.io/en/stable/streamsapi.html
		int result = words.stream().reduce(0, (i, str) -> {
			System.out.println("BiFunc: " + i + "  " + str);
			return i + str.length();
		}, (i, j) -> {
			System.out.println("BiOpr: " + i + "  " + j);
			return i + j;
		});
	}

}
