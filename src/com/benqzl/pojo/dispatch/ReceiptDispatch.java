package com.benqzl.pojo.dispatch;

import java.util.Date;
import java.util.List;


public class ReceiptDispatch {
    private String id;

    private String receiptid;

    private String creater;
    
  

    private Date createtime;

    private String code;

    private String memo;

    private String sender;
    
 

    private Date sendtime;

    private String transmitcontents;

    private String transmiter;

    private Date transmittime;

    //0：保存 1：已提交 2：片区调度中 3：片区调度完成  9：删除
    private Long state;

    private Date completetime;

    private String dispatchtype;
    
    private Receipt receipt;
    
    private ReceiptDispatchUserName names;

    private List<ReceiptDispatchInstructions> receiptDispatchInstructions;
    
    private List<ReceiptDispatchStations> dispatchStations;
    
    private List<ReceiptDispatchDepartment> rdDepartments;
    
    public List<ReceiptDispatchDepartment> getRdDepartments() {
		return rdDepartments;
	}

	public void setRdDepartments(List<ReceiptDispatchDepartment> rdDepartments) {
		this.rdDepartments = rdDepartments;
	}

	//0：未转发 1：已转发  2：回放中 3：督查中  4：转发完成
    private Long trstate;
   
	public ReceiptDispatchUserName getNames() {
		return names;
	}

	public void setNames(ReceiptDispatchUserName names) {
		this.names = names;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(String receiptid) {
        this.receiptid = receiptid == null ? null : receiptid.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getTransmitcontents() {
        return transmitcontents;
    }

    public void setTransmitcontents(String transmitcontents) {
        this.transmitcontents = transmitcontents == null ? null : transmitcontents.trim();
    }

    public String getTransmiter() {
        return transmiter;
    }

    public void setTransmiter(String transmiter) {
        this.transmiter = transmiter == null ? null : transmiter.trim();
    }

    public Date getTransmittime() {
        return transmittime;
    }

    public void setTransmittime(Date transmittime) {
        this.transmittime = transmittime;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Date getCompletetime() {
        return completetime;
    }

    public void setCompletetime(Date completetime) {
        this.completetime = completetime;
    }

    public String getDispatchtype() {
        return dispatchtype;
    }

    public void setDispatchtype(String dispatchtype) {
        this.dispatchtype = dispatchtype == null ? null : dispatchtype.trim();
    }

	public List<ReceiptDispatchInstructions> getReceiptDispatchInstructions() {
		return receiptDispatchInstructions;
	}

	public void setReceiptDispatchInstructions(
			List<ReceiptDispatchInstructions> receiptDispatchInstructions) {
		this.receiptDispatchInstructions = receiptDispatchInstructions;
	}

	public List<ReceiptDispatchStations> getDispatchStations() {
		return dispatchStations;
	}

	public void setDispatchStations(List<ReceiptDispatchStations> dispatchStations) {
		this.dispatchStations = dispatchStations;
	}
	
	public Long getTrstate() {
        return trstate;
    }

    public void setTrstate(Long trstate) {
        this.trstate = trstate;
    }
}