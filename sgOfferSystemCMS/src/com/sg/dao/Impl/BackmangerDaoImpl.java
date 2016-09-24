package com.sg.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.itcast.common.page.Pagination;

import com.sg.dao.BackmangerDao;
import com.sg.dao.UtilDao;
import com.sg.model.*;

@Repository
public class BackmangerDaoImpl implements BackmangerDao {
	
	@Autowired
	private UtilDao utilDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int getCount(String name) {
		String GETCOUNT = "select count(id) from "+name;
		int totalcount = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		return totalcount;
	}
	
	final int pageSize = 8;
	
	@Override
	public Pagination getAll(int pageNo) {
		String GET_ALL=("select * from notpads order by case when remark like '%推荐%' then 0 else 1 end,jd_price limit ?,?");
		int totalCount = getCount("notpads");
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();
		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Notpads u = new Notpads(rs.getInt("id"), rs
								.getString("pno"), rs.getInt("brandId"),rs.getInt("category_id"), rs
								.getString("model"), rs
								.getString("configuration_code"), rs
								.getString("CPU"), rs
								.getString("graphics_card"), rs
								.getString("memories"), rs
								.getString("screen_size"),
								rs.getString("disk"), rs
										.getString("keypad_backlight"), rs
										.getString("cd_drive"), rs
										.getInt("m_2"), rs.getInt("mSATA"), rs
										.getDouble("standard_price"), rs
										.getDouble("unstandard_price"), rs
										.getDouble("guide_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("jd_price"),  rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getCategory_id(),u.getPno(),u.getId(),
								u.getModel(), u.getStandard_price(),u.getUnstandard_price(),
								u.getGuide_price(),u.getMini_price(), u.getJd_price(),
								u.getIs_display(),u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllcpu(int pageNo) {
		String GET_ALL=("select * from cpu limit ?,?");
		//System.out.println(GET_ALL);
		int totalCount = getCount("cpu");
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();
		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						CPU u = new CPU(rs.getInt("id"), rs.getString("pno"),
								rs.getInt("brandId"), rs.getInt("category_id"),
								rs.getString("model"), rs
										.getString("match_board"), rs
										.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(), u.getIs_display(), u.getRemark());
						
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAlldisk(int pageNo) {
		
		String GET_ALL=("select * from disk limit ?,?");
		//System.out.println(GET_ALL);
		int totalCount = getCount("disk");
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();
		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Disk u = new Disk(rs.getInt("id"), rs.getString("pno"),
								rs.getInt("brandId"),rs.getString("model"), rs.getInt("category_id"),
								rs.getString("interface_type_id"),
								rs.getDouble("capacity"),
								 rs.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(),u.getIs_display(), u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllgraphics(int pageNo) {
		String GET_ALL=("select * from graphics_card limit ?,?");
		int totalCount = getCount("graphics_card");
		int fromIndex = (pageNo - 1) * pageSize;

		List<Itemvo> list=new ArrayList<Itemvo>();
		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Graphics_card u = new Graphics_card(rs.getInt("id"), rs.getString("pno"),
								rs.getInt("brandId"), rs.getInt("category_id"),rs.getString("model"),
								rs.getDouble("capacity"),rs.getString("display_core"),
								 rs.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("other_spec"), rs
										.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(),u.getIs_display(), u.getOther_spec());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllkeymouse(int pageNo) {
		String GET_ALL=("select * from key_mouse limit ?,?");
		int totalCount = getCount("key_mouse");
		int fromIndex = (pageNo - 1) * pageSize;

		List<Itemvo> list=new ArrayList<Itemvo>();
		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Key_mouse u = new Key_mouse(rs.getInt("id"), rs.getString("pno"),rs.getInt("category_id"),
								rs.getInt("brandId"),rs.getInt("typeId"),rs.getString("model"), 
								 rs.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(),u.getIs_display(), u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllcasee(int pageNo) {
		String GET_ALL=("select * from casee limit ?,?");
		//System.out.println(GET_ALL);
		int totalCount = getCount("casee");
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();

		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Casee u = new Casee(rs.getInt("id"), rs.getString("pno"),rs.getInt("category_id"),
								rs.getInt("brandId"),rs.getString("model"), 
								 rs.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(), u.getIs_display(),u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllmemories(int pageNo) {
		String GET_ALL=("select * from memories_bar limit ?,?");
		//System.out.println(GET_ALL);
		int totalCount = getCount("memories_bar");
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();
		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Memories_bar u = new Memories_bar(rs.getInt("id"), rs.getString("pno"), rs.getInt("category_id"),
								rs.getInt("brandId"),rs.getString("model"),
								rs.getDouble("capacity"),
								 rs.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(), u.getIs_display(),u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllmonitor(int pageNo) {
		String GET_ALL=("select * from monitor limit ?,?");
		//System.out.println(GET_ALL);
		int totalCount = getCount("monitor");
		int fromIndex = (pageNo - 1) * pageSize;

		List<Itemvo> list=new ArrayList<Itemvo>();

		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Monitor u = new Monitor(rs.getInt("id"), rs.getString("pno"), rs.getInt("category_id"),
								rs.getInt("brandId"),rs.getString("model"),
								rs.getString("size"),
								rs.getString("panel"),
								 rs.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));

						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(),u.getIs_display(), u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllpower(int pageNo) {
		String GET_ALL=("select * from power limit ?,?");
		//System.out.println(GET_ALL);
		
		int totalCount = getCount("power");
		int fromIndex = (pageNo - 1) * pageSize;

		List<Itemvo> list=new ArrayList<Itemvo>();

		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Power u = new Power(rs.getInt("id"), rs.getString("pno"),
								 rs.getInt("category_id"),rs.getInt("brandId"),rs.getString("model"),
								rs.getDouble("power"),rs.getString("other_spec"),
								 rs.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));

						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(),u.getIs_display(), u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllradiator(int pageNo) {
		String GET_ALL = ("select * from radiator limit ?,?");
		int totalCount = getCount("radiator");
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();
		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Radiator u = new Radiator(rs.getInt("id"), rs
								.getString("pno"), rs.getInt("category_id"), rs
								.getInt("brandId"), rs.getString("model"), rs
								.getString("template"), rs
								.getDouble("cost_price"), rs
								.getDouble("mini_price"), rs
								.getDouble("inner_price"), rs
								.getDouble("media_price"), rs
								.getDouble("jd_price"), rs
								.getDouble("vip_price"), rs
								.getString("create_time"), rs
								.getString("update_time"), rs
								.getString("remark"), rs.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(),u.getIs_display(), u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAlludisk(int pageNo) {
		String GET_ALL=("select * from u_disk limit ?,?");
		//System.out.println(GET_ALL);
		int totalCount = getCount("u_disk");
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();

		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						U_disk u = new U_disk(rs.getInt("id"), rs.getString("pno"), rs.getInt("category_id"),
								rs.getInt("brandId"),rs.getString("model"),
								rs.getDouble("capacity"),
								 rs.getDouble("cost_price"), rs
										.getDouble("mini_price"), rs
										.getDouble("inner_price"), rs
										.getDouble("media_price"), rs
										.getDouble("jd_price"), rs
										.getDouble("vip_price"), rs
										.getString("create_time"), rs
										.getString("update_time"), rs
										.getString("remark"), rs
										.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(),u.getIs_display(), u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Pagination getAllboard(int pageNo) {
		String GET_ALL =("select * from mother_board limit ?,?");
		//System.out.println(GET_ALL);
		int totalCount = getCount("mother_board");
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();

		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Mother_board u = new Mother_board(rs.getInt("id"), rs
								.getString("pno"), rs.getInt("category_id"), rs
								.getInt("brandId"), rs.getString("model"), rs
								.getString("hardware_spec"), rs
								.getDouble("cost_price"), rs
								.getDouble("mini_price"), rs
								.getDouble("inner_price"), rs
								.getDouble("media_price"), rs
								.getDouble("jd_price"), rs
								.getDouble("vip_price"), rs
								.getString("create_time"), rs
								.getString("update_time"), rs
								.getString("remark"), rs.getInt("is_display"));
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(),u.getIs_display(), u.getRemark());
						list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

}
