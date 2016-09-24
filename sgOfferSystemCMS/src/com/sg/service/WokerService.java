package com.sg.service;

import com.sg.model.Worker;

public interface WokerService {
	
	public Worker login(String name,String pwd,int position);
	
	public int logout();
}
