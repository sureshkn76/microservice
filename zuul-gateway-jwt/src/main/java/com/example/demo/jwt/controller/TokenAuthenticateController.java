package com.example.demo.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jwt.domain.AuthRequest;
import com.example.demo.jwt.security.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TokenAuthenticateController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String welcome() {
		log.info("*** Received Greeting request ***");
		return "Welcome to javatechie !!";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		log.info("*** Received authenticate request ***");

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		log.info("*** UserName authenticated. Generate token started ***");

		return jwtUtil.generateToken(authRequest.getUserName());
	}
}
