package com.benqzl.pojo.system;

import java.util.Date;

public class Prickle {
    private String id;

    private String editer;

    private String name;

    private String createrid;

    private Date createtime;

    private Date edittime;
    
    private Long isdel;
    
    private User user;
    
    private Employee employee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEditer() {
        return editer;
    }

    public void setEditer(String editer) {
        this.editer = editer == null ? null : editer.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCreaterid() {
        return createrid;
    }

    public void setCreaterid(String createrid) {
        this.createrid = createrid == null ? null : createrid.trim();
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

	public Long getIsdel() {
		return isdel;
	}

	public void setIsdel(Long isdel) {
		this.isdel = isdel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}