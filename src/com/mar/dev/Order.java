package com.mar.dev;

import java.util.ArrayList;
import java.util.List;

class Order {
	List<Item> items;
	
	public Order() {
		items = new ArrayList<>();
		for (int i=0; i<10; i++) {
			Item item = new Item();
			if (i % 2 == 0) {
				item.promotion = true;
				item.price = i * 3.5;
			} else {
				item.price = i * 2.7;
			}
			items.add(item);
			
		}
	}
	
	public List<Item> getItems() {
		return items;
	}
	
}
