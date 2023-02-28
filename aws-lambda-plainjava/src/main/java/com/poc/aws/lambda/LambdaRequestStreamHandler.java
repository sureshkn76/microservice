package com.poc.aws.lambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.aws.lambda.domain.Employee;

public class LambdaRequestStreamHandler implements RequestStreamHandler {

	/*
	 * public void handleRequest(InputStream inputStream, OutputStream outputStream,
	 * Context context) { String greeting; try { greeting = new
	 * String(inputStream.readAllBytes()); outputStream.write(("Hello World - " +
	 * greeting).getBytes()); } catch (IOException e) {
	 * System.out.println("*** Exception caught in LambdaRequestStreamHandler: "+e.
	 * getMessage()); e.printStackTrace(); } }
	 */

	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
		try {
			LambdaLogger log = context.getLogger();
			log.log("**** Received the input Request ***");
			ObjectMapper mapper = new ObjectMapper();
			Employee employee = mapper.readValue(inputStream, Employee.class);

			log.log("**** Received Employee: "+employee.toString());
			employee.setSkillList(Arrays.asList("Java 8","AWS","Springboot 3"));
			
			log.log("**** updated Employee Skillset: "+employee.toString());
			outputStream.write(mapper.writeValueAsBytes(employee));
			outputStream.close();
		} catch (Exception e) {
			System.out.println("*** Exception caught in LambdaRequestStreamHandler: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
