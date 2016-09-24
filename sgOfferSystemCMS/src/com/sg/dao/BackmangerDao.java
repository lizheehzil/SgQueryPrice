package com.sg.dao;

import cn.itcast.common.page.Pagination;

public interface BackmangerDao {
	/**
	 * 后台得到笔记本列表
	 * @param pageNo
	 * @return 分页对象
	 */
	public Pagination getAll(int pageNo);

	public Pagination getAllcpu(int pageNo);

	public Pagination getAlldisk(int pageNo);

	public Pagination getAllgraphics(int pageNo);

	public Pagination getAllkeymouse(int pageNo);

	public Pagination getAllcasee(int pageNo);

	public Pagination getAllmemories(int pageNo);

	public Pagination getAllmonitor(int pageNo);

	public Pagination getAllpower(int pageNo);

	public Pagination getAllradiator(int pageNo);

	public Pagination getAlludisk(int pageNo);

	public Pagination getAllboard(int pageNo);
}
