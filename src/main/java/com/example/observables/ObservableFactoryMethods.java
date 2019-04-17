package com.example.observables;

import io.reactivex.Observable;

public class ObservableFactoryMethods {
	public static void main(String ...strings) {
		/***** OBSERVABLE *****/
		Observable<String> source = Observable.create(emitter -> {
			emitter.onNext("alpha");
			emitter.onNext("beta");
			emitter.onNext("sigma");
			emitter.onNext("lambda");
			emitter.onNext("eta");
			emitter.onNext("gamma");
			emitter.onComplete();
		});
		
		Observable<Integer> lengths = source.map(String::length)
				.filter(length -> length < 4);
		lengths.subscribe(emission -> System.out.println("emission: " + emission));
		
		Observable<String> filteredSource = source.filter(emission -> emission.length() < 4);
		filteredSource.subscribe(emission -> System.out.println("emission: " + emission));
	}
}
