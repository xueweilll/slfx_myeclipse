package com.benqzl.pojo.material;

import java.math.BigDecimal;
import java.util.Date;

import com.benqzl.pojo.system.Material;

public class MaterialManage {
    private String id;

    private Date createtime;

    private Date edittime;

    private String source;

    private String memo;

    private String storageid;

    private BigDecimal oldstorage;

    private BigDecimal newstorage;

    private Long type;

    private Long typedate;
    
    private Material material;
    
    private Long count;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getStorageid() {
        return storageid;
    }

    public void setStorageid(String storageid) {
        this.storageid = storageid == null ? null : storageid.trim();
    }

    public BigDecimal getOldstorage() {
        return oldstorage;
    }

    public void setOldstorage(BigDecimal oldstorage) {
        this.oldstorage = oldstorage;
    }

    public BigDecimal getNewstorage() {
        return newstorage;
    }

    public void setNewstorage(BigDecimal newstorage) {
        this.newstorage = newstorage;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getTypedate() {
        return typedate;
    }

    public void setTypedate(Long typedate) {
        this.typedate = typedate;
    }

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}