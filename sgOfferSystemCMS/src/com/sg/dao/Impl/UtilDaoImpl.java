package com.sg.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.sg.dao.UtilDao;
import com.sg.model.Brand;
import com.sg.model.Category;

@Repository
public class UtilDaoImpl implements UtilDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int hascode(String remark) {
		String str="推荐";
		try {
			if(remark.indexOf(str)>=0){
				return 1;
			}
		} catch (Exception e) {
			
		}
		return 0;
	}
	
	@Override
	public String searchItem(String pno) {
		String sql="select model from parts_index where pno=?";
		String className=jdbcTemplate.queryForObject(sql,
				new Object[] {pno}, String.class);
		return className;
	}
	
	
	
	
	@Override
	public Integer checkBrand(String name,int category_id){
		//System.out.println(category_id);
		String GET_ID = "select id from brand where name=? and category_id=?";
		String NEW ="insert into brand (id,name,category_id) values(?,?,?)";
		Integer id;
		try {
			id = jdbcTemplate.queryForObject(GET_ID, new Object[] { name,category_id },
					Integer.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			jdbcTemplate.update(NEW,new Object[]{setRandomId(),name,category_id},
					new int[]{Types.INTEGER,Types.VARCHAR,Types.INTEGER});
		}
			
		return 1;
	}
	
	@Override
	public Integer getBrandId(String name,int category_id) {
		
		String GET_ID = "select id from brand where name=? and category_id=?";
		
		Integer id = jdbcTemplate.queryForObject(GET_ID, new Object[] { name,category_id },
				Integer.class);
		
		if (id != null)
			return id;
		return -1;
	}

	@Override
	public double getDouble(String string) {
		if (string.equals("") || string == null)
			return 0;
		else
			try {
				return new Double(string);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return 0;
				
			}
	}

	@Override
	public String getCreatTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String creattime = sdf.format(date);
		return creattime;
	}

	@Override
	public Integer setRandomId() {
		Integer id = new Random().nextInt(99999);
		return id;
	}

	@Override
	public Integer getCategoryId(String name) {
		String GET_ID = "select id from category where name like '%"+name+"%'";
		System.out.println("!!!!!!!!!!!!!!!!!"+name);
		Integer id;
		try {
			id = jdbcTemplate.queryForObject(GET_ID, Integer.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return -1;
		}
		return id;
	}

	@Override
	public Integer getCategoryPID(String name) {
		String GET_ID = "select PID from category where name=?";
		Integer PID = jdbcTemplate.queryForObject(GET_ID,
				new Object[] { name }, Integer.class);
		if (PID != null)
			return PID;
		return -1;
	}

	@Override
	public Integer getCategoryPID(Integer id) {
		String GET_ID = "select PID from category where id=?";
		Integer PID = jdbcTemplate.queryForObject(GET_ID, new Object[] { id },
				Integer.class);
		if (PID != null)
			return PID;
		return -1;
	}

	@Override
	public String getBrandName(Integer id) {
		String GET_NAME = "select name from brand where id=?";
		String name = jdbcTemplate.queryForObject(GET_NAME,
				new Object[] { id }, String.class);
		if (name != null)
			return name;
		return "";
	}

	@Override
	public String getCategoryName(Integer id) {
		String GET_NAME = "select name from category where id=?";
		String name = jdbcTemplate.queryForObject(GET_NAME,
				new Object[] { id }, String.class);
		if (name != null)
			return name;
		return "";
	}

	@Override
	public List<Brand> getAllBrand() {
		String GET_ALL_USER = "SELECT * FROM brand";

		final List<Brand> list = new ArrayList<Brand>();
		jdbcTemplate.query(GET_ALL_USER, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Brand u = new Brand();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setCategory_id(rs.getInt("category_id"));
				list.add(u);
			}
		});
		return list;
	}

	@Override
	public List<Category> getAllCategory() {
		String GET_ALL_USER = "SELECT * FROM category";

		final List<Category> list = new ArrayList<Category>();
		jdbcTemplate.query(GET_ALL_USER, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Category u = new Category();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setPID(rs.getInt("PID"));
				list.add(u);
			}
		});
		return list;
	}


}
