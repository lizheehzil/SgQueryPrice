package com.sg.dao.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.sg.dao.WorkerDao;
import com.sg.model.Worker;

@Repository
public class WorkerDaoImpl implements WorkerDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Worker getworker(String name){
		String getworker="select * from worker where worker_no=?";
		Worker worker =new Worker();
		jdbcTemplate.query(getworker, new Object[]{name}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				worker.setId(rs.getInt("id"));
				worker.setName(rs.getString("name"));
				worker.setWorker_no(rs.getString("worker_no"));
				worker.setPwd(rs.getString("pwd"));
			}
		});
		return worker;
	}
	
	
	@Override
	public Worker login(String name, String pwd,int position) {
		
		String LOGIN="select id,worker_no as name,pwd from worker where worker_no=? and postionId=?";
		final Worker w=new Worker();
		jdbcTemplate.query(LOGIN, new Object[]{name,position}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				w.setId(rs.getInt("id"));
				w.setWorker_no(rs.getString("name"));
				w.setPwd(rs.getString("pwd"));
			}
		});
		
		if(pwd.equals(w.getPwd())){
			
			return getworker(name);
		}
		return null;
	}

	@Override
	public int logout() {
		return 0;
	}

}
