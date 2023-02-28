package com.poc.aws.lambda;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class LambdaRequestStreamHandler implements RequestStreamHandler {

	public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) {
		String greeting;
		try {
			greeting = new String(inputStream.readAllBytes());
			outputStream.write(("Hello World - " + greeting).getBytes());
		} catch (IOException e) {
			System.out.println("*** Exception caught in LambdaRequestStreamHandler: "+e.getMessage());
			e.printStackTrace();
		}

	}
}
