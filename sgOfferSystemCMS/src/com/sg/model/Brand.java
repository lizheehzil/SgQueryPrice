package com.sg.model;

public class Brand {
	private int id;
	private String name;
	private int category_id;

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

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", category_id="
				+ category_id + "]";
	}

}
