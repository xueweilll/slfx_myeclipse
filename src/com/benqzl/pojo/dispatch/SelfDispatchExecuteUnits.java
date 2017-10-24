package com.benqzl.pojo.dispatch;

import java.util.Date;

import com.benqzl.pojo.system.Unit;

public class SelfDispatchExecuteUnits {
    private String id;

    private String unitid;

    private Date begintime;

    private Date endtime;

    private String executeid;

    private Unit unit;
    
    public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUnitid() {
        return unitid;
    }

    public void setUnitid(String unitid) {
        this.unitid = unitid == null ? null : unitid.trim();
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getExecuteid() {
        return executeid;
    }

    public void setExecuteid(String executeid) {
        this.executeid = executeid == null ? null : executeid.trim();
    }
}