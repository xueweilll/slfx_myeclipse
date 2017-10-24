package com.benqzl.pojo.patrol;


public class PatrolSpecialResolveDetails {
    private String id;

    private String rid;

    private String did;
    
    private PatrolSpecialFolw folw;
    
    private PatrolSpecialResolve specialResolve;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

	public PatrolSpecialResolve getSpecialResolve() {
		return specialResolve;
	}

	public void setSpecialResolve(PatrolSpecialResolve specialResolve) {
		this.specialResolve = specialResolve;
	}

	public PatrolSpecialFolw getFolw() {
		return folw;
	}

	public void setFolw(PatrolSpecialFolw folw) {
		this.folw = folw;
	}
}