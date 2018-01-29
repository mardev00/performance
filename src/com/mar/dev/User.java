package com.mar.dev;

import java.util.ArrayList;
import java.util.List;

class User {
	List<Order> orders;
	double total;
	
	public User() {
		orders = new ArrayList<>();
		for (int i=0; i<50; i++) {
			Order o = new Order();
			orders.add(o);

		}
	}
	
	public List<Order> getOrders() {
		return orders;
	}

}