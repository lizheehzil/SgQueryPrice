package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.Mother_board;

public interface Mother_boardDao {
	public Pagination getAll(int pageNo, String term, String term2, String term3);
	public Pagination searchboard(int pageNo, String term, String term4);
	public int saveBoard(Mother_board board);
	public int importMother_board(List<Mother_board> list);
	public Mother_board getboard(Integer id);
	public Mother_board getItem(String pno);
	public void deleteBoard(String pno);
	public List<Brand> getBrand();
}
