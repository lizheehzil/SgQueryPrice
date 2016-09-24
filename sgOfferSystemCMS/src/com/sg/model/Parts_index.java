package com.sg.model;

public class Parts_index {
	private int id; // 'ID',
	private String pno; // '货号',
	private int category; // '产品类别',
	@Override
	public String toString() {
		return "Parts_index [id=" + id + ", pno=" + pno + ", category="
				+ category + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
}
