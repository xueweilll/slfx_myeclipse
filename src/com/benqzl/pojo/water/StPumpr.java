package com.benqzl.pojo.water;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.benqzl.pojo.system.Station;

/** 
* @ClassName: StPumpr 
* @Description: 泵站运行工况表
* @author xw 
* @date 2016年1月30日 下午2:16:28 
*  
*/
public class StPumpr implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	/** 
    * @Fields fId : 主键
    */ 
    private String fId;

    /** 
    * @Fields stcd : 枢纽code
    */ 
    private String stcd;
    
    /** 
    * @Fields tm : 时间
    */ 
    private Date tm;

    /** 
    * @Fields ppupz : 外河水位
    */ 
    private BigDecimal ppupz;

    /** 
    * @Fields ppdwz : 内河水位
    */ 
    private BigDecimal ppdwz;

    /** 
    * @Fields omcn : 开机台数
    */ 
    private Long omcn;

    /** 
    * @Fields ompwr : 开机功率
    */ 
    private Long ompwr;
    
    /** 
    * @Fields pmpq : 抽水流量
    */ 
    private BigDecimal pmpq;

    /** 
    * @Fields ppupwptn : 外河水势
    */ 
    private String ppupwptn;

    /** 
    * @Fields ppdwwptn : 内河水势
    */ 
    private String ppdwwptn;

    /** 
    * @Fields pdchcd : 引排特征码
    */ 
    private Station station;
    
    private String pdchcd;
    
    private String sname;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
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

    public BigDecimal getPpupz() {
        return ppupz;
    }

    public void setPpupz(BigDecimal ppupz) {
        this.ppupz = ppupz;
    }

    public BigDecimal getPpdwz() {
        return ppdwz;
    }

    public void setPpdwz(BigDecimal ppdwz) {
        this.ppdwz = ppdwz;
    }

    public Long getOmcn() {
        return omcn;
    }

    public void setOmcn(Long omcn) {
        this.omcn = omcn;
    }

    public Long getOmpwr() {
        return ompwr;
    }

    public void setOmpwr(Long ompwr) {
        this.ompwr = ompwr;
    }

    public BigDecimal getPmpq() {
        return pmpq;
    }

    public void setPmpq(BigDecimal pmpq) {
        this.pmpq = pmpq;
    }

    public String getPpupwptn() {
        return ppupwptn;
    }

    public void setPpupwptn(String ppupwptn) {
        this.ppupwptn = ppupwptn == null ? null : ppupwptn.trim();
    }

    public String getPpdwwptn() {
        return ppdwwptn;
    }

    public void setPpdwwptn(String ppdwwptn) {
        this.ppdwwptn = ppdwwptn == null ? null : ppdwwptn.trim();
    }

    public String getPdchcd() {
        return pdchcd;
    }

    public void setPdchcd(String pdchcd) {
        this.pdchcd = pdchcd == null ? null : pdchcd.trim();
    }

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
}