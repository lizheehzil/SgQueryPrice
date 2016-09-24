package com.sg.dao;

import java.util.List;

import com.sg.model.Brand;
import com.sg.model.Power;

import cn.itcast.common.page.Pagination;

public interface PowerDao {
	
	public Pagination getAll(int pageNo, String term, String term2);

	public Pagination searchpower(int pageNo, String term, String term4);

	public int savePower(Power power);

	public int importPower(List<Power> list);

	public Power getpower(Integer id);

	public Power getItem(String pno);

	public void deletePower(String pno);

	public List<Brand> getBrand();

}
