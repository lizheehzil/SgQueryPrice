package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.Radiator;

public interface RadiatorDao {
	public Pagination getAll(int pageNo, String term, String term2);
	public Pagination searchradiator(int pageNo, String term, String term4);
	public int saveRadiator(Radiator radiator);
	public int importRadiator(List<Radiator> list);
	public Radiator getradiator(Integer id);
	public Radiator getItem(String pno);
	public void deleteRadiator(String pno);
	public List<Brand> getBrand();
}
