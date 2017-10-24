package com.benqzl.pojo.oa;

import java.util.Date;

import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;

public class DocumentReceiver {
    private String id;
    
    private Document document;
    
    private User user;
    
    private Employee employee;
    
    private String documentid;

    private String receiverid;

    private Date receiptdate;

    private Long state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid == null ? null : documentid.trim();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


}