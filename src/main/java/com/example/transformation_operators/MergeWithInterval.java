package com.example.transformation_operators;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MergeWithInterval {

	public static void main(String[] args) throws InterruptedException {
		Flux<Object> f1 = Flux.interval(Duration.ofMillis(300))
			.map(l -> l + 1)
			.map(l -> "SourceF1: " + l + " ms");
		Flux<Object> f2 = Flux.interval(Duration.ofSeconds(1))
			.map(l -> l + 1)
			.map(l -> "SourceF2: " + l + " seconds");
		
		Flux<Object> merged = f1.mergeWith(f2).flatMap(f -> 
			Mono.just(f));
		Disposable d1 = merged.subscribe(f -> 
				System.out.println("merge - " + f));
		
		Thread.sleep(3005L);
		System.out.println();
		if (!d1.isDisposed()) d1.dispose();
		
		Flux<Object> concatenated = f1.take(/*Duration.ofMillis(3005L)*/10).concatWith(f2).flatMap(f -> 
			Mono.just(f));
		Disposable d2 = concatenated.subscribe(f ->
				System.out.println("concat - " + f));
		
		Thread.sleep(3005L);
		System.out.println();
		Thread.sleep(3005L);
		System.out.println();
		if (!d2.isDisposed()) d2.dispose();
		
		Disposable d3 = Flux.just(1, 2, 3).concatMap(i -> 
			Flux.interval(Duration.ofMillis(i*100)).take(5L).map(i1 -> 
				(i1 + 1)).map(i2 -> {
					System.out.println("i = " + i2);
					return i;
				}
			)
		).subscribe(); 
		
		Thread.sleep(2000);
		System.out.println();
		if (!d3.isDisposed()) d3.dispose();
		
		Disposable d4 = Flux.just(1).concatMap(i -> 
		Flux.interval(Duration.ofMillis(i*100)).map(i1 -> 
				(i1 + 1)).map(i2 -> {
					System.out.println("i = " + i2);
					return i;
				}
			)
		).subscribe(); 
		
		Thread.sleep(2000);
		System.out.println();
		if (!d4.isDisposed()) d4.dispose();
	}
}
