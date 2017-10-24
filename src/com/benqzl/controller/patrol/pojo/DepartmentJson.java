package com.benqzl.controller.patrol.pojo;

import java.util.List;

public class DepartmentJson {
	private List<String> ids;//ID数据
	private String type;//弹窗类型  0. 明细弹窗  1.汇总弹窗 2.自行解决弹窗
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
