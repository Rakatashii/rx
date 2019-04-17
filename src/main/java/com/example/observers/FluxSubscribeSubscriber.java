package com.example.observers;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class FluxSubscribeSubscriber {
	public static void main(String...strings) {
		Flux<String> src = Flux.create(emitter -> {
			emitter.next("alpha");
			emitter.next("beta");
			emitter.next("sigma");
			emitter.next("lambda");
			emitter.next("eta");
			emitter.next("gamma");
			emitter.complete();
		});
		Flux<String> uppers = src.map(String::toUpperCase);
		
		System.out.println("Subscription with custom subscriber object:");
		SrcSubscriber<String> srcSubscriber = new SrcSubscriber<String>();
		uppers.subscribe(srcSubscriber);
		
		System.out.println("\nSubscription with lambda:");
		uppers.subscribe(
			upper -> {
				System.out.println(upper);
			},
			e -> e.printStackTrace(),
			() -> System.out.println("Done")
		);
	}
}

class SrcSubscriber<T> extends BaseSubscriber<T>{
	public void hookOnSubscribe(Subscription subscription) {
		System.out.println("Subscribed");
		request(1);
	}
	
	public void hookOnNext(T value) {
		System.out.println(value);
		request(1);
	}
	
	public void hookOnComplete() {
		System.out.println("Done");
		request(1);
	}
	
	public void hookOnError(Throwable t) {
		t.printStackTrace();
	}
}