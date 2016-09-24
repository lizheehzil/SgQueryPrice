package com.sg.model;

/*
 * '所有产品均分为不同的科目，例如：笔记本、台式机、手机'
 */
public class Category {
	private int id;
	private String name;
	private int PID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

}
