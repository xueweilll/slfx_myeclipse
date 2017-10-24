package com.benqzl.pojo.water;

import java.io.Serializable;

/** 
* @ClassName: TbWarndict 
* @Description: 设备报警目录表(这里用一句话描述这个类的作用) 
* @author xw 
* @date 2016年1月30日 下午2:16:51 
*  
*/
public class TbWarndict implements Serializable{
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;

	//主键
    private String fId;

    //枢纽code
    private String fStationcode;

    //报警编码
    private String fCode;

    //报警名称
    private String fCaption;

    //备注
    private String fMemo;

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

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }

    public String getfCaption() {
        return fCaption;
    }

    public void setfCaption(String fCaption) {
        this.fCaption = fCaption == null ? null : fCaption.trim();
    }

    public String getfMemo() {
        return fMemo;
    }

    public void setfMemo(String fMemo) {
        this.fMemo = fMemo == null ? null : fMemo.trim();
    }
}