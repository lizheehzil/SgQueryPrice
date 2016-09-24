package com.sg.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.itcast.common.page.Pagination;

import com.sg.dao.Graphics_cardDao;
import com.sg.dao.UtilDao;
import com.sg.model.Brand;
import com.sg.model.Graphics_card;
import com.sg.model.Itemvo;

@Repository
public class Graphics_cardDaoImpl implements Graphics_cardDao{
	@Autowired
	private UtilDao utilDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	final int pageSize = 8;
	
	public int isExist(String pno){
		String GETCOUNT = "select count(id) from graphics_card where pno='"+pno+"'";
		int count = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		if(count==0) return 0;
		if(count==1) return 1;
		return 1;
	}
	
	public int getCount() {
		String GETCOUNT = "select count(id) from graphics_card";
		int totalcount = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		return totalcount;
	}
	
	
	@Override
	public Pagination getAll(int pageNo, String term, String term2, String term3) {
		
		StringBuilder sb=new StringBuilder("select * from graphics_card where is_display =1 ");
		if(!term.equals("")) sb.append("and model like '").append(term+"%' ");
		if(!term2.equals("")) sb.append("and brandId ='").append(term2+"' ");
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end limit ?,?");
		String GET_ALL=sb.toString();
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
						if(utilDao.hascode(u.getOther_spec())==1){
							u.setIs_display(3);
						}
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								u.getBrandId(),u.getCategory_id(),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),u.getId(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(), u.getIs_display(),u.getOther_spec());
								list.add(vo);
						//list.add(u);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}


	@Override
	public int importGraphics_card(List<Graphics_card> list) {
		String ADD = "insert into graphics_card (id,pno,brandId,category_id,model,capacity,"
				+ "display_core,cost_price,mini_price,inner_price,media_price,"
				+ "jd_price,vip_price,create_time,update_time,other_spec,is_display)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String add="insert into parts_index (id,pno,category_id,model) values(?,?,?,?)";
		for (Graphics_card graphics_card : list) {
			if(isExist(graphics_card.getPno())!=0){
				deleteGraphics_card(graphics_card.getPno());
			}
				Object[] args = new Object[] { graphics_card.getId(), graphics_card.getPno(),
						graphics_card.getBrandId(), graphics_card.getCategory_id(), graphics_card.getModel(),
						graphics_card.getCapacity(), graphics_card.getDisplay_core(),
						graphics_card.getCost_price(),graphics_card.getMini_price(), graphics_card.getInner_price(),
						graphics_card.getMedia_price(), graphics_card.getJd_price(),graphics_card.getVip_price(),
						graphics_card.getCreate_time(),graphics_card.getUpdate_time(), graphics_card.getOther_spec(), 
						graphics_card.getIs_display() };
				int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR,
						Types.INTEGER, Types.INTEGER, Types.VARCHAR,
						Types.DOUBLE,Types.VARCHAR,
						Types.DOUBLE, Types.DOUBLE, Types.DOUBLE, Types.DOUBLE,
						Types.DOUBLE, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER };
				Object[] args1=new Object[]{graphics_card.getId(),graphics_card.getPno(),
						graphics_card.getCategory_id(),"Graphics_card"};
				int[] arg1Types = new int[]{Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.VARCHAR};
				jdbcTemplate.update(add,args1,arg1Types);
				jdbcTemplate.update(ADD, args, argTypes);
				DataSource dataSource = jdbcTemplate.getDataSource();
				try {
					dataSource.getConnection().close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return 1;
	}
	public Integer getBrandId(String name) {
		String GET_ID = "select id from brand where name=?";
		Integer id = jdbcTemplate.queryForObject(GET_ID, new Object[] { name },
				Integer.class);
		if (id != null)
			return id;
		return -1;
	}
	@Override
	public Pagination searchgraphics(int pageNo, String term, String term4) {
		
		StringBuilder sb=new StringBuilder("select * from graphics_card where is_display =1 ");
		if(!term.equals("")) sb.append("and model like '").append(term+"%' ");
		
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end,jd_price "+term4+" limit ?,?");
		String GET_ALL=sb.toString();
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
						if(utilDao.hascode(u.getOther_spec())==1){
							u.setIs_display(3);
						}
						Itemvo vo=new Itemvo(utilDao.getBrandName(u.getBrandId()),
								u.getBrandId(),u.getCategory_id(),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),u.getId(),
								u.getModel(), u.getCost_price(), u.getMini_price(), 
								u.getMedia_price(), u.getIs_display(),u.getOther_spec());
								list.add(vo);
					}
				});
		Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
				list);
		return pagination;
	}

	@Override
	public Graphics_card getgraphics(Integer id) {
		String sql="SELECT * FROM graphics_card WHERE id="+id;	
		List<Graphics_card> list = new ArrayList<Graphics_card>();
		jdbcTemplate.query(sql,
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public Graphics_card getItem(String pno) {
		String sql="SELECT * FROM graphics_card WHERE pno='"+pno+"'";	
		List<Graphics_card> list = new ArrayList<Graphics_card>();
		jdbcTemplate.query(sql,
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public int saveGraphics(Graphics_card u) {
		String updateTime=utilDao.getCreatTime();
		String save="update graphics_card set model=?,capacity=?,display_core=?,cost_price=?,"
				+ "mini_price=?,inner_price=?,media_price=?,jd_price=?,other_spec=?,is_display=?,"
				+ "update_time=? where pno=?";
		int s=jdbcTemplate.update(save,new Object[]{u.getModel(),u.getCapacity(),
				u.getDisplay_core(),u.getCost_price(),u.getMini_price(),u.getInner_price(),u.getMedia_price(),
				u.getJd_price(),u.getOther_spec(),u.getIs_display(),updateTime,u.getPno()});
		
		return s;
	}

	@Override
	public void deleteGraphics_card(String pno) {
		String delete="delete from graphics_card where pno=?";
		jdbcTemplate.update(delete, pno);
		String sql="delete from parts_index where pno=?";
		jdbcTemplate.update(sql, pno);
	}

	@Override
	public List<Brand> getBrand() {
		String sql="select id from category where name='显卡'";
		int category_id;
		try {
			category_id = jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			category_id = 0;
			e.printStackTrace();
		}		
		String sql2="select * from brand where category_id="+category_id;
		List<Brand> list=new ArrayList<Brand>();
		jdbcTemplate.query(sql2, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet arg0) throws SQLException {
				// TODO Auto-generated method stub
				Brand brand=new Brand();
				brand.setId(arg0.getInt("id"));
				brand.setName(arg0.getString("name"));
				brand.setCategory_id(arg0.getInt("category_id"));
				list.add(brand);
			}
		});
		return list;
	}

}
