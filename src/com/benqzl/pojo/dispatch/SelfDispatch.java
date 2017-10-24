package com.benqzl.pojo.dispatch;

import java.util.Date;
import java.util.List;

import com.benqzl.pojo.system.Department;
import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;

public class SelfDispatch {
	private String id;

	private String promoter;
	

	private Date promotetime;

	private String sender;

	private Date sendtime;

	private String memo;

	private String creater;

	private Date createtime;

	//0：初始 1：已提交 2：部门审核通过 3：部门审核不过 4：领导审批通过 5：领导审批不通过 6：片区调度中 7：片区调度完成
	private Long state;

	private Date completetime;

	private String code;

	//发起人
	private User user;
	//制单人
	private User CreateUser;
	//下发人
	private User SendUser;
	
	private List<SelfDispatchInstructions> instructions;

	private List<SelfDispatchStations> stations;
	
	private Employee employee;
	
	private String departmentauditor;
	
    private Department departmentid;
	
	private User Auditor;

    private String ldapproval;
    
    private User LdApproval;

    private Date departmentaudittime;

    private Date ldapprovaltime;
    
    private String departmentnames;
    
    private String departmenttimes;    
	
	public String getDepartmentnames() {
		return departmentnames;
	}

	public void setDepartmentnames(String departmentnames) {
		this.departmentnames = departmentnames;
	}

	public String getDepartmenttimes() {
		return departmenttimes;
	}

	public void setDepartmenttimes(String departmenttimes) {
		this.departmenttimes = departmenttimes;
	}

	public String getDepartmentauditor() {
		return departmentauditor;
	}

	public void setDepartmentauditor(String departmentauditor) {
		this.departmentauditor = departmentauditor;
	}

	public String getLdapproval() {
		return ldapproval;
	}

	public void setLdapproval(String ldapproval) {
		this.ldapproval = ldapproval;
	}

	public Date getDepartmentaudittime() {
		return departmentaudittime;
	}

	public void setDepartmentaudittime(Date departmentaudittime) {
		this.departmentaudittime = departmentaudittime;
	}

	public Date getLdapprovaltime() {
		return ldapprovaltime;
	}

	public void setLdapprovaltime(Date ldapprovaltime) {
		this.ldapprovaltime = ldapprovaltime;
	}

	public User getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(User createUser) {
		CreateUser = createUser;
	}

	public User getSendUser() {
		return SendUser;
	}

	public void setSendUser(User sendUser) {
		SendUser = sendUser;
	}

	
	public List<SelfDispatchInstructions> getInstructions() {
		return instructions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setInstructions(List<SelfDispatchInstructions> instructions) {
		this.instructions = instructions;
	}

	public List<SelfDispatchStations> getStations() {
		return stations;
	}

	public void setStations(List<SelfDispatchStations> stations) {
		this.stations = stations;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPromoter() {
		return promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter == null ? null : promoter.trim();
	}

	public Date getPromotetime() {
		return promotetime;
	}

	public void setPromotetime(Date promotetime) {
		this.promotetime = promotetime;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
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

	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	public Date getCompletetime() {
		return completetime;
	}

	public void setCompletetime(Date completetime) {
		this.completetime = completetime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public User getAuditor() {
		return Auditor;
	}

	public void setAuditor(User auditor) {
		Auditor = auditor;
	}

	public User getLdApproval() {
		return LdApproval;
	}

	public void setLdApproval(User ldApproval) {
		LdApproval = ldApproval;
	}

	public Department getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Department departmentid) {
		this.departmentid = departmentid;
	}

	
}