package com.benqzl.pojo.patrol;

import java.util.Date;
import java.util.List;

import com.benqzl.pojo.system.User;

public class PatrolSpecialIssue {
    private String id;
    
    private String code;

    //关联PatrolSpecialResolve
    private  PatrolSpecialResolve patrolSpecialResolve;
    //关联PatrolSpecialFolw
    private PatrolSpecialFolw patrolSpecialFolw;
    
    private List<PatrolSpecialDeaprtmentReport> deaprtmentReports;
    
    public List<PatrolSpecialDeaprtmentReport> getDeaprtmentReports() {
		return deaprtmentReports;
	}

	public void setDeaprtmentReports(
			List<PatrolSpecialDeaprtmentReport> deaprtmentReports) {
		this.deaprtmentReports = deaprtmentReports;
	}

	public PatrolSpecialResolve getPatrolSpecialResolve() {
		return patrolSpecialResolve;
	}

	public void setPatrolSpecialResolve(PatrolSpecialResolve patrolSpecialResolve) {
		this.patrolSpecialResolve = patrolSpecialResolve;
	}

	public PatrolSpecialFolw getPatrolSpecialFolw() {
		return patrolSpecialFolw;
	}

	public void setPatrolSpecialFolw(PatrolSpecialFolw patrolSpecialFolw) {
		this.patrolSpecialFolw = patrolSpecialFolw;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String creater;

    private Date createtime;

    private Long classes;

    private String content;

    private String remark;

	private User user;
    
   /* private PatrolSpecialFolw patrolSpecialFolw;
   
    public PatrolSpecialFolw getPatrolSpecialFolw() {
		return patrolSpecialFolw;
	}

	public void setPatrolSpecialFolw(PatrolSpecialFolw patrolSpecialFolw) {
		this.patrolSpecialFolw = patrolSpecialFolw;
	}*/
	
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

    public Long getClasses() {
        return classes;
    }

    public void setClasses(Long classes) {
        this.classes = classes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}