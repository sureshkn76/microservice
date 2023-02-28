package com.poc.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaRequestHandlerImpl implements RequestHandler<Object, Object>{

	@Override
	public Object handleRequest(Object input, Context context) {
		context.getLogger().log("Input: " + input);
		return "LambdaRequestHandlerImpl which implements RequestHandler.handleRequest() Method. Takes input as - - " + input;
	}

}
