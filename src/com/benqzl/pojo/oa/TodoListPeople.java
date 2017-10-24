package com.benqzl.pojo.oa;

import java.util.Date;

import com.benqzl.pojo.system.User;

public class TodoListPeople {
    private String id;

    private String doid;

    private String dotoer;

    private Long state;

    private Date viewdate;

    private Date feedbackdate;
    
    private User user;            
    
    private TodoList todolist;        
    
	public TodoList getTodolist() {
		return todolist;
	}

	public void setTodolist(TodoList todolist) {
		this.todolist = todolist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDoid() {
        return doid;
    }

    public void setDoid(String doid) {
        this.doid = doid == null ? null : doid.trim();
    }

    public String getDotoer() {
        return dotoer;
    }

    public void setDotoer(String dotoer) {
        this.dotoer = dotoer == null ? null : dotoer.trim();
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Date getViewdate() {
        return viewdate;
    }

    public void setViewdate(Date viewdate) {
        this.viewdate = viewdate;
    }

    public Date getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(Date feedbackdate) {
        this.feedbackdate = feedbackdate;
    }
}