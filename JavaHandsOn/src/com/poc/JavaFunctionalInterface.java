package com.poc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.poc.domain.Student;
import com.poc.domain.StudentDataBase;

public class JavaFunctionalInterface {

	static Consumer<Student> consumer1 = (student) -> System.out.print(" -- " + student.getName());

	public static void main(String[] args) {

		List<Student> students = StudentDataBase.getAllStudents();
		students.forEach(student -> {
			consumer1.accept(student);
		});

		List<Integer> intList = Arrays.asList(20, 12, 33, 4, 15, 56, 27);

		// Without initial value. returns optional
		Optional<Integer> result = intList.stream()
				// .peek((s1) -> System.out.println("" + s1))
				.reduce((s1, s2) -> s1 + s2);
		System.out.println("\nReduce result without initial vallue -> " + result);

		// With initial value. returns optional
		Integer result1 = intList.stream().reduce(1, (s1, s2) -> s1 + s2);
		System.out.println("Reduce result with initial value -> " + result1);

		List<Integer> output = intList.stream().sorted().collect(Collectors.toList());
		System.out.println("Sorted Int List -> " + output);

		int[] intArray = { 20, 12, 33, 4, 15, 56, 27 };
		List<Integer> output2 = IntStream.of(intArray).boxed().sorted().collect(Collectors.toList());
		System.out.println("Sorted Int List -> " + output2);

//		for(int i : output2) {System.out.println(" int value -> "+i); }
		List<Student> emp = StudentDataBase.getAllStudents();
//		for(Student student : emp) {System.out.println(" emp value -> "+student); }

		ConvertToListAndSet(students);

		stringReversal("This is my test program for java");

		sortStudents(students);
		
		groupByGenderAndFindMaxGpa(students);
	}

	public static void groupByGenderAndFindMaxGpa(List<Student> students) {
		System.out.println("*** groupByGenderAndFindMaxGpa using collectingAndThen : ");
		//using collectingAndThen with MaxBy
		 Map<String, Student> groupByResult = students.stream()
		.collect(Collectors.groupingBy(Student::getGender,
					Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Student::getGpa)),
												Optional::get))
									   );
		 groupByResult.forEach((k,v) -> System.out.println("Key : " + k + ", Value : " + v));
		 
		System.out.println("*** groupByGenderAndFindMaxGpa using BinaryOperator : ");
		Map<String, Student> groupByStudentWithBinOps =
				 students.stream()
				            .collect(Collectors.toMap(
				                e -> e.getGender(),
				                e -> e, BinaryOperator.maxBy(Comparator.comparingDouble(e -> e.getGpa())) 
				            ));		
		groupByStudentWithBinOps.forEach((k,v) -> System.out.println("Key : " + k + ", Value : " + v));


		System.out.println("*** groupByGenderAndFindMaxGpa using reducing : ");
		Comparator<Student> byGpa = Comparator.comparing(Student::getGpa);
		Map<String, Optional<Student>> groupByStudentWithReduce = 
				students.stream().collect(
		            Collectors.groupingBy(
		            		Student::getGender,
		                    Collectors.reducing(BinaryOperator.maxBy(byGpa))
		    )
		);
		groupByStudentWithReduce.forEach((k,v) -> System.out.println("Key : " + k + ", Value : " + v));

	}

	public static void ConvertToListAndSet(List<Student> students) {
		System.out.println("*** Activity List : ");
		List<String> activityList = students.stream().map(Student::getActivities)
//		.peek(e-> System.out.println("map element: "+e))		
				.flatMap(e -> e.stream())
//		.peek(e-> System.out.println("flatmap element: "+e))		
				.toList();
		activityList.forEach(e -> System.out.print(" - " + e));

		System.out.println("\n*** Activity Set - Distinct : ");
		Set<String> activitySet = students.stream().map(e -> e.getActivities()).flatMap(e -> e.stream())
				.collect(Collectors.toSet());
		activitySet.forEach(e -> System.out.print(" : " + e));

		System.out.println("\n*** Activity Set - Distinct : ");
		Set<String> activityLinkedHasSet = students.stream().map(e -> e.getActivities()).flatMap(e -> e.stream())
				.collect(Collectors.toCollection(LinkedHashSet::new));
		activityLinkedHasSet.forEach(e -> System.out.print(" *** " + e));
	}

	public static void stringReversal(String input) {
		System.out.println("\n Original String -> " + input);
		List<String> inputArray = Arrays.asList(input.split(" "));

		String test = inputArray.stream().collect(Collectors.joining(" "));
		System.out.println(" Reversed String -> " + new StringBuffer(test).reverse());
	}

	public static void sortStudents(List<Student> students) {
		System.out.println("\n Original List -> " + students);
		System.out.println("\n Sorted by Grade List -> " + students);
		students.stream().sorted(Comparator.comparingInt(Student::getGradeLevel)).collect(Collectors.toList())
				.forEach(System.out::println);

		System.out.println("\n Sorted by Name List -> " + students);
		students.stream().sorted(Comparator.comparing(Student::getName))
				.collect(Collectors.toList())
				.forEach(System.out::println);
		
		System.out.println("\n Reversed Sort by Name List -> " + students);
		students.stream().sorted(Comparator.comparing(Student::getName).reversed())
				.collect(Collectors.toList())
				.forEach(System.out::println);
		
		System.out.println("\nSorted by Grade and then Name List -> " + students);
		students.stream().sorted((s1, s2) -> {
			if (s1.getGradeLevel() == s2.getGradeLevel())
				return s1.getName().compareTo(s2.getName());
			else if (s1.getGradeLevel() > s2.getGradeLevel())
				return 1;
			else
				return -1;
		}).collect(Collectors.toList()).forEach(System.out::println);
		
		Comparator<Student> gradeCompare = Comparator.comparing(Student::getGradeLevel);
		Comparator<Student> nameCompare = Comparator.comparing(Student::getName);

		System.out.println("\nSorted by Grade and then Name List using comparator -> " + students);
		students.stream()
		.sorted(gradeCompare.thenComparing(nameCompare))
		.collect(Collectors.toList()).forEach(System.out::println);		
	}

	private void println(String string1, Student student2) {
	}

}
