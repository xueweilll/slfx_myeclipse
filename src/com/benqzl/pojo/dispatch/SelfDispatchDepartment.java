package com.benqzl.pojo.dispatch;

import java.util.Date;

import com.benqzl.pojo.system.Department;
import com.benqzl.pojo.system.User;

public class SelfDispatchDepartment {
    private String id;

    private String departmentid;

    private String sender;

    private Date sendtime;

    private Short state;

    private String sdid;
    
    private SelfDispatch sd;
    
    private Department department;
    
	private User SendUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public String getSdid() {
        return sdid;
    }

    public void setSdid(String sdid) {
        this.sdid = sdid == null ? null : sdid.trim();
    }

	public SelfDispatch getSd() {
		return sd;
	}

	public void setSd(SelfDispatch sd) {
		this.sd = sd;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getSendUser() {
		return SendUser;
	}

	public void setSendUser(User sendUser) {
		SendUser = sendUser;
	}
}