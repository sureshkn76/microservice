package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/message")
	public String displayMessage(@RequestParam String name){
		System.out.println(" ******* Received the request in container!!! *****");
		return "Congratulation "+name +"!. You successfully deployed your application to kubernetes !!";
	}	
	
}
