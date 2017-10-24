package com.benqzl.pojo.patrol;

import java.util.Date;
import java.util.List;

import com.benqzl.pojo.system.User;

public class PatrolPlan {
	private String id;

	private String code;

	private Date excepttime;

	private String memo;

	private Long state;

	private String creater;

	private Date createtime;

	private String sender;

	private Date sendtime;

	private List<PatrolPlanDetails> ppds;

	// 制单人
	private User createruser;

	// 发令人
	private User senderuser;

	
	public User getCreateruser() {
		return createruser;
	}

	public void setCreateruser(User createruser) {
		this.createruser = createruser;
	}

	public User getSenderuser() {
		return senderuser;
	}

	public void setSenderuser(User senderuser) {
		this.senderuser = senderuser;
	}

	public List<PatrolPlanDetails> getPpds() {
		return ppds;
	}

	public void setPpds(List<PatrolPlanDetails> ppds) {
		this.ppds = ppds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public Date getExcepttime() {
		return excepttime;
	}

	public void setExcepttime(Date excepttime) {
		this.excepttime = excepttime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
}