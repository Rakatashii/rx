package com.example.rx;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.function.SupplierUtils;

import com.example.transformation_operators.MapAndFlatMap;
import com.example.transformation_operators.MapAndFlatMap.User;

import io.reactivex.schedulers.Schedulers;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.test.scheduler.VirtualTimeScheduler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RxApplicationTests {

	@Test
	public void testStepVerifier() {
		MapAndFlatMap mfm = new MapAndFlatMap();
		
		StepVerifierOptions options = StepVerifierOptions.create();
		
		VirtualTimeScheduler vts = VirtualTimeScheduler.create();
		vts.createWorker();
		System.out.println("disposed? " + vts.createWorker());
		
		MapAndFlatMap.User user1 = mfm.new User("username", "firstname", "lastname");
		MapAndFlatMap.User user2 = mfm.new User("un", "fn", "ln");
		MapAndFlatMap.User user3 = mfm.new User("uname", "fname", "lname");
		MapAndFlatMap.User user4 = mfm.new User("usern", "firstn", "lastn");
		
		List<User> users = Arrays.asList(user1, user2, user3, user4);
		Flux<User> flux = Flux.fromIterable(users).doOnComplete(() -> {int i = 5/0;});

		StepVerifier.create(flux, options)
			.thenRequest(1L)
			.assertNext(u -> {
				assert(u.getFirstname().equals("firstname"));
		
			})
			.thenRequest(1L)
			//.thenRequest(0L).expectError() - NO: request(0) in method context, not within the stream itself
			.assertNext(u -> {
				assert(u.getFirstname().equals("fn"));
			})
			//.thenRequest(2L)
			//.expectAccessibleContext().then()
			.assertNext(n -> {}).assertNext(n -> {})
			.expectError()
			.verify();
	}

}
