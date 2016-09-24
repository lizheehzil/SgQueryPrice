package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.Notpads;

public interface NotpadsDao {
	
	public Pagination getAll(int pageNo, String term, String term2, String term3, String term4);
	public Pagination searchnotpads(int pageNo, String term, String term4);
	public int saveNotpads(Notpads notpads);
	public int importNotspads(List<Notpads> list);
	public Notpads getnotpads(Integer id);
	public Notpads getItem(String pno);
	public void deleteNotpads(String pno);
	public List<Brand> getBrand();
	
}

