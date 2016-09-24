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

import com.sg.dao.PowerDao;
import com.sg.dao.UtilDao;
import com.sg.model.Brand;
import com.sg.model.Itemvo;
import com.sg.model.Power;

@Repository
public class PowerDaoImpl implements PowerDao {
	@Autowired
	private UtilDao utilDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	final int pageSize = 8;
	
	public int isExist(String pno){
		String GETCOUNT = "select count(id) from power where pno='"+pno+"'";
		int count = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		if(count==0) return 0;
		if(count==1) return 1;
		return 1;
	}
	
	public int getCount() {
		String GETCOUNT = "select count(id) from power";
		int totalcount = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		return totalcount;
	}
	
	@Override
	public Pagination getAll(int pageNo, String term, String term2) {
				StringBuilder sb=new StringBuilder("select * from power where is_display =1 ");
				if(!term.equals("")) sb.append("and power ").append(term+" ");
				if(!term2.equals("")) sb.append("and brandId ='").append(term2+"' ");
				int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
				sb.append("limit ?,?");
				String GET_ALL=sb.toString();
				int fromIndex = (pageNo - 1) * pageSize;
				List<Itemvo> list=new ArrayList<Itemvo>();
				///List<Power> list = new ArrayList<Power>();
				jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
						new RowCallbackHandler() {

							@Override
							public void processRow(ResultSet rs) throws SQLException {
								Power u = new Power(rs.getInt("id"), rs.getString("pno"),
										rs.getInt("category_id"),rs.getInt("brandId"), rs.getString("model"),
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
										u.getBrandId(),u.getCategory_id(),
										utilDao.getCategoryName(u.getCategory_id()),u.getPno(),u.getId(),
										u.getModel(), u.getCost_price(), u.getMini_price(), 
										u.getMedia_price(), u.getIs_display(),u.getRemark());
										list.add(vo);
								//list.add(u);
							}
						});
				Pagination pagination = new Pagination(pageNo, pageSize, totalCount,
						list);
				return pagination;
	}


	@Override
	public int importPower(List<Power> list) {
		String ADD = "insert into power (id,pno,brandId,category_id,model,power,"
				+ "other_spec,cost_price,mini_price,inner_price,media_price,"
				+ "jd_price,vip_price,create_time,update_time,remark,is_display)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String add="insert into parts_index (id,pno,category_id,model) values(?,?,?,?)";
		for (Power power : list) {
			if(isExist(power.getPno())!=0){
				deletePower(power.getPno());
			}
				Object[] args = new Object[] { power.getId(), power.getPno(),
						power.getBrandId(), power.getCategory_id(), power.getModel(),
						power.getPower(), power.getOther_spec(),
						power.getCost_price(),power.getMini_price(), power.getInner_price(),
						power.getMedia_price(), power.getJd_price(),power.getVip_price(),
						power.getCreate_time(),power.getUpdate_time(), power.getRemark(), 
						power.getIs_display() };
				int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR,
						Types.INTEGER, Types.INTEGER, Types.VARCHAR,
						Types.DOUBLE,Types.VARCHAR,
						Types.DOUBLE, Types.DOUBLE, Types.DOUBLE, Types.DOUBLE,
						Types.DOUBLE, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER };
				Object[] args1=new Object[]{power.getId(),power.getPno(),power.getCategory_id(),"Power"};
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
	public Pagination searchpower(int pageNo, String term, String term4) {
		
		StringBuilder sb=new StringBuilder("select * from power where is_display =1 ");
		if(!term.equals("")) sb.append("and power ='").append(term+"' or model like '%")
		.append(term+"%' ");
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end,jd_price "+term4+" limit ?,?");
		String GET_ALL=sb.toString();
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();
		jdbcTemplate.query(GET_ALL, new Object[] { fromIndex, pageSize },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Power u = new Power(rs.getInt("id"), rs.getString("pno"),
								rs.getInt("category_id"),rs.getInt("brandId"), rs.getString("model"),
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
								u.getBrandId(),u.getCategory_id(),
								utilDao.getCategoryName(u.getCategory_id()),u.getPno(),u.getId(),
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
	public Power getpower(Integer id) {
		String sql="SELECT * FROM power WHERE id="+id;	
		List<Power> list = new ArrayList<Power>();
		jdbcTemplate.query(sql, 
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
						list.add(u);
					}
				});
		return list.get(0);
		
	}

	@Override
	public Power getItem(String pno) {
		String sql="SELECT * FROM power WHERE pno='"+pno+"'";	
		List<Power> list = new ArrayList<Power>();
		jdbcTemplate.query(sql, 
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Power u = new Power(rs.getInt("id"), rs.getString("pno"),
								rs.getInt("category_id"),rs.getInt("brandId"), rs.getString("model"),
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public int savePower(Power u) {
		String updateTime=utilDao.getCreatTime();
		String save="update power set model=?,power=?,cost_price=?,"
				+ "mini_price=?,inner_price=?,media_price=?,jd_price=?,remark=?,is_display=?,"
				+ "update_time=? where pno=?";
		int s=jdbcTemplate.update(save,new Object[]{u.getModel(),u.getPower(),
				u.getCost_price(),u.getMini_price(),u.getInner_price(),u.getMedia_price(),
				u.getJd_price(),u.getRemark(),u.getIs_display(),updateTime,u.getPno()});
		
		return s;
	}

	@Override
	public void deletePower(String pno) {
		String delete="delete from power where pno=?";
		jdbcTemplate.update(delete, pno);
		String sql="delete from parts_index where pno=?";
		jdbcTemplate.update(sql, pno);
	}

	@Override
	public List<Brand> getBrand() {
		String sql="select id from category where name='电源'";
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
