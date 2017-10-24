package com.benqzl.pojo.patrol;

public class PatrolNormalReportDetails {
    private String id;

    private String reportid;

    private String patrolid;

    private String departmentreportid;

    private Long state;
    
    private PatrolNormal patrolNormal;
    

    public PatrolNormal getPatrolNormal() {
		return patrolNormal;
	}

	public void setPatrolNormal(PatrolNormal patrolNormal) {
		this.patrolNormal = patrolNormal;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid == null ? null : reportid.trim();
    }

    public String getPatrolid() {
        return patrolid;
    }

    public void setPatrolid(String patrolid) {
        this.patrolid = patrolid == null ? null : patrolid.trim();
    }

    public String getDepartmentreportid() {
        return departmentreportid;
    }

    public void setDepartmentreportid(String departmentreportid) {
        this.departmentreportid = departmentreportid == null ? null : departmentreportid.trim();
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }
}