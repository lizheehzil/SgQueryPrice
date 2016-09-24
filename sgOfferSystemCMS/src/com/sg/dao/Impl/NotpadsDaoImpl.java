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

import com.sg.dao.NotpadsDao;
import com.sg.dao.UtilDao;
import com.sg.model.Brand;
import com.sg.model.Itemvo;
import com.sg.model.Notpads;

@Repository
public class NotpadsDaoImpl implements NotpadsDao {
	@Autowired
	private UtilDao utilDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	final int pageSize = 8;
	
	public int isExist(String pno){
		String GETCOUNT = "select count(id) from notpads where pno='"+pno+"'";
		int count = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		if(count==0) return 0;
		if(count==1) return 1;
		return 1;
	}

	public int getCount() {
		String GETCOUNT = "select count(id) from notpads";
		int totalcount = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		return totalcount;
	}

	@Override
	public Pagination getAll(int pageNo,String term, String term2, String term3, String term4) {
		
		StringBuilder sb=new StringBuilder("select * from notpads where is_display =1 ");
		
		if(!term.equals("")) sb.append("and cpu like '%").append(term+"%' ");
		
		if(!term2.equals("")) sb.append("and graphics_card like '%").append(term2+"%' ");
		
		if(!term3.equals("")) sb.append("and brandId ='").append(term3+"' ");
		
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end,jd_price "+term4+" limit ?,?");
		String GET_ALL=sb.toString();
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
						if(utilDao.hascode(u.getRemark())==1){
							u.setIs_display(3);
						}
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
	public int importNotspads(List<Notpads> list) {
		String ADD = "insert into notpads (id,pno,brandId,category_id,model,configuration_code,"
				+ "CPU,graphics_card,memories,screen_size,disk,keypad_backlight,"
				+ "Cd_drive,M_2,MSATA,standard_price,unstandard_price,guide_price,mini_price,"
				+ "jd_price,create_time,update_time,remark,is_display)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String add="insert into parts_index (id,pno,category_id,model) values(?,?,?,?)";
		for (Notpads notpads : list) {
			if(isExist(notpads.getPno())!=0){
				deleteNotpads(notpads.getPno());
			}
			Object[] args = new Object[] { notpads.getId(), notpads.getPno(),
					notpads.getBrandId(),notpads.getCategory_id(), notpads.getModel(),
					notpads.getConfiguration_code(), notpads.getCPU(),
					notpads.getGraphics_card(), notpads.getMemories(),
					notpads.getScreen_size(), notpads.getDisk(),
					notpads.getKeypad_backlight(), notpads.getCd_drive(),
					notpads.getM_2(), notpads.getMSATA(),
					notpads.getStandard_price(), notpads.getUnstandard_price(),
					notpads.getGuide_price(), notpads.getMini_price(),
					notpads.getJd_price(),
					notpads.getCreate_time(), notpads.getUpdate_time(),
					notpads.getRemark(), notpads.getIs_display() };
			int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR,
					Types.INTEGER,Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
					Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER,
					Types.DOUBLE, Types.DOUBLE, Types.DOUBLE, Types.DOUBLE,
					Types.DOUBLE, Types.VARCHAR, Types.VARCHAR,
					Types.VARCHAR, Types.INTEGER };
			Object[] args1=new Object[]{notpads.getId(),notpads.getPno(),notpads.getCategory_id(),
					"Notpads"};
			int[] arg1Types = new int[]{Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.VARCHAR};
			jdbcTemplate.update(add,args1,arg1Types);
			jdbcTemplate.update(ADD, args, argTypes);
			DataSource dataSource = jdbcTemplate.getDataSource();
			try {
				dataSource.getConnection().close();
			} catch (SQLException e) {
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
	public Pagination searchnotpads(int pageNo, String term1,String term4) {
		
		
		StringBuilder sb=new StringBuilder("select * from notpads where is_display =1 ");
		
		if(!term1.equals("")){
			int term;
			try {
				term = Integer.parseInt(term1);
				sb.append("and cpu like '%").append(term1+"%' or model like '%")
				.append(term1+"%' or graphics_card like '%").append(term1+"%' or pno like '%").
				append(term1+"%' or brandId=").append(term+" ");
			} catch (NumberFormatException e) {
				sb.append("and cpu like '%").append(term1+"%' or model like '%")
				.append(term1+"%' or graphics_card like '%").append(term1+"%' or pno like '%").
				append(term1+"%' ");
			}
			
			}
		
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		int fromIndex = (pageNo - 1) * pageSize;
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end,jd_price "+term4+" limit ?,?");
		String GET_ALL=sb.toString();
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
						if(utilDao.hascode(u.getRemark())==1){
							u.setIs_display(3);
						}
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
	public Notpads getnotpads(Integer id) {
		String sql="SELECT * FROM notpads WHERE id="+id;	
		List<Notpads> list = new ArrayList<Notpads>();
		jdbcTemplate.query(sql, 
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public Notpads getItem(String pno) {
		String sql="SELECT * FROM notpads WHERE pno = '"+pno+"'";	
		List<Notpads> list = new ArrayList<Notpads>();
		jdbcTemplate.query(sql, 
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public int saveNotpads(Notpads u) {
		String updateTime=utilDao.getCreatTime();
		String save="update notpads set model=?,configuration_code=?,CPU=?,graphics_card=?,"
				+ "memories=?,screen_size=?,disk=?,keypad_backlight=?,Cd_drive=?,M_2=?,MSATA=?,standard_price=?,"
				+ "unstandard_price=?,guide_price=?,mini_price=?,jd_price=?,remark=?,is_display=?,"
				+ "update_time=? where pno=?";
		int s=jdbcTemplate.update(save,new Object[]{u.getModel(),u.getConfiguration_code(),
				u.getCPU(),u.getGraphics_card(),u.getMemories(),u.getScreen_size(),u.getDisk(),
				u.getKeypad_backlight(),u.getCd_drive(),u.getM_2(),u.getMSATA(),
				u.getStandard_price(),u.getUnstandard_price(),u.getGuide_price(),u.getMini_price(),
				u.getJd_price(),u.getRemark(),u.getIs_display(),updateTime,u.getPno()});
		
		return s;
	}

	@Override
	public void deleteNotpads(String pno) {
		String DELETE="delete from notpads where pno=?";
		String sql="delete from parts_index where pno=?";
		jdbcTemplate.update(sql, pno);
		jdbcTemplate.update(DELETE, pno);
	}

	@Override
	public List<Brand> getBrand() {
		String sql="select id from category where name='笔记本'";
		
		Integer category_id;
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