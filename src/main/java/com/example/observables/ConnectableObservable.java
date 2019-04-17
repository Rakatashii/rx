package com.example.observables;

import org.reactivestreams.Subscription;

import io.reactivex.Observer;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class ConnectableObservable {
	public static void main(String ... args) {
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
		ConnectableFlux<String> hotFlux = flux.publish();
		
		HotSubscriber<String> sub1 = new HotSubscriber<String>();
		HotSubscriber<String> sub2 = new HotSubscriber<String>();
		HotSubscriber<String> sub3 = new HotSubscriber<String>();
		
		hotFlux.subscribe(sub1);
		hotFlux.subscribe(sub2);
		hotFlux.subscribe(sub3);
		
		hotFlux.map(string -> string.concat(" boss")).subscribe(next -> {
				System.out.println("next: " + next);
			}, 
			e -> {
				e.printStackTrace();
			},
			() -> System.out.println("DONE")
		);
		
		hotFlux.connect();
	}
}
	
class HotSubscriber<T> extends BaseSubscriber<T> {
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
