package com.benqzl.pojo.dispatch;

import java.util.Date;

import com.benqzl.pojo.system.User;

public class ReceiptDispatchSupervise {
	private String id;

	private Date createtime;

	private String rdid;

	private String supervise;

	private String superviseunit;

	private String supervisepeople;

	private Date supervisetime;

	private String operater;

	private String creater;

	private User operateUser;

	public User getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
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

	public String getRdid() {
		return rdid;
	}

	public void setRdid(String rdid) {
		this.rdid = rdid == null ? null : rdid.trim();
	}

	public String getSupervise() {
		return supervise;
	}

	public void setSupervise(String supervise) {
		this.supervise = supervise == null ? null : supervise.trim();
	}

	public String getSuperviseunit() {
		return superviseunit;
	}

	public void setSuperviseunit(String superviseunit) {
		this.superviseunit = superviseunit == null ? null : superviseunit
				.trim();
	}

	public String getSupervisepeople() {
		return supervisepeople;
	}

	public void setSupervisepeople(String supervisepeople) {
		this.supervisepeople = supervisepeople == null ? null : supervisepeople
				.trim();
	}

	public Date getSupervisetime() {
		return supervisetime;
	}

	public void setSupervisetime(Date supervisetime) {
		this.supervisetime = supervisetime;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater == null ? null : operater.trim();
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}
}