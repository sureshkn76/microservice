package com.poc.aws.lambda.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	int id;
	String name;
	String department;
	List<String> skillList;
	String role;

}
