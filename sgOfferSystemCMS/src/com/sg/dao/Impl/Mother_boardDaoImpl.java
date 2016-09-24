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

import com.sg.dao.Mother_boardDao;
import com.sg.dao.UtilDao;
import com.sg.model.Brand;
import com.sg.model.Itemvo;
import com.sg.model.Mother_board;

@Repository
public class Mother_boardDaoImpl implements Mother_boardDao {
	
	@Autowired
	private UtilDao utilDao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	final int pageSize = 8;

	public int isExist(String pno) {
		String GETCOUNT = "select count(id) from mother_board where pno='"
				+ pno + "'";
		int count = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		if (count == 0)
			return 0;
		if (count == 1)
			return 1;
		return 1;
	}

	public int getCount() {
		String GETCOUNT = "select count(id) from mother_board";
		int totalcount = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		return totalcount;
	}

	@Override
	public Pagination getAll(int pageNo, String term, String term2, String term3) {

		StringBuilder sb = new StringBuilder(
				"select * from mother_board where is_display =1 ");

		if (!term.equals("")) {
			sb.append("and hardware_spec like '%").append(term+"%' ");
		}
		if (!term2.equals(""))
			sb.append("and model like '%").append(term2 + "%' ");
		if (!term3.equals(""))
			sb.append("and brandId ='").append(term3 + "' ");
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end limit ?,?");
		String GET_ALL = sb.toString();
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list = new ArrayList<Itemvo>();
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
	public int importMother_board(List<Mother_board> list) {
		String ADD = "insert into mother_board (id,pno,brandId,category_id,model,hardware_spec,"
				+ "cost_price,mini_price,inner_price,media_price,"
				+ "jd_price,vip_price,create_time,update_time,remark,is_display)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String add="insert into parts_index (id,pno,category_id,model) values(?,?,?,?)";
		for (Mother_board mother_board : list) {
			if (isExist(mother_board.getPno()) != 0) {
				deleteBoard(mother_board.getPno());
			}
				Object[] args = new Object[] { mother_board.getId(), mother_board.getPno(),
						mother_board.getBrandId(), mother_board.getCategory_id(),
						mother_board.getModel(), mother_board.getHardware_spec(),
						mother_board.getCost_price(),
						mother_board.getMini_price(), mother_board.getInner_price(),
						mother_board.getMedia_price(), mother_board.getJd_price(),
						mother_board.getVip_price(), mother_board.getCreate_time(),
						mother_board.getUpdate_time(), mother_board.getRemark(),
						mother_board.getIs_display() };
				int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR,
						Types.INTEGER, Types.INTEGER, Types.VARCHAR,
						Types.VARCHAR, Types.DOUBLE,
						Types.DOUBLE, Types.DOUBLE, Types.DOUBLE, Types.DOUBLE,
						Types.DOUBLE, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER };
				Object[] args1=new Object[]{mother_board.getId(),mother_board.getPno(),
						mother_board.getCategory_id(),"Mother_board"};
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
	public Pagination searchboard(int pageNo, String term, String term4) {
		
		StringBuilder sb = new StringBuilder(
				"select * from mother_board where is_display =1 ");
		if(!term.equals("")) sb.append("and hardware_spec like '%").append(term+"%' or model like '%")
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
	public Mother_board getboard(Integer id) {
		String sql="SELECT * FROM mother_board WHERE id="+id;	
		List<Mother_board> list = new ArrayList<Mother_board>();
		jdbcTemplate.query(sql,new RowCallbackHandler() {
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
						list.add(u);
					}
				});
		
		return list.get(0);
	}

	@Override
	public Mother_board getItem(String pno) {
		String sql="SELECT * FROM mother_board WHERE pno='"+pno+"'";	
		List<Mother_board> list = new ArrayList<Mother_board>();
		jdbcTemplate.query(sql,new RowCallbackHandler() {
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
						list.add(u);
					}
				});
		
		return list.get(0);
	}

	@Override
	public int saveBoard(Mother_board u) {
		
		String updateTime=utilDao.getCreatTime();
		String save="update mother_board set model=?,hardware_spec=?,cost_price=?,"
				+ "mini_price=?,inner_price=?,media_price=?,jd_price=?,remark=?,is_display=?,"
				+ "update_time=? where pno=?";
		int s=jdbcTemplate.update(save,new Object[]{u.getModel(),u.getHardware_spec(),
				u.getCost_price(),u.getMini_price(),u.getInner_price(),u.getMedia_price(),
				u.getJd_price(),u.getRemark(),u.getIs_display(),updateTime,u.getPno()});
		
		return s;
	}

	@Override
	public void deleteBoard(String pno) {
		String delete="delete from mother_board where pno=?";
		jdbcTemplate.update(delete, pno);
		String sql="delete from parts_index where pno=?";
		jdbcTemplate.update(sql, pno);
	}

	@Override
	public List<Brand> getBrand() {
		String sql="select id from category where name='主板'";
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
