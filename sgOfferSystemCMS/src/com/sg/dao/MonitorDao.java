package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.Monitor;

public interface MonitorDao {
	public Pagination getAll(int pageNo, String term, String term2, String term3);
	public Pagination searchmonitor(int pageNo, String term, String term4);
	public int saveMonitor(Monitor monitor);
	public int importMonitor(List<Monitor> list);
	public Monitor getmonitor(Integer id);
	public Monitor getItem(String pno);
	public void deleteMonitor(String pno);
	public List<Brand> getBrand();
}
