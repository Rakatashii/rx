package com.example.multicasting_operations;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class RefCountAndShare {
	public static void main(String[] args) throws InterruptedException {
		// refCount is similar to autoConnect, but will dispose of itself once it has no more Subscribers, 
		// then begin a new stream if another Observer subscribes to it
		Observable<Long> secs = Observable.interval(1, TimeUnit.SECONDS).publish().refCount();
		
		secs.take(5).subscribe(l -> System.out.println("Observer1: " + l));
		
		Thread.sleep(3000);
		
		secs.take(2).subscribe(l -> System.out.println("Observer2: " + l));
		
		Thread.sleep(3000); // Here, secs should have no more subscribers to emit to, since 5 * 1000 - 3000 + 2 * 1000 = 5000
		
		// So the stream will re-emit from 0, whereas in subscriber 2, it will continue emitting following the iteration of observer1
		secs.take(3).subscribe(l -> System.out.println("Observer3: " + l));
		
		Thread.sleep(3000);
	}
}
