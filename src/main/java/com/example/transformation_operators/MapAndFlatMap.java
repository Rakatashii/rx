package com.example.transformation_operators;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MapAndFlatMap {
	
	@AllArgsConstructor
	@Data
	public class User {
		public String username;
		public String firstname;
		public String lastname;
	}
	
	public Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(user -> 
            new User(
            user.getUsername().toUpperCase(),
            user.getFirstname().toUpperCase(),
            user.getLastname().toUpperCase())
        );
	}

	public Flux<User> capitalizeMany(Flux<User> flux) {
		return flux.map(user -> new User(
            user.getUsername().toUpperCase(),
            user.getFirstname().toUpperCase(),
            user.getLastname().toUpperCase())
        );
	}

	public Flux<User> asyncCapitalizeMany(Flux<User> flux) {
		return flux.flatMap(user -> capitalizeOne(Mono.just(user)));
	}

	public Mono<User> asyncCapitalizeUser(User u) {
		return Mono.just(new User(u.getUsername().toUpperCase(), 
			u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
	}
	
	public static void main(String[] args) {
		MapAndFlatMap klass = new MapAndFlatMap();
		
		User user = klass.new User("username", "firstname", "lastname");
		User user2 = klass.new User("d", "f", "g");
		List<User> users = Arrays.asList(user, user2);
		
		klass.capitalizeOne(Mono.just(user))
			.subscribe(u -> 
				System.out.println("user [capitalizeOne]: " + u));
		System.out.println();
		
		klass.capitalizeMany(Flux.fromIterable(users))
			.subscribe(u -> 
				System.out.println("user [capitalizeMany]: " + u));
		System.out.println();
		
		klass.asyncCapitalizeMany(Flux.fromIterable(users))
			.subscribe(u -> 
				System.out.println("user [asyncCapitalizeMany]: " + u));
	}
}










	

