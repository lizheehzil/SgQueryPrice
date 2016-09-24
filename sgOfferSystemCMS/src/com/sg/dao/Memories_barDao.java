package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.Memories_bar;

public interface Memories_barDao {
	public Pagination getAll(int pageNo, String term, String term2, String term3);
	public Pagination searchmemories(int pageNo, String term, String term4);
	public int saveMemories(Memories_bar memories);
	public int importMemories_bar(List<Memories_bar> list);
	public Memories_bar getmemories(Integer id);
	public Memories_bar getItem(String pno);
	public void deleteMemories_bar(String pno);
	public List<Brand> getBrand();
}
