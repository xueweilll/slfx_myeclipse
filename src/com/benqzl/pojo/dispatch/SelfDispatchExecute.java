package com.benqzl.pojo.dispatch;

import java.util.Date;
import java.util.List;

import com.benqzl.pojo.system.Station;

public class SelfDispatchExecute {
    private String id;

    private String sid;

    private String dispatchstationid;

    private Date executetime;

    private Date unitexecutetime;
    
    private String memo;
    private SelfDispatchExecutePeople selfDispatchExecutePeople;
    private SelfDispatchStations sds;
    private Station s;   
    
    private List<SelfDispatchExecutePeople> sdep;
    
    private List<SelfDispatchExecuteGate> executegates;
    
    private List<SelfDispatchExecuteUnits> executeunits;
    
    public List<SelfDispatchExecutePeople> getSdep() {
		return sdep;
	}

	public void setSdep(List<SelfDispatchExecutePeople> sdep) {
		this.sdep = sdep;
	}
	
	public List<SelfDispatchExecuteGate> getExecutegates() {
		return executegates;
	}

	public void setExecutegates(List<SelfDispatchExecuteGate> executegates) {
		this.executegates = executegates;
	}

	public List<SelfDispatchExecuteUnits> getExecuteunits() {
		return executeunits;
	}

	public void setExecuteunits(List<SelfDispatchExecuteUnits> executeunits) {
		this.executeunits = executeunits;
	}

	public Station getS() {
		return s;
	}

	public void setS(Station s) {
		this.s = s;
	}

	public SelfDispatchStations getSds() {
		return sds;
	}

	public void setSds(SelfDispatchStations sds) {
		this.sds = sds;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

    public SelfDispatchExecutePeople getSelfDispatchExecutePeople() {
		return selfDispatchExecutePeople;
	}

	public void setSelfDispatchExecutePeople(
			SelfDispatchExecutePeople selfDispatchExecutePeople) {
		this.selfDispatchExecutePeople = selfDispatchExecutePeople;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getDispatchstationid() {
        return dispatchstationid;
    }

    public void setDispatchstationid(String dispatchstationid) {
        this.dispatchstationid = dispatchstationid == null ? null : dispatchstationid.trim();
    }

    public Date getExecutetime() {
        return executetime;
    }

    public void setExecutetime(Date executetime) {
        this.executetime = executetime;
    }

    public Date getUnitexecutetime() {
        return unitexecutetime;
    }

    public void setUnitexecutetime(Date unitexecutetime) {
        this.unitexecutetime = unitexecutetime;
    }
    
    private String startouterlevel;
    
    private String startinlandlevel;
    
    private String stopouterlevel;
    
    private String stopinlandlevel;
    
    public String getStartouterlevel() {
		return startouterlevel;
	}

	public void setStartouterlevel(String startouterlevel) {
		this.startouterlevel = startouterlevel;
	}

	public String getStartinlandlevel() {
		return startinlandlevel;
	}

	public void setStartinlandlevel(String startinlandlevel) {
		this.startinlandlevel = startinlandlevel;
	}

	public String getStopouterlevel() {
		return stopouterlevel;
	}

	public void setStopouterlevel(String stopouterlevel) {
		this.stopouterlevel = stopouterlevel;
	}

	public String getStopinlandlevel() {
		return stopinlandlevel;
	}

	public void setStopinlandlevel(String stopinlandlevel) {
		this.stopinlandlevel = stopinlandlevel;
	}
}