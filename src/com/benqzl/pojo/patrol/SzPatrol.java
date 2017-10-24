package com.benqzl.pojo.patrol;

import java.util.Date;

import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;

public class SzPatrol {
    private String id;

    private Date createtime;

    private Date edittime;

    private Date patroltime;

    private String sid;

    private String patroladdress;

    private String patrolcase;

    private String patrolmemo;

    private String creater;

    private Date creatertime;

    private String handler;

    private Date handlertime;

    private Long state;
    //枢纽负责人
    private User user;
    //枢纽
    private Station station;
    //审核人
    private User userapproval;
    
    public User getUserapproval() {
		return userapproval;
	}

	public void setUserapproval(User userapproval) {
		this.userapproval = userapproval;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public Date getPatroltime() {
        return patroltime;
    }

    public void setPatroltime(Date patroltime) {
        this.patroltime = patroltime;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getPatroladdress() {
        return patroladdress;
    }

    public void setPatroladdress(String patroladdress) {
        this.patroladdress = patroladdress == null ? null : patroladdress.trim();
    }

    public String getPatrolcase() {
        return patrolcase;
    }

    public void setPatrolcase(String patrolcase) {
        this.patrolcase = patrolcase == null ? null : patrolcase.trim();
    }

    public String getPatrolmemo() {
        return patrolmemo;
    }

    public void setPatrolmemo(String patrolmemo) {
        this.patrolmemo = patrolmemo == null ? null : patrolmemo.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreatertime() {
        return creatertime;
    }

    public void setCreatertime(Date creatertime) {
        this.creatertime = creatertime;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler == null ? null : handler.trim();
    }

    public Date getHandlertime() {
        return handlertime;
    }

    public void setHandlertime(Date handlertime) {
        this.handlertime = handlertime;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }
}