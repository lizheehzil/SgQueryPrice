package com.sg.service.Impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sg.dao.WorkerDao;
import com.sg.model.Worker;
import com.sg.service.WokerService;

@Service("workerService")
public class WorkerServiceImpl implements WokerService {

	@Autowired
	private WorkerDao workerDao;
	
	@Override
	public Worker login(String name, String pwd,int position) {
		Worker a=workerDao.login(name, pwd,position);
		return a;
	}

	@Override
	public int logout() {
		
		return 0;
	}

}
