package com.sg.model;

public class Worker {
	private int id;
	private String worker_no;// 员工编号
	private String name;
	private String pwd;
	private int is_part_time;// 是否任职

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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getWorker_no() {
		return worker_no;
	}

	public void setWorker_no(String worker_no) {
		this.worker_no = worker_no;
	}

	public int getIs_part_time() {
		return is_part_time;
	}

	public void setIs_part_time(int is_part_time) {
		this.is_part_time = is_part_time;
	}

}
