package com.sg.dao;

import java.util.List;

import com.sg.model.Itemvo;
import com.sg.model.Message;


public interface MessageDao {
	public int getCount(Integer uid);
	public List<Message> getUnread(Long uid);
	public void insertUnread(Message msg);
	public void readAll(Integer uid);
	public List<Message> getMyNotice(Integer uid,Integer fromIndex,Integer pageSize);
	public List<Message> getAllNotice(Integer uid,Integer fromIndex,Integer pageSize);
	public Itemvo getItem(String className,String pno);
	public void buy(Integer id);
	public void feedback(Message msg);
	public Message getMessage(Integer msgid);
	public int getTotolCount();
}
