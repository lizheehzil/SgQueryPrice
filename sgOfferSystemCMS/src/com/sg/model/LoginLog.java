package com.sg.model;

import java.util.Date;

public class LoginLog {
    /**
    * 登录日志表id
    */
    private String id;

    /**
    * 登录时间
    */
    private Date logintime;

    /**
    * 登录人员id
    */
    private Integer workerid;

    /**
    * 区分前后端人员
    */
    private Integer postionid;

    private String workername;
    
    

    /**
	 * 
	 */
	public LoginLog() {
		super();
	}

	/**
	 * @param id
	 * @param logintime
	 * @param workerid
	 * @param postionid
	 * @param workername
	 */
	public LoginLog(String id, Date logintime, Integer workerid,
			Integer postionid, String workername) {
		super();
		this.id = id;
		this.logintime = logintime;
		this.workerid = workerid;
		this.postionid = postionid;
		this.workername = workername;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Integer getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Integer workerid) {
        this.workerid = workerid;
    }

    public Integer getPostionid() {
        return postionid;
    }

    public void setPostionid(Integer postionid) {
        this.postionid = postionid;
    }

    public String getWorkername() {
        return workername;
    }

    public void setWorkername(String workername) {
        this.workername = workername;
    }
}