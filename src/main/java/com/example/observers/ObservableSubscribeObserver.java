package com.example.observers;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ObservableSubscribeObserver {
	public static void main(String... args) {
		Observable<String> src = Observable.create(emitter -> {
			emitter.onNext("alpha");
			emitter.onNext("beta");
			emitter.onNext("sigma");
			emitter.onNext("lambda");
			emitter.onNext("eta");
			emitter.onNext("gamma");
			emitter.onComplete();
		});
		Observable<Integer> lengths = src.map(String::length);
		
		Observer<Integer> srcObserver = new Observer<Integer>(){
			@Override
			public void onSubscribe(Disposable d) {}
			@Override
			public void onNext(Integer t) {
				System.out.println("RECEIVED: " + t);
			}
			@Override
			public void onError(Throwable e) {
				e.printStackTrace();
			}
			@Override
			public void onComplete() {
				System.out.println("DONE");
			}
		};
		
		lengths.subscribe(srcObserver);
	}
}
