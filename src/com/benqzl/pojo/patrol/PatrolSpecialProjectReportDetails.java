package com.benqzl.pojo.patrol;

public class PatrolSpecialProjectReportDetails {
    private String id;

    private String reportid;

    private String departmentreportid;

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

    public String getDepartmentreportid() {
        return departmentreportid;
    }

    public void setDepartmentreportid(String departmentreportid) {
        this.departmentreportid = departmentreportid == null ? null : departmentreportid.trim();
    }
}