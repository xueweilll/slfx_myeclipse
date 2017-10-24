package com.benqzl.pojo.system;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.benqzl.pojo.water.PumprunItems;

public class Unit implements Comparable<Unit>{
    private String id;

    private String sid;

    private String code;

    private String name;

    private Long types;

    private String power;

    private String motertype;

    private Long feature;

    private BigDecimal designdischarge;

    private Date createtime;

    private Date edittime;

    private Long isdel;
    
    private Station station;
    
    private List<String> names;
    
    private List<PumprunItems> items;
    
    private Integer states;//机组开关机状态
    
    public List<PumprunItems> getItems() {
		return items;
	}

	public void setItems(List<PumprunItems> items) {
		this.items = items;
	}

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

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

    public Long getTypes() {
        return types;
    }

    public void setTypes(Long types) {
        this.types = types;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getMotertype() {
        return motertype;
    }

    public void setMotertype(String motertype) {
        this.motertype = motertype == null ? null : motertype.trim();
    }

    public Long getFeature() {
        return feature;
    }

    public void setFeature(Long feature) {
        this.feature = feature;
    }

    public BigDecimal getDesigndischarge() {
        return designdischarge;
    }

    public void setDesigndischarge(BigDecimal designdischarge) {
        this.designdischarge = designdischarge;
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
	public int compareTo(Unit o) {
		return this.name.compareTo(o.name);
	}

    
}