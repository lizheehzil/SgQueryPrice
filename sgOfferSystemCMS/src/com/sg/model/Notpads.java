package com.sg.model;

/*
 * 笔记本电脑
 */
public class Notpads {
	private int id;
	private String pno;//货号
	private int brandId;// '品牌ID',
	private int category_id;
	private String model;// '型号'
	private String configuration_code;//配置代码
	private String CPU;// 'CPU',
	private String graphics_card; // '显卡',
	private String memories; // '内存',
	private String screen_size; // '屏幕尺寸',
	private String disk; // '硬盘',
	private String keypad_backlight; // '键盘背光',
	private String Cd_drive; // '有无光驱',
	private int M_2; // 'M_2',
	private int MSATA; // 'MSATA',
	private double standard_price;
	private double unstandard_price;
	private double guide_price;
	private double mini_price; // '最低价',
	private double inner_price; // 内部价',
	private double media_price; // '媒体价',
	private double vip_price; // '会员价',
	private double jd_price; // '京东价',
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getConfiguration_code() {
		return configuration_code;
	}
	public void setConfiguration_code(String configuration_code) {
		this.configuration_code = configuration_code;
	}
	public String getCPU() {
		return CPU;
	}
	public void setCPU(String cPU) {
		CPU = cPU;
	}
	public String getGraphics_card() {
		return graphics_card;
	}
	public void setGraphics_card(String graphics_card) {
		this.graphics_card = graphics_card;
	}
	public String getMemories() {
		return memories;
	}
	public void setMemories(String memories) {
		this.memories = memories;
	}
	public String getScreen_size() {
		return screen_size;
	}
	public void setScreen_size(String screen_size) {
		this.screen_size = screen_size;
	}
	public String getDisk() {
		return disk;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}
	public String getKeypad_backlight() {
		return keypad_backlight;
	}
	public void setKeypad_backlight(String keypad_backlight) {
		this.keypad_backlight = keypad_backlight;
	}
	public String getCd_drive() {
		return Cd_drive;
	}
	public void setCd_drive(String cd_drive) {
		Cd_drive = cd_drive;
	}
	public int getM_2() {
		return M_2;
	}
	public void setM_2(int m_2) {
		M_2 = m_2;
	}
	public int getMSATA() {
		return MSATA;
	}
	public void setMSATA(int mSATA) {
		MSATA = mSATA;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getMedia_price() {
		return media_price;
	}
	public void setMedia_price(double media_price) {
		this.media_price = media_price;
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
	public double getMini_price() {
		return mini_price;
	}
	public void setMini_price(double mini_price) {
		this.mini_price = mini_price;
	}
	public double getJd_price() {
		return jd_price;
	}
	public void setJd_price(double jd_price) {
		this.jd_price = jd_price;
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
	public int getIs_display() {
		return is_display;
	}
	public void setIs_display(int is_display) {
		this.is_display = is_display;
	}
	
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public double getStandard_price() {
		return standard_price;
	}
	public void setStandard_price(double standard_price) {
		this.standard_price = standard_price;
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
	public Notpads(int id, String pno, int brandId, int category_id,
			String model, String configuration_code, String cPU,
			String graphics_card, String memories, String screen_size,
			String disk, String keypad_backlight, String cd_drive, int m_2,
			int mSATA, double standard_price, double unstandard_price,
			double guide_price, double mini_price,double jd_price,
			String create_time, String update_time, String remark,
			int is_display) {
		super();
		this.id = id;
		this.pno = pno;
		this.brandId = brandId;
		this.category_id = category_id;
		this.model = model;
		this.configuration_code = configuration_code;
		CPU = cPU;
		this.graphics_card = graphics_card;
		this.memories = memories;
		this.screen_size = screen_size;
		this.disk = disk;
		this.keypad_backlight = keypad_backlight;
		Cd_drive = cd_drive;
		M_2 = m_2;
		MSATA = mSATA;
		this.standard_price = standard_price;
		this.unstandard_price = unstandard_price;
		this.guide_price = guide_price;
		this.mini_price = mini_price;
		this.jd_price = jd_price;
		this.create_time = create_time;
		this.update_time = update_time;
		this.remark = remark;
		this.is_display = is_display;
	}
	public Notpads() {
		super();
		// TODO Auto-generated constructor stub
	}

}
