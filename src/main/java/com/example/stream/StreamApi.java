package com.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApi {
	public static void main(String[] args) {
		List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> list2 = list1.stream().map(i -> i+=2).collect(Collectors.toList());
		System.out.println("Can we modify Collection in place?");
		list1 = list1.stream().map(i -> i+=2).collect(Collectors.toList());
		list2.forEach(i -> System.out.println("LIST2: " + i));
		System.out.println("Yes");
		list1.forEach(i -> System.out.println("LIST1: " + i));
	}
}
