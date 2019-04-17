package com.example.basic_operators;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Delay {
	public static void main(String... args) throws InterruptedException {
		Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
			.delaySubscription(3, TimeUnit.SECONDS)
			.subscribe(s -> System.out.println("Received: " + s));
		
		for (int i  = 0; i < 6; i++) {
			Thread.sleep(500);
		}
	}
	
}
