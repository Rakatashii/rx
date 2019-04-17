package com.example.basic_operators;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxCollect {
	public static void main(String ... args) {
		Mono<Set<String>> mset = Flux.just("Alpha", "Beta", "Gamma", "Epsilon").collect(HashSet<String>::new, Set<String>::add);
		mset.subscribe(set -> {
			Iterator<String> iter = set.iterator();
			while (iter.hasNext()) {
				System.out.println("Element: " + iter.next());
			}
		});
	}
}
