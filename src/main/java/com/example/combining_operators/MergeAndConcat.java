package com.example.combining_operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.util.comparator.ComparableComparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;

public class MergeAndConcat {
	@AllArgsConstructor
	@Data
	public class User {
		public int id;
		public String name;
	}
	
	public static Comparator<User> getComparator(){
		Comparator<User> comparator = new Comparator<User>(){
			@Override
			public int compare(User a, User b) {
				return ((a.id < b.id) ? 1 : ((b.id < a.id) ? -1 : 0));
			}
		};
		return comparator;
	}
	
	static int i;
	public static void main(String[] args) throws InterruptedException {
		MergeAndConcat mc = new MergeAndConcat();
		List<User> users1 = Arrays.asList(mc.new User(1, "one"), mc.new User(2, "two"));
		List<User> users2 = Arrays.asList(mc.new User(3, "three"), mc.new User(4, "four")); 
		
		Flux<User> flux1 = Flux.fromIterable(users1);
		Flux<User> flux2 = Flux.fromIterable(users2);
		
		System.out.println();
		i = 0;
		
		Flux<User> merged = flux2.mergeWith(flux1.mergeWith(flux2));
		merged.flatMap(u -> {
			System.out.println("flux["+(++i)+"] (merge): "+u);
			u.setName(u.getName().toUpperCase());
			return Flux.just(u);
		})
		.flatMap(u -> {
			System.out.println("flux["+(++i)+"] (merge): "+u);
			if (i == 6) {
				System.out.println();
				i = 0;
			}
			u.setName(u.getName().toUpperCase());
			return Flux.just(u);
		}).
		subscribe(u -> {
			//System.out.println("flux["+(++i)+"] (merge): "+u);
		});
		i = 0;
		
		flux1 = Flux.fromIterable(users1);
		flux2 = Flux.fromIterable(users2);
		
		Flux<User> concatenated = flux2.concatWith(flux1.concatWith(flux2));
		concatenated.flatMap(u -> {
			System.out.println("flux["+(++i)+"] (concat): "+u);
			u.setName(u.getName().toUpperCase());
			return Flux.just(u);
		})
		.flatMap(u -> {
			System.out.println("flux["+(++i)+"] (merge): "+u);
			if (i == 6) {
				System.out.println();
				i = 0;
			}
			u.setName(u.getName().toUpperCase());
			return Flux.just(u);
		})
		.doOnSubscribe(s -> System.out.println()).subscribe(u -> {
			//System.out.println("flux["+(++i)+"] (concat): "+u);
		});
		System.out.println();
		i = 0;
		
	}
}
