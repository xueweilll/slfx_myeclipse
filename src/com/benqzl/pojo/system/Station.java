package com.benqzl.pojo.system;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Station implements Comparable<Station> {
	private String id;

	private String code;

	private String name;

	/**
	 * @Fields lat : 纬度(用一句话描述这个变量表示什么)
	 */
	private BigDecimal lat;

	/**
	 * @Fields lon : 经度(用一句话描述这个变量表示什么)
	 */
	private BigDecimal lon;

	private String departmentid;

	private Long levels;

	private Date createtime;

	private Date edittime;

	private Long isdel;

	private String remark;

	private String nvrusername;

	private String nvrpassword;

	private String nvrprot;

	private String nvrurl;

	private List<String> names;

	private Department department;

	private BigDecimal controlwaterlevel;

	private String imgurl;

	private String address;

	// 内河上限
	private BigDecimal inwatertop;

	// 内河下限
	private BigDecimal inwaterdown;

	// 外河上限
	private BigDecimal outwatertop;

	// 外河下限
	private BigDecimal outwaterdown;

	private String serviceport;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private List<Gate> gates;

	private List<Unit> units;

	private List<Camera> cameras;

	public List<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}

	public List<Gate> getGates() {
		return gates;
	}

	public void setGates(List<Gate> gates) {
		this.gates = gates;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public BigDecimal getControlwaterlevel() {
		return controlwaterlevel;
	}

	public void setControlwaterlevel(BigDecimal controlwaterlevel) {
		this.controlwaterlevel = controlwaterlevel;
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

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public Long getLevels() {
		return levels;
	}

	public void setLevels(Long levels) {
		this.levels = levels;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getNvrusername() {
		return nvrusername;
	}

	public void setNvrusername(String nvrusername) {
		this.nvrusername = nvrusername == null ? null : nvrusername.trim();
	}

	public String getNvrpassword() {
		return nvrpassword;
	}

	public void setNvrpassword(String nvrpassword) {
		this.nvrpassword = nvrpassword == null ? null : nvrpassword.trim();
	}

	public String getNvrprot() {
		return nvrprot;
	}

	public void setNvrprot(String nvrprot) {
		this.nvrprot = nvrprot == null ? null : nvrprot.trim();
	}

	public String getNvrurl() {
		return nvrurl;
	}

	public void setNvrurl(String nvrurl) {
		this.nvrurl = nvrurl == null ? null : nvrurl.trim();
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public BigDecimal getInwatertop() {
		return inwatertop;
	}

	public void setInwatertop(BigDecimal inwatertop) {
		this.inwatertop = inwatertop;
	}

	public BigDecimal getInwaterdown() {
		return inwaterdown;
	}

	public void setInwaterdown(BigDecimal inwaterdown) {
		this.inwaterdown = inwaterdown;
	}

	public BigDecimal getOutwatertop() {
		return outwatertop;
	}

	public void setOutwatertop(BigDecimal outwatertop) {
		this.outwatertop = outwatertop;
	}

	public BigDecimal getOutwaterdown() {
		return outwaterdown;
	}

	public void setOutwaterdown(BigDecimal outwaterdown) {
		this.outwaterdown = outwaterdown;
	}

	// 计算内外河水位报警
	public AlarmInfo getAlarmInfo(BigDecimal inwaterlevel, BigDecimal outwaterlevel) {
		AlarmInfo alarmInfo = new AlarmInfo();
		alarmInfo.setStationId(id);
		alarmInfo.setStationName(name);
		alarmInfo.setIsinAlarm(false);
		alarmInfo.setIsoutAlarm(false);
		if (inwaterlevel != null) {
			if (inwatertop != null && inwaterdown != null) {
				if (inwaterlevel.compareTo(inwaterdown) == -1) {
					alarmInfo.setIsinAlarm(true);
					alarmInfo.setInval(inwaterlevel);
					alarmInfo.setInCompTxt("内河低于下限");
				}
				if (inwaterlevel.compareTo(inwatertop) == 1) {
					alarmInfo.setIsinAlarm(true);
					alarmInfo.setInval(inwaterlevel);
					alarmInfo.setInCompTxt("内河高于上限");
				}
			} else if (inwatertop == null && inwaterdown != null) {
				if (inwaterlevel.compareTo(inwaterdown) == -1) {
					alarmInfo.setIsinAlarm(true);
					alarmInfo.setInval(inwaterlevel);
					alarmInfo.setInCompTxt("内河低于下限");
				}
			} else if (inwaterdown == null && inwatertop != null) {
				if (inwaterlevel.compareTo(inwatertop) == 1) {
					alarmInfo.setIsinAlarm(true);
					alarmInfo.setOutval(inwaterlevel);
					alarmInfo.setInCompTxt("内河高于上限");
				}
			}
		}
		if (outwaterlevel != null) {
			if (outwatertop != null && outwaterdown != null) {
				if (outwaterlevel.compareTo(outwaterdown) == -1) {
					alarmInfo.setIsoutAlarm(true);
					alarmInfo.setOutval(outwaterlevel);
					alarmInfo.setOutCompTxt("外河低于下限");
				}
				if (outwaterlevel.compareTo(outwatertop) == 1) {
					alarmInfo.setIsoutAlarm(true);
					alarmInfo.setOutval(outwaterlevel);
					alarmInfo.setOutCompTxt("外河高于上限");
				}
			} else if (outwatertop == null && outwaterdown != null) {
				if (outwaterlevel.compareTo(outwaterdown) == -1) {
					alarmInfo.setIsoutAlarm(true);
					alarmInfo.setOutval(outwaterlevel);
					alarmInfo.setOutCompTxt("外河低于下限");
				}
			} else if (outwaterdown == null && outwatertop != null) {
				if (outwaterlevel.compareTo(outwatertop) == 1) {
					alarmInfo.setIsoutAlarm(true);
					alarmInfo.setOutval(outwaterlevel);
					alarmInfo.setOutCompTxt("外河高于上限");
				}
			}
		}
		return alarmInfo;
	}

	@Override
	public int compareTo(Station o) {
		return this.getLevels().compareTo(o.getLevels());
	}

	public String getServiceport() {
		return serviceport;
	}

	public void setServiceport(String serviceport) {
		this.serviceport = serviceport;
	}
}