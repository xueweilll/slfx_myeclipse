package com.benqzl.pojo.patrol;

import java.util.Date;

import com.benqzl.pojo.system.Station;
import com.benqzl.pojo.system.User;

public class PatrolPlanDetails {
    private String id;

    private String patrolplanid;

    private String sid;

    private Long state;

    private String receipter;
    
    private User receiptuser;

    public User getReceiptuser() {
		return receiptuser;
	}

	public void setReceiptuser(User receiptuser) {
		this.receiptuser = receiptuser;
	}

	private Date receipttime;

    private PatrolPlan patrolplan;
    
    private Patrol patrol;
    
    private Station station;
    
    public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public PatrolPlan getPatrolplan() {
		return patrolplan;
	}

	public void setPatrolplan(PatrolPlan patrolplan) {
		this.patrolplan = patrolplan;
	}

	public Patrol getPatrol() {
		return patrol;
	}

	public void setPatrol(Patrol patrol) {
		this.patrol = patrol;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatrolplanid() {
        return patrolplanid;
    }

    public void setPatrolplanid(String patrolplanid) {
        this.patrolplanid = patrolplanid == null ? null : patrolplanid.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getReceipter() {
        return receipter;
    }

    public void setReceipter(String receipter) {
        this.receipter = receipter == null ? null : receipter.trim();
    }

    public Date getReceipttime() {
        return receipttime;
    }

    public void setReceipttime(Date receipttime) {
        this.receipttime = receipttime;
    }
}