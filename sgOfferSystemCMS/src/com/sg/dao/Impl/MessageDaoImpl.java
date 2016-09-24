package com.sg.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.sg.dao.CPUDao;
import com.sg.dao.CaseeDao;
import com.sg.dao.DiskDao;
import com.sg.dao.Graphics_cardDao;
import com.sg.dao.Key_mouseDao;
import com.sg.dao.Memories_barDao;
import com.sg.dao.MessageDao;
import com.sg.dao.MonitorDao;
import com.sg.dao.Mother_boardDao;
import com.sg.dao.NotpadsDao;
import com.sg.dao.PowerDao;
import com.sg.dao.RadiatorDao;
import com.sg.dao.U_diskDao;
import com.sg.dao.UtilDao;
import com.sg.model.CPU;
import com.sg.model.Casee;
import com.sg.model.Disk;
import com.sg.model.Graphics_card;
import com.sg.model.Itemvo;
import com.sg.model.Key_mouse;
import com.sg.model.Memories_bar;
import com.sg.model.Message;
import com.sg.model.Monitor;
import com.sg.model.Mother_board;
import com.sg.model.Notpads;
import com.sg.model.Power;
import com.sg.model.Radiator;
import com.sg.model.U_disk;


@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NotpadsDao notpadsDao;
	@Resource(name="cpuDaoImpl")
	private CPUDao cpuDao;
	@Autowired
	private UtilDao utilDao;
	@Autowired
	private CaseeDao caseeDao;
	@Autowired
	private DiskDao diskDao;
	@Autowired
	private Graphics_cardDao graphics_cardDao;
	@Autowired
	private Key_mouseDao key_mouseDao;
	@Autowired
	private Memories_barDao memories_barDao;
	@Autowired
	private MonitorDao monitorDao;
	@Autowired
	private Mother_boardDao mother_boardDao;
	@Autowired
	private PowerDao powerDao;
	@Autowired
	private RadiatorDao radiatorDao;
	@Autowired
	private U_diskDao u_diskDao;
	
	
	public String getName(Integer id){
		String sql="select name from worker where id="+id;
		String name=jdbcTemplate.queryForObject(sql, String.class);
		return name;
	}
	
	
	@Override
	public int getCount(Integer uid) {
		String GETCOUNT = "select count(id) from notice where from_id="+uid;
		int totalcount = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		return totalcount;
	}
	
	@Override
	public int getTotolCount() {
		String GETCOUNT = "select count(id) from notice";
		int totalcount = jdbcTemplate.queryForObject(GETCOUNT, Integer.class);
		return totalcount;
	}
	
	@Override
	public Message getMessage(Integer id){
		Message msg=new Message();
		String sql="select from_id,to_id from notice where id="+id;
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				msg.setId(id);
				msg.setTo(new Long(rs.getInt("to_id")));
				msg.setFrom(new Long(rs.getInt("from_id")));
			}
		});
		return msg;
	}
	
	
	@Override
	public void buy(Integer id) {
		String sql="update notice set status=3,type=3 where id="+id;
		jdbcTemplate.update(sql);
		
	}
	
	@Override
	public void feedback(Message msg) {
		int id=msg.getId();
		String text=msg.getText();
		String sql="update notice set status=2,text=? where id=?";
		jdbcTemplate.update(sql, new Object[]{text,id},new int[]{Types.VARCHAR,Types.INTEGER});
		
	}
	@Override
	public List<Message> getUnread(Long uid){
		
		String sql1="select id,from_id,title,type,text from notice where to_id=? and status=2";
		String sql2="select id,from_id,title,type,text from notice where to_id=? and status=1 or status=3";
		List<Message> list=new ArrayList<Message>();
		Integer id=uid.intValue();
		String sql;
		if(id==3213123){
			sql=sql2;
		}else{
			sql=sql1;
		}
		jdbcTemplate.query(sql, new Object[]{id},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Message m=new Message();
				m.setFrom((long) rs.getInt("from_id"));
				m.setTitle(rs.getString("title"));
				m.setTo(new Long(id));
				m.setText(rs.getString("text"));
				list.add(m);
			}
		});
		return list;
	}

	@Override
	public void insertUnread(Message msg) {
		String sql="insert into notice (status,title,type,from_id,to_id,date) values(?,?,?,?,?,?)";
		java.sql.Date date=new java.sql.Date(msg.getDate().getTime());
		Object[] o=new Object[]{1,msg.getTitle(),msg.getType(),msg.getFrom(),msg.getTo(),date};
		jdbcTemplate.update(sql, o, new int[]{Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.DATE});
	}

	@Override
	public void readAll(Integer uid) {
		String sql="update notice set status=0 where status=2 and from_id="+uid;
		String sql2="update notice set status=0 where status=1 or status=3 and to_id="+uid;
		if(uid==3213123){
			jdbcTemplate.update(sql2);
		}else{
			jdbcTemplate.update(sql);
		}
	}
	
	@Override
	public List<Message> getMyNotice(Integer uid,Integer fromIndex,Integer pageSize){
		String sql="select id,title,type,date,text from notice where from_id=? order by id desc limit ?,?";
		
		List<Message> list=new ArrayList<Message>();
		
		jdbcTemplate.query(sql, new Object[]{uid,fromIndex,pageSize},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Message m=new Message();
				m.setId(rs.getInt("id"));
				m.setTitle(rs.getString("title"));
				m.setDate(rs.getDate("date"));
				m.setType(rs.getInt("type"));
				m.setText(rs.getString("text"));
				list.add(m);
			}
		});
		return list;
	}
	@Override
	public List<Message> getAllNotice(Integer uid,Integer fromIndex,Integer pageSize){
		String sql="select id,title,type,date,text,from_id from notice where to_id=? order by id desc limit ?,?";
		
		List<Message> list=new ArrayList<Message>();
		
		jdbcTemplate.query(sql, new Object[]{uid,fromIndex,pageSize},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Message m=new Message();
				m.setId(rs.getInt("id"));
				m.setTitle(rs.getString("title"));
				m.setDate(rs.getDate("date"));
				m.setType(rs.getInt("type"));
				m.setText(rs.getString("text"));
				m.setFrom(new Long(rs.getInt("from_id")));
				m.setFromName(getName(rs.getInt("from_id")));
				list.add(m);
			}
		});
		return list;
	}
	
	
	
	@Override
	public Itemvo getItem(String className,String pno) {
		
		Itemvo vo=new Itemvo();
		if(className.equals("Notpads")){
			Notpads n=notpadsDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			vo.setGuide_price(n.getGuide_price());
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
		if(className.equals("Casee")){
			Casee n=caseeDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}

		if(className.equals("Disk")){
			Disk n=diskDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("CPU")){
			CPU n= cpuDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("Graphics_card")){
			Graphics_card n= graphics_cardDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("Memories_bar")){
			Memories_bar n=memories_barDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("Monitor")){
			Monitor n=monitorDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("Mother_board")){
			Mother_board n=mother_boardDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("Key_mouse")){
			Key_mouse n=key_mouseDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("U_disk")){
			U_disk n=u_diskDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("Power")){
			Power n= powerDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		if(className.equals("Radiator")){
			Radiator n=radiatorDao.getItem(pno);
			vo.setBrand(utilDao.getBrandName(n.getBrandId()));
			vo.setBrandId(n.getBrandId());
			vo.setCategory_id(n.getCategory_id());
			vo.setCategory(utilDao.getCategoryName(n.getCategory_id()));
			
			vo.setPno(pno);
			vo.setModel(n.getModel());
		}
			
		return vo;
	}


	
}
