package com.example.multicasting_operations;

import java.util.concurrent.ThreadLocalRandom;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

public class BasicMulticast {
	public static void main (String[] args) {
		Observable<Integer> numRange = Observable.range(1, 10).map(i -> ThreadLocalRandom.current().nextInt(10000));
		numRange.subscribe(n -> System.out.println("Num (1) : " + n), e -> {}, () -> System.out.println("Done (1)"));
		numRange.subscribe(n -> System.out.println("Num (2) : " + n), e -> {}, () -> System.out.println("Done (2)"));
		
		System.out.println();
		
		ConnectableObservable<Integer> cnumRange = Observable.range(1, 10).map(i -> ThreadLocalRandom.current().nextInt(10000))
			.publish();
		cnumRange.subscribe(n -> System.out.println("Num (1) : " + n), e -> {}, () -> System.out.println("Done (1)"));
		cnumRange.subscribe(n -> System.out.println("Num (2) : " + n), e -> {}, () -> System.out.println("Done (2)"));
		
		cnumRange.connect();
	}
}
