package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.Graphics_card;

public interface Graphics_cardDao {
	public Pagination searchgraphics(int pageNo, String term, String term4);
	public Pagination getAll(int pageNo, String term, String term2, String term3);
	public int saveGraphics(Graphics_card graphics);
	public int importGraphics_card(List<Graphics_card> list);
	public Graphics_card getgraphics(Integer id);
	public Graphics_card getItem(String pno);
	public void deleteGraphics_card(String pno);
	public List<Brand> getBrand();
}
