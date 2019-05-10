package com.example.action_operators;

import reactor.core.publisher.Flux;

public class DoOn {
	public static void main(String ... args) {
		Flux<Integer> initial = Flux.just(0, 1, 2, 3, 4, 5, 6, 7, 8);
		Flux<Integer> results = initial
			.doOnNext(n -> System.out.println("before filter: " + n))
			.filter(i -> i % 2 == 0)
			.doOnNext(n -> System.out.println("before map: " + n))
			.map(n -> (n != 0) ? n / 2 : 0);
		results.subscribe(
			n -> System.out.println("results: " + n), 
			e -> e.printStackTrace(), 
			() -> System.out.println("DONE"));
	}
}
