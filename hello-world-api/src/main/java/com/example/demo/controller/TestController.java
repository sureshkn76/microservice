package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/poc")
public class TestController {

	@Value("${welcome.message}")
	private String welcomeMessage;
	
	@GetMapping("/getWelcomeMsg")
	public String getEmployeeDetails(@RequestParam String name)  {

		return "Welcome "+name +" " +welcomeMessage;
	}
}
