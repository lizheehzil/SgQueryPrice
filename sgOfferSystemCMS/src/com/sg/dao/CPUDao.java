package com.sg.dao;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.sg.model.CPU;

public interface CPUDao {
	public Pagination searchcpu(int pageNo,String term, String term4);
	public Pagination getAll(int pageNo,String term,String term2);
	public int saveCPU(CPU cpu); 
	public int importCPU(List<CPU> list);
	public CPU getcpu(Integer id);
	public CPU getItem(String pno);
	public void deleteCPU(String pno);
}
