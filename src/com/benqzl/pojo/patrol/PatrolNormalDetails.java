package com.benqzl.pojo.patrol;

public class PatrolNormalDetails {
    private String id;

    private String patrolid;

    private String enumid;

    private Long handletype;

    private String handlecontents;

    private String contents;

    private Long istype;
    
    private PatrolNormal patrolnormal;
    
    private PatrolEnumid patrolenumid;
    
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPatrolid() {
        return patrolid;
    }

    public void setPatrolid(String patrolid) {
        this.patrolid = patrolid == null ? null : patrolid.trim();
    }

    public String getEnumid() {
        return enumid;
    }

    public void setEnumid(String enumid) {
        this.enumid = enumid == null ? null : enumid.trim();
    }

    public Long getHandletype() {
        return handletype;
    }

    public void setHandletype(Long handletype) {
        this.handletype = handletype;
    }

    public String getHandlecontents() {
        return handlecontents;
    }

    public void setHandlecontents(String handlecontents) {
        this.handlecontents = handlecontents == null ? null : handlecontents.trim();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public Long getIstype() {
        return istype;
    }

    public void setIstype(Long istype) {
        this.istype = istype;
    }

	public PatrolNormal getPatrolnormal() {
		return patrolnormal;
	}

	public void setPatrolnormal(PatrolNormal patrolnormal) {
		this.patrolnormal = patrolnormal;
	}

	public PatrolEnumid getPatrolenumid() {
		return patrolenumid;
	}

	public void setPatrolenumid(PatrolEnumid patrolenumid) {
		this.patrolenumid = patrolenumid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


    
	
}