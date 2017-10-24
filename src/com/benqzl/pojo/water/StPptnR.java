package com.benqzl.pojo.water;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.benqzl.pojo.system.Station;

public class StPptnR implements Serializable{
	
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String fid;
	
    public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	private String stcd;
    
    private Station station;
    
    public Station getStation() {
  		return station;
  	}

  	public void setStation(Station station) {
  		this.station = station;
  	}

    private Date tm;
	
	private BigDecimal dpr;//1毫米

    private BigDecimal intv;

    private BigDecimal pdr;

    private BigDecimal dyp;//日降雨量

    private String wth;
    
    private BigDecimal hisrain;//历史累计雨量
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd == null ? null : stcd.trim();
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public BigDecimal getHisrain() {
		return hisrain;
	}

	public void setHisrain(BigDecimal hisrain) {
		this.hisrain = hisrain;
	}

	public BigDecimal getDpr() {
        return dpr;
    }

    public void setDpr(BigDecimal dpr) {
        this.dpr = dpr;
    }

    public BigDecimal getIntv() {
        return intv;
    }

    public void setIntv(BigDecimal intv) {
        this.intv = intv;
    }

    public BigDecimal getPdr() {
        return pdr;
    }

    public void setPdr(BigDecimal pdr) {
        this.pdr = pdr;
    }

    public BigDecimal getDyp() {
        return dyp;
    }

    public void setDyp(BigDecimal dyp) {
        this.dyp = dyp;
    }

    public String getWth() {
        return wth;
    }

    public void setWth(String wth) {
        this.wth = wth == null ? null : wth.trim();
    }
}