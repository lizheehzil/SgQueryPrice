package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.Casee;

public interface CaseeDao {
	public Pagination searchcasee(int pageNo, String term, String term4);
	
	public Pagination getAll(int pageNo, String term, String term2);

	public int saveCasee(Casee casee);

	public int importCasee(List<Casee> list);

	public Casee getcasee(Integer id);

	public Casee getItem(String pno);

	public void deleteCasee(String pno);

	public List<Brand> getBrand();
}
