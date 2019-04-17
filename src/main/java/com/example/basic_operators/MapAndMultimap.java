package com.example.basic_operators;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;

import com.fasterxml.jackson.core.type.TypeReference;

import io.reactivex.Observable;
import io.reactivex.Single;

public class MapAndMultimap {
	public static void main(String... args) {

		Single<Map<Object, Integer>> mappedSource = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
			.toMap(k -> k.charAt(0), String::length, HashMap::new);
		mappedSource.subscribe(s -> System.out.println("Received: " + s.getClass() + " : " + s));	

		Single<Map<Object, Collection<String>>> mappedSource2 = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
			.filter(s -> s.length()== 1).toMultimap(String::length);
		mappedSource2.onErrorReturn(e -> {
			HashMap <Object, Collection<String>> mmap = new HashMap<Object, Collection<String>>();
			mmap.put(5, Arrays.asList("Sigma"));
			return mmap;
		}).subscribe(s -> System.out.println("Received: " + s.getClass() + " : " + s));
	
	}
}
