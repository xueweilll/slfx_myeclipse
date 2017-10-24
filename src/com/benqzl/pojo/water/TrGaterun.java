package com.benqzl.pojo.water;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
* @ClassName: TrGaterun 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xw 
* @date 2016年1月30日 下午2:23:51 
*  
*/
public class TrGaterun implements Serializable{
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
    * @Fields fGatecode : 闸门编号
    */ 
    private String fGatecode;

    /** 
    * @Fields fTime : 记录时间
    */ 
    private Date fTime;

    /** 
    * @Fields fRunstate : 闸门运行状态
    */ 
    private String fRunstate;

    /** 
    * @Fields fVa : 闸门运行状态
    */ 
    private Long fVa;

    /** 
    * @Fields fVb : 电机电压Vb
    */ 
    private Long fVb;

    /** 
    * @Fields fVc : 电机电压Vc
    */ 
    private Long fVc;

    /** 
    * @Fields fIa : 电机电流Ia
    */ 
    private BigDecimal fIa;

    /** 
    * @Fields fIb : 电机电流Ib
    */ 
    private BigDecimal fIb;

    /** 
    * @Fields fIc : 电机电流Ic
    */ 
    private BigDecimal fIc;

    /** 
    * @Fields fLevel : 闸门开度
    */ 
    private BigDecimal fLevel;

    /** 
    * @Fields fPositionstate : 位置状态   1：全开；2：中间；3：全关
    */ 
    private String fPositionstate;

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

    public String getfGatecode() {
        return fGatecode;
    }

    public void setfGatecode(String fGatecode) {
        this.fGatecode = fGatecode == null ? null : fGatecode.trim();
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    public String getfRunstate() {
        return fRunstate;
    }

    public void setfRunstate(String fRunstate) {
        this.fRunstate = fRunstate == null ? null : fRunstate.trim();
    }

    public Long getfVa() {
        return fVa;
    }

    public void setfVa(Long fVa) {
        this.fVa = fVa;
    }

    public Long getfVb() {
        return fVb;
    }

    public void setfVb(Long fVb) {
        this.fVb = fVb;
    }

    public Long getfVc() {
        return fVc;
    }

    public void setfVc(Long fVc) {
        this.fVc = fVc;
    }

    public BigDecimal getfIa() {
        return fIa;
    }

    public void setfIa(BigDecimal fIa) {
        this.fIa = fIa;
    }

    public BigDecimal getfIb() {
        return fIb;
    }

    public void setfIb(BigDecimal fIb) {
        this.fIb = fIb;
    }

    public BigDecimal getfIc() {
        return fIc;
    }

    public void setfIc(BigDecimal fIc) {
        this.fIc = fIc;
    }

    public BigDecimal getfLevel() {
        return fLevel;
    }

    public void setfLevel(BigDecimal fLevel) {
        this.fLevel = fLevel;
    }

    public String getfPositionstate() {
        return fPositionstate;
    }

    public void setfPositionstate(String fPositionstate) {
        this.fPositionstate = fPositionstate == null ? null : fPositionstate.trim();
    }
}