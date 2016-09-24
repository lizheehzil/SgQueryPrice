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

import com.sg.dao.RadiatorDao;
import com.sg.dao.UtilDao;
import com.sg.model.Brand;
import com.sg.model.Itemvo;
import com.sg.model.Radiator;

@Repository
public class RadiatorDaoImpl implements RadiatorDao {
	@Autowired
	private UtilDao utilDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	final int pageSize = 8;

	public int isExist(String pno) {
		String GETCOUNT = "select count(id) from radiator where pno='" + pno
				+ "'";
		int count = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		if (count == 0)
			return 0;
		if (count == 1)
			return 1;
		return 1;
	}

	public int getCount() {
		String GETCOUNT = "select count(id) from radiator";
		int totalcount = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		return totalcount;
	}

	@Override
	public Pagination getAll(int pageNo, String term, String term2) {
		StringBuilder sb = new StringBuilder(
				"select * from radiator where is_display =1 ");
		if (!term.equals(""))
			sb.append("and template like '%").append(term + "%' ");
		if (!term2.equals(""))
			sb.append("and brandId ='").append(term2 + "' ");
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end limit ?,?");
		String GET_ALL = sb.toString();
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();
		//List<Radiator> list = new ArrayList<Radiator>();
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
						if(utilDao.hascode(u.getRemark())==1){
							u.setIs_display(3);
						}

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
	public int importRadiator(List<Radiator> list) {
		String ADD = "insert into radiator (id,pno,brandId,category_id,model,template,"
				+ "cost_price,mini_price,inner_price,media_price,"
				+ "jd_price,vip_price,create_time,update_time,remark,is_display)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String add="insert into parts_index (id,pno,category_id,model) values(?,?,?,?)";
		for (Radiator radiator : list) {
			if(isExist(radiator.getPno())!=0){
			deleteRadiator(radiator.getPno());
			}
				Object[] args = new Object[] { radiator.getId(), radiator.getPno(),
						radiator.getBrandId(), radiator.getCategory_id(), radiator.getModel(),
						radiator.getTemplate(), 
						radiator.getCost_price(),radiator.getMini_price(), radiator.getInner_price(),
						radiator.getMedia_price(), radiator.getJd_price(),radiator.getVip_price(),
						radiator.getCreate_time(),radiator.getUpdate_time(), radiator.getRemark(), 
						radiator.getIs_display() };
				int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR,
						Types.INTEGER, Types.INTEGER, Types.VARCHAR,
						Types.VARCHAR,
						Types.DOUBLE, Types.DOUBLE, Types.DOUBLE, Types.DOUBLE,
						Types.DOUBLE, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER };
				Object[] args1=new Object[]{radiator.getId(),radiator.getPno(),radiator.getCategory_id(),"Radiator"};
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
	public Pagination searchradiator(int pageNo, String term, String term4) {
		StringBuilder sb = new StringBuilder(
				"select * from radiator where is_display =1 ");
		
		if(!term.equals("")) sb.append("and template like'%").append(term+"%' or model like '%")
		.append(term+"%' ");
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end,jd_price "+term4+" limit ?,?");
		String GET_ALL = sb.toString();
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
						if(utilDao.hascode(u.getRemark())==1){
							u.setIs_display(3);
						}

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
	public Radiator getradiator(Integer id) {
		String sql="SELECT * FROM radiator WHERE id="+id;	
		List<Radiator> list = new ArrayList<Radiator>();
		jdbcTemplate.query(sql, 
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public Radiator getItem(String pno) {
		String sql="SELECT * FROM radiator WHERE pno='"+pno+"'";	
		List<Radiator> list = new ArrayList<Radiator>();
		jdbcTemplate.query(sql, 
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public int saveRadiator(Radiator u) {
		String updateTime=utilDao.getCreatTime();
		String save="update radiator set model=?,template=?,cost_price=?,"
				+ "mini_price=?,inner_price=?,media_price=?,jd_price=?,remark=?,is_display=?,"
				+ "update_time=? where pno=?";
		int s=jdbcTemplate.update(save,new Object[]{u.getModel(),u.getTemplate(),
				u.getCost_price(),u.getMini_price(),u.getInner_price(),u.getMedia_price(),
				u.getJd_price(),u.getRemark(),u.getIs_display(),updateTime,u.getPno()});
		
		return s;
	}

	@Override
	public void deleteRadiator(String pno) {
		String delete="delete from radiator where pno=?";
		String sql="delete from parts_index where pno=?";
		jdbcTemplate.update(sql, pno);
		jdbcTemplate.update(delete, pno);
	}

	@Override
	public List<Brand> getBrand() {
		String sql="select id from category where name='散热器'";
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
