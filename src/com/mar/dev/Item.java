package com.mar.dev;

interface ItemIf {
	public double  getPrice();
}

class Item implements ItemIf{
	boolean promotion;
	double price;
	
	public double  getPrice() {
		return price;
	}
}
	