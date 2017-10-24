package com.benqzl.pojo.water;

import java.io.Serializable;
import java.util.Date;

import com.benqzl.pojo.system.Station;

/** 
* @ClassName: TrWarnlog 
* @Description: 报警记录表
* @author xw 
* @date 2016年1月30日 下午2:26:35 
*  
*/
public class TrWarnlog implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	/** 
    * @Fields fId : 主键
    */ 
    private String fId;

    /** 
    * @Fields fStationcode : 枢纽code
    */ 
    private String fStationcode;

    /** 
    * @Fields fWarncode : 报警信息
    */ 
    private String fCaption;

    /** 
    * @Fields fWarntime : 报警时间
    */ 
    private Date fWarntime;

    /** 
    * @Fields fFreetime : 解除时间
    */ 
    private Date fFreetime;

    /** 
    * @Fields fWarnstate : 报警状态   0：解除；1：报警；
    */ 
    private String fWarnstate;

    private Station station;
    
    public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getfStationcode() {
        return fStationcode;
    }

    public void setfStationcode(String fStationcode) {
        this.fStationcode = fStationcode == null ? null : fStationcode.trim();
    }

    public String getfCaption() {
        return fCaption;
    }

    public void setfCaption(String fCaption) {
        this.fCaption = fCaption == null ? null : fCaption.trim();
    }

    public Date getfWarntime() {
        return fWarntime;
    }

    public void setfWarntime(Date fWarntime) {
        this.fWarntime = fWarntime;
    }

    public Date getfFreetime() {
        return fFreetime;
    }

    public void setfFreetime(Date fFreetime) {
        this.fFreetime = fFreetime;
    }

    public String getfWarnstate() {
        return fWarnstate;
    }

    public void setfWarnstate(String fWarnstate) {
        this.fWarnstate = fWarnstate == null ? null : fWarnstate.trim();
    }
}