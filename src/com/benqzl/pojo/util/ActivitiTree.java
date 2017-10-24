package com.benqzl.pojo.util;

import java.util.List;

public class ActivitiTree {
	private String id;
	private String name;
	private String parent;
	List<String> userids;
	private int isedit;
	public int getIsedit() {
		return isedit;
	}
	public void setIsedit(int isedit) {
		this.isedit = isedit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<String> getUserids() {
		return userids;
	}
	public void setUserids(List<String> userids) {
		this.userids = userids;
	}
}
