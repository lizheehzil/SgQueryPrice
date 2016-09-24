package com.sg.dao;

import java.util.List;

import com.sg.model.Brand;
import com.sg.model.Key_mouse;

import cn.itcast.common.page.Pagination;

public interface Key_mouseDao {
	
	public Pagination searchkeymouse(int pageNo, String term, String term4);

	public Pagination getAll(int pageNo, String term, String term2);
	public int saveKeymouse(Key_mouse keymouse);

	public int importKey_mouse(List<Key_mouse> list);

	public Key_mouse getkeymouse(Integer id);
	public Key_mouse getItem(String pno);

	public void deleteKey_mouse(String pno);

	public List<Brand> getBrand();
}
