package com.react.api.controller;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class ReactTestController {

	@GetMapping("/listFluxValues")
	public Flux<String> listFluxValues() {
		return Flux.just("Java 8 ->", " Springboot ->", " Reactive ->", " Kafka ->", " GraphQL")
				.delayElements(Duration.ofSeconds(5))
				.log()
				.doOnComplete(() -> System.out.println("*** Completed the Flux ***"))
				.doOnError((e) -> System.out.println("*** Error while processing the Flux ***"));
	}
	
	@GetMapping("/errorFluxValues")
	public Flux<String> errorFluxValues() {
		return Flux.just("Java 8 ->", " Springboot ->", " Reactive ->", " Kafka ->", " GraphQL")
				.delayElements(Duration.ofSeconds(5))
				.log()
				.map(e -> {
					if (e.contains("Reactive")) 
						throw new RuntimeException("--- Throwing Exception for Reactive ---"); 
					return e;
				})
				.doOnComplete(() -> System.out.println("*** Completed the Flux ***"))
				.doOnError((e) -> System.out.println("*** Error while processing the Flux ***"));
	}	
}
