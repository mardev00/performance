package com.mar.dev;

import java.util.ArrayList;
import java.util.List;

public class Loader extends Thread {
	
	private final int numUsers;
	
	public List<User> users = new ArrayList<>();
	
	public List<User> getUsers() throws InterruptedException {
		while(users.size()<numUsers/4) {
			Thread.sleep(5);

		}
		return users;
	}
	
	public Loader(int numUsers) {
		this.numUsers = numUsers;
	}
	
	public void run() {
		//Generate 10000 users
				System.out.println("Loading " + numUsers + " users");
				for (int i=0; i<numUsers; i++) {
					User u = new User();
					users.add(u);
				}
		
	}

}
