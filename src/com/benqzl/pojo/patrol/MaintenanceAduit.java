package com.benqzl.pojo.patrol;

import java.util.Date;

public class MaintenanceAduit {
    private String maintenanceid;

    private String id;

    private Long state;

    private String aduiter;

    private Date aduittime;

    private String memo;
    
    private Long type;

    public String getMaintenanceid() {
        return maintenanceid;
    }

    public void setMaintenanceid(String maintenanceid) {
        this.maintenanceid = maintenanceid == null ? null : maintenanceid.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getAduiter() {
        return aduiter;
    }

    public void setAduiter(String aduiter) {
        this.aduiter = aduiter == null ? null : aduiter.trim();
    }

    public Date getAduittime() {
        return aduittime;
    }

    public void setAduittime(Date aduittime) {
        this.aduittime = aduittime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
    
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}