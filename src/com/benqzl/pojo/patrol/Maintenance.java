package com.benqzl.pojo.patrol;

import java.util.Date;
import java.util.List;

import com.benqzl.pojo.system.Department;
import com.benqzl.pojo.system.User;

public class Maintenance {
    private String id;

    private String createrid;

    private String code;

    private String departmentid;
    
    private Department department;

    private Date createtime;

    private Date applydate;

    private String projectname;

    private String constructionunits;

    private String applyer;
    
    private User apply;

    private String checker;

    private Long state;

    private String memo;
    
    private List<MaintenanceDetails> details;
    
    private Long step;

    private String stepmemo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreaterid() {
        return createrid;
    }

    public void setCreaterid(String createrid) {
        this.createrid = createrid == null ? null : createrid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getApplydate() {
        return applydate;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getConstructionunits() {
        return constructionunits;
    }

    public void setConstructionunits(String constructionunits) {
        this.constructionunits = constructionunits == null ? null : constructionunits.trim();
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer == null ? null : applyer.trim();
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getApply() {
		return apply;
	}

	public void setApply(User apply) {
		this.apply = apply;
	}

	public List<MaintenanceDetails> getDetails() {
		return details;
	}

	public void setDetails(List<MaintenanceDetails> details) {
		this.details = details;
	}
	
	public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }
    
	public String getStepmemo() {
        return stepmemo;
    }

    public void setStepmemo(String stepmemo) {
        this.stepmemo = stepmemo == null ? null : stepmemo.trim();
    }
}