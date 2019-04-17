package com.example.basic_operators;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TakeAndSkip {
	public static void main(String ...args) {
		Observable<Long> src = Observable.interval(300, TimeUnit.MILLISECONDS);
		src.take(4, TimeUnit.SECONDS)
			.subscribe(t -> System.out.println("Took: " + t));
		src.skip(4, TimeUnit.SECONDS)
			.subscribe(s -> System.out.println("Didnt Skip [4]: " + s));
		src.skip(2, TimeUnit.SECONDS)
			.subscribe(s -> System.out.println("Didnt Skip [2]: " + s));
		sleep(10000L);
		
		Observable<Long> newSrc = Observable.rangeLong(1, 100);
		newSrc.takeWhile(t -> (t < 20))
			.subscribe(s -> System.out.println("Took [while]: " + s));
		newSrc.skipWhile(t -> (t < 20))
			.subscribe(s -> System.out.println("Didn't Skip [while]: " + s));
		newSrc.takeWhile(t -> t < 60)
			.subscribe(s -> System.out.println("Took [while2]: " + s));
		newSrc.skipWhile(t -> t < 60)
			.subscribe(s -> System.out.println("Didn't Skip [while2]: " + s));
	}
	public static void sleep(Long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
