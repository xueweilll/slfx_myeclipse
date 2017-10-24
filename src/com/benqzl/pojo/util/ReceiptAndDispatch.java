package com.benqzl.pojo.util;

import com.benqzl.pojo.dispatch.Receipt;
import com.benqzl.pojo.dispatch.ReceiptDispatch;

public class ReceiptAndDispatch {
	private ReceiptDispatch dispatch;
	private Receipt receipt;
	private String lname;
	private String rname;
	private String rcname;
	private String dcname;
	private String id;
	private String transmiter;
	private String edname;
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRcname() {
		return rcname;
	}
	public void setRcname(String rcname) {
		this.rcname = rcname;
	}
	public String getDcname() {
		return dcname;
	}
	public void setDcname(String dcname) {
		this.dcname = dcname;
	}
	public ReceiptDispatch getDispatch() {
		return dispatch;
	}
	public void setDispatch(ReceiptDispatch dispatch) {
		this.dispatch = dispatch;
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
		this.id = id;
	}
	public String getTransmiter() {
		return transmiter;
	}
	public void setTransmiter(String transmiter) {
		this.transmiter = transmiter;
	}
	public String getEdname() {
		return edname;
	}
	public void setEdname(String edname) {
		this.edname = edname;
	}
}
