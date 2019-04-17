package com.example.observables;

import java.util.ArrayList;

import reactor.core.publisher.Flux;

public class FluxBasicFactoryMethods {
	public static void main(String ...args) {
		/***** FLUX *****/
		/** CREATE **/
		// Use the .create factory method to define a new Flux<T> in terms of it's onNext, OnComplete
		// and onError callbacks
		Flux<String> flux = Flux.create(emitter -> {
			emitter.next("alpha");
			emitter.next("alpha");
			emitter.next("beta");
			emitter.next("sigma");
			emitter.next("lambda");
			emitter.next("eta");
			emitter.next("gamma");
			emitter.complete();
		});

		System.out.println("\nFirst: ");
		System.out.println(flux.blockFirst());
		
		/** TO_ITERABLE **/
		System.out.println("\nFull Flux");
		flux.toIterable().forEach(string -> {
			System.out.println(string);
		});
		System.out.println("\nFiltered Flux");
		Flux<Integer> srcLengths = flux.map(String::length).filter(length -> length <= 4);
		srcLengths.subscribe(length -> System.out.println("Length: " + length));
		
		/** JUST **/
		System.out.println("\nJust Flux");
		ArrayList<String> greekLetters = new ArrayList<String>();
		greekLetters.add("alpha");
		greekLetters.add("beta");
		// Use .just to hook sources that are not reactive
		Flux<String> greekFlux = Flux.just(greekLetters.get(0), greekLetters.get(1));
		greekFlux.subscribe(letter -> System.out.println("Letter: " + letter));
	}
}
