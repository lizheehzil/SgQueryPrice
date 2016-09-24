package com.sg.dao;

import com.sg.model.Worker;

public interface WorkerDao {
	
	public Worker login(String name,String pwd,int position);
	
	public int logout();
}
