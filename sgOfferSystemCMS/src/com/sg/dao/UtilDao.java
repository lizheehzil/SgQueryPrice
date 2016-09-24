package com.sg.dao;

import java.util.List;

import com.sg.model.Brand;
import com.sg.model.Category;

public interface UtilDao {

	public Integer checkBrand(String name, int category_id);

	public Integer getBrandId(String name,int category_id);

	public String searchItem(String pno);

	public String getBrandName(Integer id);

	public String getCategoryName(Integer id);

	public double getDouble(String capacity);

	public String getCreatTime();

	public Integer setRandomId();

	public Integer getCategoryId(String name);

	public Integer getCategoryPID(String name);

	public Integer getCategoryPID(Integer id);

	public List<Brand> getAllBrand();

	public List<Category> getAllCategory();

	public int hascode(String remark);
}
