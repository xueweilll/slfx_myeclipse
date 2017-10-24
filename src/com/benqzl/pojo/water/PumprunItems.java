package com.benqzl.pojo.water;

import java.util.Date;

/**
 * @ClassName: PumprunItems
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 冯庆国
 * @date 2016年8月3日 上午9:58:42
 * 
 */
public class PumprunItems {
	private String id;

	private String pid;

	private Date starttime;

	private Date endtime;

	private float sumH;

	private float sumF;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public float getSumH() {
		return sumH;
	}

	public void setSumH(float sumH) {
		this.sumH = sumH;
	}

	public float getSumF() {
		return sumF;
	}

	public void setSumF(float sumF) {
		this.sumF = sumF;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	/**
	 * @Title: sum
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param bdt 查询开始时间
	 * @param @param edt 查询结束时间
	 * @return void 返回类型
	 * @throws
	 */
	public void sum(Date bdt, Date edt,Float designdischarge) {
		/**
		 * 开机时间在查询起始时间外，但关机时间在查询结束时间内。
		 */
		if(bdt==null&&edt==null){
			sumH = (float)(endtime.getTime() - starttime.getTime()) /(float)1000 /(float) 60 / (float)60;
			sumF=sumH*designdischarge;
			return;
		}
		if (starttime.getTime() < bdt.getTime()
				&& endtime.getTime() < edt.getTime()) {
			sumH = (float)(endtime.getTime() - bdt.getTime()) / (float)1000 /(float) 60 / (float)60;
			sumF=sumH*designdischarge;
			return;
		}

		/**
		 * 开机时间在查询起始时间内，且关机时间在查询结束时间内。
		 */
		if (starttime.getTime() > bdt.getTime()
				&& endtime.getTime() < edt.getTime()) {
			sumH = (float)(endtime.getTime() - starttime.getTime()) /(float)1000 /(float) 60 / (float)60;
			sumF=sumH*designdischarge;
			return;
		}

		/**
		 * 开机时间在查询起始时间之内，但关机时间在查询结束时间之外。
		 */
		if (starttime.getTime() > bdt.getTime()
				&& endtime.getTime() > edt.getTime()) {
			sumH = (float)(edt.getTime() - starttime.getTime()) /(float)1000 /(float) 60 / (float)60;
			sumF=sumH*designdischarge;
			return;
		}

		/**
		 * 开机使劲按在查询起始时间之外，且关机时间在查询结束时间之外。
		 */
		if (starttime.getTime() < bdt.getTime()
				&& endtime.getTime() > edt.getTime()) {
			sumH = (float)(edt.getTime() - bdt.getTime()) /(float)1000 /(float) 60 / (float)60;
			sumF=sumH*designdischarge;
			return;
		}

	}
}