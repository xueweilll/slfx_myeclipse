package com.benqzl.pojo.system;

import java.util.Date;
import java.util.List;

public class User {
    private String userid;

    private String username;

    private String userpwd;

    private Long sex;

    private Long age;

    private Employee employee;

    private Actor actor;

    private String email;

    private Date createtime;

    private Date edittime;

    private Long isdel;
    private String employeeid;
    private List<String> names;
    
    public String getEmployeeid() {
		return employeeid;
	}

	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActorid(Actor actor) {
        this.actor = actor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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


}