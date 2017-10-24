package com.benqzl.pojo.system;

import java.util.Date;
import java.util.List;

public class Department {
    private String id;

    private String pareid;

    private String name;

    private String telephone;

    private String remark;

    private Date createtime;

    private Date edittime;

    private Long isdel;
    
    //employee属性
    private List<Employee> employeeList;
    
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPareid() {
        return pareid;
    }

    public void setPareid(String pareid) {
        this.pareid = pareid == null ? null : pareid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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