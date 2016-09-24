package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.Brand;
import com.sg.model.Disk;

public interface DiskDao {
	public Pagination searchdisk(int pageNo, String term, String term4);
	public Pagination getAll(int pageNo, String term, String term2, String term3);

	public int saveDisk(Disk disk);
	public int importDisk(List<Disk> list);
	public Disk getdisk(Integer id);
	public Disk getItem(String pno);
	public void deleteDisk(String pno);
	public List<Brand> getBrand();
}
