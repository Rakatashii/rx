package com.example.observables;

import java.time.Duration;

import org.reactivestreams.Subscriber;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotConnectableFluxInterval {
	public static void main(String... args) {
		ConnectableFlux<Long> hotFlux = Flux.interval(Duration.ofMillis(500L))
			.publish();
		hotFlux.subscribe(emit -> System.out.println("Observer1: " + (emit+1L)));
		hotFlux.connect();
		sleep(5000L);
		hotFlux.subscribe(emit -> System.out.println("Observer2: " + (emit+1L)));
		sleep(5000L);
		hotFlux.subscribe(emit -> System.out.println("Observer3: " + (emit+1L)));
		sleep(5000L);
	}
	public static void sleep(Long millis) {
		try { 
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
