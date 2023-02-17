package com.poc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.poc.domain.Student;
import com.poc.domain.StudentDataBase;

public class Test {

	public static void main(String args[]) {
		System.out.println("===== Test");
		
		List<Integer> intList = Arrays.asList(13,45,6,84,2,53);
		Collections.sort(intList);
		Collections.reverse(intList);
		System.out.println("===== sorted List -> "+intList);
		
		List<Student> students = StudentDataBase.getAllStudents();
		Map<String, Optional<Student>> output = students.stream().collect(
				Collectors.groupingBy(
							Student::getGender,
							Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Student::getGpa))))
				
				);
		System.out.println("===== grouped map -> "+output);
	

		int[] test = {11,32,2,13,25,6};
		List<Integer> intOutput = IntStream.of(test).boxed().sorted().collect(Collectors.toList());
		System.out.println("Integer object list: "+intOutput);
	}
}
