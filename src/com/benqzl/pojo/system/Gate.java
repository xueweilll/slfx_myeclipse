package com.benqzl.pojo.system;

import java.util.Date;


/** 
* @ClassName: Gate 
* @Description: 闸门对象(这里用一句话描述这个类的作用) 
* @author xw 
* @date 2015年12月2日 下午3:26:55 
*  
*/
public class Gate implements Comparable<Gate>{
    private String id;

    private String sid;

    private String code;

    private String name;

    private Long onoffway;

    private Date createtime;

    private Date edittime;

    private Long isdel;
    
    private Station station;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getOnoffway() {
        return onoffway;
    }

    public void setOnoffway(Long onoffway) {
        this.onoffway = onoffway;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public Long getIsdel() {
        return isdel;
    }

    public void setIsdel(Long isdel) {
        this.isdel = isdel;
    }

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@Override
	public int compareTo(Gate o) {
		return this.name.compareTo(o.name);
	}

	
}