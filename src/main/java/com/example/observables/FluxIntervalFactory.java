package com.example.observables;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import reactor.core.publisher.Flux;

public class FluxIntervalFactory {
	private static Logger log = LoggerFactory.getLogger(FluxIntervalFactory.class);
	public static void main (String ...args) {
		Flux<Long> time = Flux.interval(Duration.ofMillis(100))
			.map(tick -> {
				System.out.println("tick");
				return tick.longValue()+1L;
			});
		sleep(90);
		
		List<String> data = new ArrayList<String>(Arrays.asList("{A}", "{B}", "{C}"));
	    Flux<String> intervalFlux1 = Flux
	                  .interval(Duration.ofMillis(500))
	                  .map(tick -> {
	                    if (tick < data.size())
	                      return "item " + tick + ": " + data.get(tick.intValue());
	                    return "Done (tick == data.size())";
	                  });
	    
	    intervalFlux1.take(data.size() + 1).subscribe(System.out::println);
	    sleep(3000);
	    
	    System.out.println("=== from Collection using zipWithIterable() and map() ===");
	    Flux<String> intervalFlux2 = Flux
	                  .interval(Duration.ofMillis(500))
	                  .zipWithIterable(data)
	                  .map(source -> "item " + source.getT1() + ": " + source.getT2());
	 
	    intervalFlux2.subscribe(System.out::println);
	    sleep(3000);
	    
	    System.out.println("=== from Flux using zipWith() ===");
	    Flux<String> flux = Flux.just("{A}", "{B}", "{C}");
	    Flux<String> intervalFlux3 = Flux
	                  .interval(Duration.ofMillis(500))
	                  .zipWith(flux, (i, item) -> "item " + i + ": " + item);
	    
	    intervalFlux3.subscribe(System.out::println);
	    sleep(3000);


		System.out.println("Time Over");
	}
	
	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
