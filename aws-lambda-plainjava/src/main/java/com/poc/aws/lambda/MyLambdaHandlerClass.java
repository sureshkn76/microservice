package com.poc.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;

/**
Every lambda function needs a handler. A handler is nothing but the entry point for lambda to start executing your code.
There are 3 ways to create lambda handlers such as
1) Creating Custom Lambda Handler class with handleRequest() method. This code follow this approach
2) Implementing RequestHandler interface 
3) Implementing RequestStreamHandler interface
**/

public class MyLambdaHandlerClass {
	
	public String handleRequest(String input, Context context) {
		context.getLogger().log("Input: " + input);
		return "MyLambdaHandlerClass which has handleRequest() Method. Takes input as - " + input;
	}
	
}
