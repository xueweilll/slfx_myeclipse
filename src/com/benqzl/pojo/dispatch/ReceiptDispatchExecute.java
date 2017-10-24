package com.benqzl.pojo.dispatch;

import java.util.Date;
import java.util.List;

import com.benqzl.pojo.system.Station;

public class ReceiptDispatchExecute {
    private String id;

    private String sid;
    
    private Station station;

    public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	private String rdstationid;

    private Date gateexecutetime;

    private Date unitexecutetime;

    private String memo;
    
    private Station s;
    
    private String startouterlevel;
  
    private String startinlandlevel;
    
    private String stopouterlevel;
    
    private String stopinlandlevel;
    
    private List<ReceiptDispatchExecuteGate> executegate;
    
    private List<ReceiptDispatchExecuteUnits> executeunits;
    
    public List<ReceiptDispatchExecuteUnits> getExecuteunits() {
		return executeunits;
	}

	public void setExecuteunits(List<ReceiptDispatchExecuteUnits> executeunits) {
		this.executeunits = executeunits;
	}

	public List<ReceiptDispatchExecuteGate> getExecutegate() {
		return executegate;
	}

	public void setExecutegate(List<ReceiptDispatchExecuteGate> executegate) {
		this.executegate = executegate;
	}

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

	private ReceiptDispatchStations rds;
    
    private ReceiptDispatchExecutePeople rdep;
    
    private List<ReceiptDispatchExecutePeople> rdeplist;

    public List<ReceiptDispatchExecutePeople> getRdeplist() {
		return rdeplist;
	}

	public void setRdeplist(List<ReceiptDispatchExecutePeople> rdeplist) {
		this.rdeplist = rdeplist;
	}

	public ReceiptDispatchExecutePeople getRdep() {
		return rdep;
	}

	public void setRdep(ReceiptDispatchExecutePeople rdep) {
		this.rdep = rdep;
	}

	public ReceiptDispatchStations getRds() {
		return rds;
	}

	public void setRds(ReceiptDispatchStations rds) {
		this.rds = rds;
	}

	public Station getS() {
		return s;
	}

	public void setS(Station s) {
		this.s = s;
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

    public String getRdstationid() {
        return rdstationid;
    }

    public void setRdstationid(String rdstationid) {
        this.rdstationid = rdstationid == null ? null : rdstationid.trim();
    }

    public Date getGateexecutetime() {
        return gateexecutetime;
    }

    public void setGateexecutetime(Date gateexecutetime) {
        this.gateexecutetime = gateexecutetime;
    }

    public Date getUnitexecutetime() {
        return unitexecutetime;
    }

    public void setUnitexecutetime(Date unitexecutetime) {
        this.unitexecutetime = unitexecutetime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}