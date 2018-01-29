package com.mar.dev;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStream {

	public static void main(String[] args) {
		int numUsers = args.length > 0 && args[0] != null ? Integer.parseInt(args[0]) : 10000;
		int sleep1 = args.length > 1 && args[1] != null ? Integer.parseInt(args[1]) : 30000;
		int sleep2 = args.length > 2 && args[2] != null ? Integer.parseInt(args[2]) : 2000;
		try {
			System.out.println("Waiting for " + sleep1 );
			System.out.println("Run jcmd to get pid, then jconsole + pid, jstack +pid");
			Thread.sleep(sleep1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ParallelStream example = new ParallelStream();
		Loader loader1 = new Loader(numUsers/8);
		Loader loader2 = new Loader(numUsers/8);
		Loader loader3 = new Loader(numUsers/8);
		Loader loader4 = new Loader(numUsers/8);
		Loader loader5 = new Loader(numUsers/8);
		Loader loader6 = new Loader(numUsers/8);
		Loader loader7 = new Loader(numUsers/8);
		Loader loader8 = new Loader(numUsers/8);
		loader1.start();
		loader2.start();
		loader3.start();
		loader4.start();
		loader5.start();
		loader6.start();
		loader7.start();
		loader8.start();

		try {
//			Stream<User> combinedStream = Stream.of(loader1.getUsers(), loader2.getUsers(),
//					loader3.getUsers(), loader4.getUsers(), loader5.getUsers(), loader6.getUsers(), loader7.getUsers(), loader8.getUsers())
//					  .flatMap(Collection::stream);
//					List<User> users = 
//					  combinedStream.collect(Collectors.toList());
			List<User> users = new ArrayList<>();
			users.addAll(loader1.getUsers());
			users.addAll(loader2.getUsers());
			users.addAll(loader3.getUsers());
			users.addAll(loader4.getUsers());
			users.addAll(loader5.getUsers());
			users.addAll(loader6.getUsers());
			users.addAll(loader7.getUsers());
			users.addAll(loader8.getUsers());
			
			Thread.sleep(sleep2);
			
			System.out.println("Total functional: " + example.calculateFunctionalUsers(Collections.synchronizedList(users)));
	
			
						//System.out.println("Total functional: " + example.calculateFunctionalUsers(combinedStream));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	public <T> double mapSum(List<T> entries, Function<T, Integer> mapper) {
		double sum = 0.0;
		for (T entry : entries) {
			sum += mapper.apply(entry);
		}
		return sum;
		
	}
	
	/**
	 * List<Result> results = ...
List<TransformedItem> newItems = results.stream()
     .map(result -> result.getItems())
     .flatMap(List::stream)
     .map(TransformedItem::new)
     .collect(toList());
	 * @param orders
	 * @return
	 */
	
	/**
	 * 
        //1. Stream<int[]>
        Stream<int[]> streamArray = Stream.of(intArray);

        //2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

        intStream.forEach(x -> System.out.println(x));
	 * @param orders
	 * @return
	 */
	
	public double calculateFunctional(List<Order> orders) {
		//Stream<Order> s = orders.stream().map(o -> o.getItems());
		System.out.println("START");
		long start = System.currentTimeMillis();
		double result = orders.stream()
		.map(o -> o.getItems())
		.flatMap(List::stream)
		.distinct()
		.mapToDouble(Item::getPrice)
		.sum();
		//.collect(Collectors.toList());
		//items.mapSum(items, Item::getPrice);
		long end = System.currentTimeMillis();
		long perf = end - start;
		System.out.println("DONE IN " + perf);
		return result;
		
		
				

		//return 0.0;
}
	
	public double calculateFunctionalStrem(Stream<Order> orders) {
		//Stream<Order> s = orders.stream().map(o -> o.getItems());
		System.out.println("START");
		long start = System.currentTimeMillis();
		double result = orders
				.parallel()
		.map(o -> o.getItems())
		.flatMap(List::stream)
		.distinct()
		.mapToDouble(Item::getPrice)
		.sum();
		//.collect(Collectors.toList());
		//items.mapSum(items, Item::getPrice);
		long end = System.currentTimeMillis();
		long perf = end - start;
		System.out.println("DONE IN " + perf);
		return result;
		
		
				

		//return 0.0;
}
	
	
	public double calculateFunctionalUsers(List<User> users) {
		System.out.println("---------------------------------");
		System.out.println("FUNCTIONAL START");
		long start = System.currentTimeMillis();
		//Stream<Order> s = orders.stream().map(o -> o.getItems());
		double result = users.stream().parallel()
				.map(u -> u.getOrders())
				.flatMap(List::stream)
		.map(o -> o.getItems())
		.flatMap(List::stream)
		//.distinct()
		.mapToDouble(Item::getPrice)
		.sum();
		long end = System.currentTimeMillis();
		long perf = end - start;
		System.out.println("FUNCTIONAL DONE IN " + perf);
		System.out.println("---------------------------------");
		return result;
		
		
				

		//return 0.0;
}
	
	
	public double calculateFunctionalUsers(Stream<User> users) {
		System.out.println("START");
		long start = System.currentTimeMillis();
		//Stream<Order> s = orders.stream().map(o -> o.getItems());
		double result = users.parallel()
				.map(u -> u.getOrders())
				.flatMap(List::stream)
		.map(o -> o.getItems())
		.flatMap(List::stream)
		.distinct()
		.mapToDouble(Item::getPrice)
		.sum();
		long end = System.currentTimeMillis();
		long perf = end - start;
		System.out.println("DONE IN " + perf);
		return result;
		
		
				

		//return 0.0;
}

public <T> List<T> flattenListOfListsStream(List<List<T>> list) {
    return list.stream()
      .flatMap(Collection::stream)
      .collect(Collectors.toList());    
}

}






//class Promotion {
//	
//}




