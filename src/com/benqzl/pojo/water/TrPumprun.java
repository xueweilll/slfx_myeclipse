package com.benqzl.pojo.water;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/** 
* @ClassName: TrPumprun 
* @Description: 机组运行工况表
* @author xw 
* @date 2016年1月30日 下午2:20:55 
*  
*/
public class TrPumprun implements Serializable{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	/** 
    * @Fields fId : 主键(用一句话描述这个变量表示什么) 
    */ 
    private String fId;

    /** 
    * @Fields fStationcode : 枢纽code
    */ 
    private String fStationcode;

    /** 
    * @Fields fPumpcode : 机组code
    */ 
    private String fPumpcode;

    /** 
    * @Fields fTime : 记录时间
    */ 
    private Date fTime;

    /** 
    * @Fields fRunstate : 机组运行状态   1：启动；2：停止；3：报警
    */ 
    private String fRunstate;

    /** 
    * @Fields fDir : 引排方向   1：引水；2：排水
    */ 
    private String fDir;

    /** 
    * @Fields fVa : 电机电压Va
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
    * @Fields fPower : 实时功率
    */ 
    private BigDecimal fPower;

    /** 
    * @Fields fFlow : 实时流量
    */ 
    private BigDecimal fFlow;

    /** 
    * @Fields fTemp : 电机轴温度
    */ 
    private BigDecimal fTemp;
    
    /** 
    * @Fields scode : list枢纽code 
    */ 
    private List<String> scode;
    
    /** 
    * @Fields ucode : list机组code 
    */ 
    private List<String> ucode;

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

    public String getfPumpcode() {
        return fPumpcode;
    }

    public void setfPumpcode(String fPumpcode) {
        this.fPumpcode = fPumpcode == null ? null : fPumpcode.trim();
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

    public String getfDir() {
        return fDir;
    }

    public void setfDir(String fDir) {
        this.fDir = fDir == null ? null : fDir.trim();
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

    public BigDecimal getfPower() {
        return fPower;
    }

    public void setfPower(BigDecimal fPower) {
        this.fPower = fPower;
    }

    public BigDecimal getfFlow() {
        return fFlow;
    }

    public void setfFlow(BigDecimal fFlow) {
        this.fFlow = fFlow;
    }

    public BigDecimal getfTemp() {
        return fTemp;
    }

    public void setfTemp(BigDecimal fTemp) {
        this.fTemp = fTemp;
    }

	public List<String> getScode() {
		return scode;
	}

	public void setScode(List<String> scode) {
		this.scode = scode;
	}

	public List<String> getUcode() {
		return ucode;
	}

	public void setUcode(List<String> ucode) {
		this.ucode = ucode;
	}
}