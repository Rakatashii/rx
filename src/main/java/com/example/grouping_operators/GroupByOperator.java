package com.example.grouping_operators;

import io.reactivex.Observable;
import io.reactivex.observables.GroupedObservable;

public class GroupByOperator {
	public static void main (String[] args) {
		Observable<String> source = Observable.just("Alpha", "Beta", "Delta", "Gamma", "Epsilon", "Psi");
		
		Observable<GroupedObservable<Integer, String>> grpSource = source.groupBy(s -> s.length());
		
		grpSource.flatMapSingle(grp -> 
			grp.reduce("", (x,y) -> x.equals("") ? y : x + ", " + y)
				.map(s -> grp.getKey() + ": " + s))
					.subscribe(System.out::println);
	}
}
