package com.sg.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import cn.itcast.common.page.Pagination;

import com.sg.dao.CPUDao;
import com.sg.dao.UtilDao;
import com.sg.model.CPU;
import com.sg.model.Itemvo;

@Repository("cpuDaoImpl")
public class CPUDaoImpl implements CPUDao {
	@Autowired
	private UtilDao utilDao;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	final int pageSize = 8;
	
	public int isExist(String pno){
		String GETCOUNT = "select count(id) from cpu where pno='"+pno+"'";
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
	public Pagination getAll(int pageNo,String term,String term2) {
		
		StringBuilder sb=new StringBuilder("select * from cpu where is_display =1 ");
		if(!term.equals("")) sb.append("and model like '%").append(term+"%' ");
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end limit ?,?");
		String GET_ALL=sb.toString();
		int fromIndex = (pageNo - 1) * pageSize;
		List<Itemvo> list=new ArrayList<Itemvo>();
		//List<CPU> list = new ArrayList<CPU>();
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
						if(utilDao.hascode(u.getRemark())==1){
							u.setIs_display(3);
						}
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
	public int importCPU(List<CPU> list) {
		String ADD = "insert into cpu (id,pno,brandId,category_id,model,"
				+ "match_board,cost_price,mini_price,inner_price,media_price,"
				+ "jd_price,vip_price,create_time,update_time,remark,is_display)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String add="insert into parts_index (id,pno,category_id,model) values(?,?,?,?)";
		for (CPU cpu : list) {
			if(isExist(cpu.getPno())!=0){
				deleteCPU(cpu.getPno());
			}
				Object[] args = new Object[] { cpu.getId(), cpu.getPno(),
						cpu.getBrandId(), cpu.getCategory_id(), cpu.getModel(),
						cpu.getMatch_board(), cpu.getCost_price(),
						cpu.getMini_price(), cpu.getInner_price(),
						cpu.getMedia_price(), cpu.getJd_price(),
						cpu.getVip_price(), cpu.getCreate_time(),
						cpu.getUpdate_time(), cpu.getRemark(), cpu.getIs_display() };
				int[] argTypes = new int[] { Types.INTEGER, Types.VARCHAR,
						Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
						Types.DOUBLE, Types.DOUBLE, Types.DOUBLE, Types.DOUBLE,
						Types.DOUBLE, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER };
				Object[] args1=new Object[]{cpu.getId(),cpu.getPno(),cpu.getCategory_id(),"CPU"};
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
	public Pagination searchcpu(int pageNo, String term,String term4) {
		
		StringBuilder sb=new StringBuilder("select * from cpu where is_display =1 ");
		
		if(!term.equals("")) sb.append("and model like '%").append(term+"%' ");
		
		int totalCount =jdbcTemplate.queryForObject("select count(1) from ("+sb.toString()+") as a", Integer.class);
		
		sb.append("order by case when remark like '%推荐%' then 0 else 1 end,jd_price "+term4+" limit ?,?");
		
		String GET_ALL=sb.toString();
		//System.out.println(GET_ALL);
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
	public CPU getcpu(Integer id) {
		String sql="SELECT * FROM cpu WHERE id="+id;	
		List<CPU> list = new ArrayList<CPU>();
		jdbcTemplate.query(sql,
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public CPU getItem(String pno) {
		String sql="SELECT * FROM cpu WHERE pno='"+pno+"'";	
		List<CPU> list = new ArrayList<CPU>();
		jdbcTemplate.query(sql,new RowCallbackHandler() {
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
						list.add(u);
					}
				});
		return list.get(0);
	}

	@Override
	public int saveCPU(CPU u) {
		String updateTime=utilDao.getCreatTime();
		String save="update cpu set model=?,match_board=?,cost_price=?,"
				+ "mini_price=?,inner_price=?,media_price=?,jd_price=?,remark=?,is_display=?,"
				+ "update_time=? where pno=?";
		int s=jdbcTemplate.update(save,new Object[]{u.getModel(),u.getMatch_board(),
				u.getCost_price(),u.getMini_price(),u.getInner_price(),u.getMedia_price(),
				u.getJd_price(),u.getRemark(),u.getIs_display(),updateTime,u.getPno()});
		
		return s;
	}

	@Override
	public void deleteCPU(String pno) {
		String delete="delete from cpu where pno=?";
		jdbcTemplate.update(delete, pno);
		String sql="delete from parts_index where pno=?";
		jdbcTemplate.update(sql, pno);
	}

}
