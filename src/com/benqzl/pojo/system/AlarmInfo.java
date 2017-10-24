package com.benqzl.pojo.system;

import java.math.BigDecimal;

public class AlarmInfo {

	// 枢纽id
	private String stationId;

	// 枢纽名称
	private String stationName;

	// 是否内河报警
	private boolean isinAlarm;

	// 是否外河报警
	private boolean isoutAlarm;

	// 内河报警水位
	private BigDecimal inval;

	// 外河报警水位
	private BigDecimal outval;

	private String inCompTxt;

	private String outCompTxt;

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public boolean getIsinAlarm() {
		return isinAlarm;
	}

	public void setIsinAlarm(boolean isinAlarm) {
		this.isinAlarm = isinAlarm;
	}

	public boolean getIsoutAlarm() {
		return isoutAlarm;
	}

	public void setIsoutAlarm(boolean isoutAlarm) {
		this.isoutAlarm = isoutAlarm;
	}

	public BigDecimal getInval() {
		return inval;
	}

	public void setInval(BigDecimal inval) {
		this.inval = inval;
	}

	public BigDecimal getOutval() {
		return outval;
	}

	public void setOutval(BigDecimal outval) {
		this.outval = outval;
	}

	public String getInCompTxt() {
		return inCompTxt;
	}

	public void setInCompTxt(String inCompTxt) {
		this.inCompTxt = inCompTxt;
	}

	public String getOutCompTxt() {
		return outCompTxt;
	}

	public void setOutCompTxt(String outCompTxt) {
		this.outCompTxt = outCompTxt;
	}

	public String alarmText() {
		String text = "当前" + stationName + "报警,报警内容：";

		if (isinAlarm && isoutAlarm) {
			text += "内河报警，报警水位:" + inval.toString() + "m," + inCompTxt;
			text += ";";
			text += "外河报警，报警水位:" + outval.toString() + "m," + outCompTxt;
		} else if (isinAlarm) {
			text += "内河报警，报警水位:" + inval.toString() + "m," + inCompTxt;
		} else if (isoutAlarm) {
			text += "外河报警，报警水位:" + outval.toString() + "m," + outCompTxt;
		} else {
			text = "";
		}

		return text;
	}
}
