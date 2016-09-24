package com.sg.model;

import java.util.Date;

public class Itemvo {

	private String brand;
	private int brandId;
	private int category_id;
	private String category;
	private String pno;
	private Integer id;
	private String model;
	private double standard_price;
	private double unstandard_price;
	private double guide_price;
	private double cost_price;
	private double mini_price;
	private double media_price;
	private double jd_price;
	private int is_display;
	private String remark;
	private Date date;
	private String text;
	private Integer from;
	private String fromName;
	
	
	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Itemvo(String brand, String category, Integer category_id,String pno, Integer id,String model,
			double standard_price, double unstandard_price, double guide_price,
			double mini_price, double jd_price, int is_display, String remark) {
		super();
		this.brand = brand;
		this.category = category;
		this.pno = pno;
		this.id = id;
		this.model = model;
		this.standard_price = standard_price;
		this.unstandard_price = unstandard_price;
		this.guide_price = guide_price;
		this.mini_price = mini_price;
		this.jd_price = jd_price;
		this.is_display = is_display;
		this.remark = remark;
	}

	public Itemvo(String brand, int brandId, int category_id, String category,
			String pno, Integer id, String model, double cost_price,
			double mini_price, double media_price, int is_display, String remark) {
		super();
		this.brand = brand;
		this.brandId = brandId;
		this.category_id = category_id;
		this.category = category;
		this.pno = pno;
		this.id = id;
		this.model = model;
		this.cost_price = cost_price;
		this.mini_price = mini_price;
		this.media_price = media_price;
		this.is_display = is_display;
		this.remark = remark;
	}

	public double getStandard_price() {
		return standard_price;
	}

	public void setStandard_price(double standard_price) {
		this.standard_price = standard_price;
	}
	public double getJd_price() {
		return jd_price;
	}

	public void setJd_price(double jd_price) {
		this.jd_price = jd_price;
	}
	public double getUnstandard_price() {
		return unstandard_price;
	}

	public void setUnstandard_price(double unstandard_price) {
		this.unstandard_price = unstandard_price;
	}

	public double getGuide_price() {
		return guide_price;
	}

	public void setGuide_price(double guide_price) {
		this.guide_price = guide_price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	

	public int getIs_display() {
		return is_display;
	}

	public void setIs_display(int is_display) {
		this.is_display = is_display;
	}

	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getCost_price() {
		return cost_price;
	}

	public void setCost_price(double cost_price) {
		this.cost_price = cost_price;
	}

	public double getMini_price() {
		return mini_price;
	}

	public void setMini_price(double mini_price) {
		this.mini_price = mini_price;
	}

	public double getMedia_price() {
		return media_price;
	}

	public void setMedia_price(double media_price) {
		this.media_price = media_price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public Itemvo(String brand, String category, String pno, String model,
			double cost_price, double mini_price, double media_price,
			int is_display, String remark) {
		super();
		this.brand = brand;
		this.category = category;
		this.pno = pno;
		this.model = model;
		this.cost_price = cost_price;
		this.mini_price = mini_price;
		this.media_price = media_price;
		this.is_display = is_display;
		this.remark = remark;
	}

	public Itemvo() {
		super();
		// TODO Auto-generated constructor stub
	}

}