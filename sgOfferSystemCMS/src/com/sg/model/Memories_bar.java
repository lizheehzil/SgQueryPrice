package com.sg.model;

public class Memories_bar {

	public Memories_bar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Memories_bar(int id, String pno, int category_id, int brandId,
			String model, double capacity, double cost_price,
			double mini_price, double inner_price, double media_price,
			double jd_price, double vip_price, String create_time,
			String update_time, String remark, int is_display) {
		super();
		this.id = id;
		this.pno = pno;
		this.category_id = category_id;
		this.brandId = brandId;
		this.model = model;
		this.capacity = capacity;
		this.cost_price = cost_price;
		this.mini_price = mini_price;
		this.inner_price = inner_price;
		this.media_price = media_price;
		this.jd_price = jd_price;
		this.vip_price = vip_price;
		this.create_time = create_time;
		this.update_time = update_time;
		this.remark = remark;
		this.is_display = is_display;
	}

	private int id; // 'ID',
	private String pno; // '货号',
	private int category_id; // '产品分类',
	private int brandId; // '品牌',
	private String model;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	private double capacity; // '容量',
	private double cost_price; // '进价',
	private double mini_price; // '最低价',
	private double inner_price; // '内部价',
	private double media_price; // '媒体价',
	private double jd_price; // '京东价',
	private double vip_price; // '会员价',
	private String create_time; // '录入日期',
	private String update_time; // '更新日期',
	private String remark; // '备注',
	private int is_display; // '是否对外可见',

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

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getInner_price() {
		return inner_price;
	}

	public void setInner_price(double inner_price) {
		this.inner_price = inner_price;
	}

	public double getVip_price() {
		return vip_price;
	}

	public void setVip_price(double vip_price) {
		this.vip_price = vip_price;
	}

	public double getJd_price() {
		return jd_price;
	}

	public void setJd_price(double jd_price) {
		this.jd_price = jd_price;
	}

	public double getCost_price() {
		return cost_price;
	}

	public void setCost_price(double cost_price) {
		this.cost_price = cost_price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public double getMedia_price() {
		return media_price;
	}

	public void setMedia_price(double media_price) {
		this.media_price = media_price;
	}

	public double getMini_price() {
		return mini_price;
	}

	public void setMini_price(double mini_price) {
		this.mini_price = mini_price;
	}

	@Override
	public String toString() {
		return "Memories_bar [id=" + id + ", pno=" + pno + ", brandId="
				+ brandId + ", capacity=" + capacity + ", media_price="
				+ media_price + ", mini_price=" + mini_price + ", inner_price="
				+ inner_price + ", vip_price=" + vip_price + ", jd_price="
				+ jd_price + ", cost_price=" + cost_price + ", remark="
				+ remark + ", category_id=" + category_id + ", is_display="
				+ is_display + ", create_time=" + create_time
				+ ", update_time=" + update_time + "]";
	}

}
