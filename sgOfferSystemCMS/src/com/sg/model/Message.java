package com.sg.model;

import java.util.Date;
/**
 * 消息类
 * @author Goofy
 * @Date 2015年6月12日 下午7:32:39
 */
public class Message {

	private Integer id;

	//发送者
	public Long from;
	//发送者名称
	public String fromName;
	//接收者
	public Long to;
	
	//发送的货号
	public String title;
	
	public String text;
	//发送日期
	public Date date;

	public Integer type;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Message [from=" + from + ", fromName=" + fromName + ", to="
				+ to + ", title=" + title + ", text=" + text + ", date=" + date
				+ ", type=" + type + "]";
	}
}
