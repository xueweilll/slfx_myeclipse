package com.benqzl.pojo.patrol;


public class PatrolSpecialDeaprtmentReportDetails {
    private String id;

    private String reportid;

    private String implementid;
    
    private PatrolSpecialImplement specialImplements;
    
    public PatrolSpecialImplement getSpecialImplements() {
		return specialImplements;
	}

	public void setSpecialImplements(PatrolSpecialImplement specialImplements) {
		this.specialImplements = specialImplements;
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

    public String getImplementid() {
        return implementid;
    }

    public void setImplementid(String implementid) {
        this.implementid = implementid == null ? null : implementid.trim();
    }
}