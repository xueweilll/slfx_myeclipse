package com.benqzl.pojo.oa;

import java.util.Date;

public class MessageReceiver {
    private String id;

    private Message message;

    private String receiverid;

    private Date receiptdate;

    private Long state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid == null ? null : receiverid.trim();
    }

    public Date getReceiptdate() {
        return receiptdate;
    }

    public void setReceiptdate(Date receiptdate) {
        this.receiptdate = receiptdate;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }
}