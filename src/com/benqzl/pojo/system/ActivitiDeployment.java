package com.benqzl.pojo.system;

import java.util.Date;

public class ActivitiDeployment {
    private String id;

    private String deploymentname;
    
    private String deploymentId;

    private String processdefinitionid;

    private String processdefinitionKey;
    
    private String version;

    private Date createtime;

    private Date edittime;

    private String edituserid;

    private Long type;
    
    private Long isDel;
    
    
    public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getProcessdefinitionKey() {
		return processdefinitionKey;
	}

	public void setProcessdefinitionKey(String processdefinitionKey) {
		this.processdefinitionKey = processdefinitionKey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getIsDel() {
		return isDel;
	}

	public void setIsDel(Long isDel) {
		this.isDel = isDel;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeploymentname() {
        return deploymentname;
    }

    public void setDeploymentname(String deploymentname) {
        this.deploymentname = deploymentname == null ? null : deploymentname.trim();
    }

    public String getProcessdefinitionid() {
        return processdefinitionid;
    }

    public void setProcessdefinitionid(String processdefinitionid) {
        this.processdefinitionid = processdefinitionid == null ? null : processdefinitionid.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getEdittime() {
        return edittime;
    }

    public void setEdittime(Date edittime) {
        this.edittime = edittime;
    }

    public String getEdituserid() {
        return edituserid;
    }

    public void setEdituserid(String edituserid) {
        this.edituserid = edituserid == null ? null : edituserid.trim();
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}