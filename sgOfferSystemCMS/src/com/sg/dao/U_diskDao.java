package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.U_disk;

public interface U_diskDao {
	public Pagination getAll(int pageNo, String term, String term2, String term3);
	public Pagination searchudisk(int pageNo, String term, String term4);
	public int saveUdisk(U_disk udisk);
	public int importU_disk(List<U_disk> list);
	public U_disk getItem(String pno);
	public void deleteU_disk(String pno);
	public U_disk getudisk(Integer id);
	public List<Brand> getBrand();
}
