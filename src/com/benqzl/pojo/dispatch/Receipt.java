package com.benqzl.pojo.dispatch;

import java.util.Date;

import com.benqzl.pojo.system.Employee;
import com.benqzl.pojo.system.User;

public class Receipt {
    private String id;

    private Long dispatchtype;

    private Long way;

    private String launcher;

    private Date launchtime;

    private Date endtime;

    private String receipter;
    
    private User receipteUser;
    
    private String aduitmemo;
    
    //不敢动
    private ReceiptDispatch redispatch;
    

    private Employee employee;

	private Date receiptetime;

    private Long state;

    private String creater;

    private Date createtime;

    private String handler;
    
    private User handlerUser;

    private Date handletime;
    
    private String fileaddress;

    public User getReceipteUser() {
		return receipteUser;
	}

	public void setReceipteUser(User receipteUser) {
		this.receipteUser = receipteUser;
	}
    public User getHandlerUser() {
		return handlerUser;
	}

	public void setHandlerUser(User handlerUser) {
		this.handlerUser = handlerUser;
	}

	private String code;

    private String memo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Long getDispatchtype() {
        return dispatchtype;
    }

    public void setDispatchtype(Long dispatchtype) {
        this.dispatchtype = dispatchtype;
    }

    public Long getWay() {
        return way;
    }

    public void setWay(Long way) {
        this.way = way;
    }

    public String getLauncher() {
        return launcher;
    }

    public void setLauncher(String launcher) {
        this.launcher = launcher == null ? null : launcher.trim();
    }

    public Date getLaunchtime() {
        return launchtime;
    }

    public void setLaunchtime(Date launchtime) {
        this.launchtime = launchtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getReceipter() {
        return receipter;
    }

    public void setReceipter(String receipter) {
        this.receipter = receipter == null ? null : receipter.trim();
    }

    public Date getReceiptetime() {
        return receiptetime;
    }

    public void setReceiptetime(Date receiptetime) {
        this.receiptetime = receiptetime;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
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

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler == null ? null : handler.trim();
    }

    public Date getHandletime() {
        return handletime;
    }

    public void setHandletime(Date handletime) {
        this.handletime = handletime;
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
    
    public String getAduitmemo() {
        return aduitmemo;
    }

    public void setAduitmemo(String aduitmemo) {
        this.aduitmemo = aduitmemo == null ? null : aduitmemo.trim();
    }

	public ReceiptDispatch getRedispatch() {
		return redispatch;
	}

	public void setRedispatch(ReceiptDispatch redispatch) {
		this.redispatch = redispatch;
	}
	
	public String getFileaddress() {
        return fileaddress;
    }

    public void setFileaddress(String fileaddress) {
        this.fileaddress = fileaddress == null ? null : fileaddress.trim();
    }

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}