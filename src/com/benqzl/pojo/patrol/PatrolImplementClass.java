package com.benqzl.pojo.patrol;

import java.util.List;


public class PatrolImplementClass {
    private String id;

    private String classes;
    
    private List<PatrolImplementClass> implementClasses;
    
    private List<PatrolImplementTable> implementTables;
    
    public List<PatrolImplementTable> getImplementTables() {
		return implementTables;
	}

	public void setImplementTables(List<PatrolImplementTable> implementTables) {
		this.implementTables = implementTables;
	}

	public List<PatrolImplementClass> getImplementClasses() {
		return implementClasses;
	}

	public void setImplementClasses(List<PatrolImplementClass> implementClasses) {
		this.implementClasses = implementClasses;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes == null ? null : classes.trim();
    }
}