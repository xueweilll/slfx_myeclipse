package com.benqzl.pojo.material;

import java.math.BigDecimal;
import java.util.Date;

import com.benqzl.pojo.system.Material;

public class StockItems {
    private String id;

    private Date createtime;

    private Date edittime;

    private Long state;

    private String materialid;
    
    private Material material;

    private String stockid;

    private BigDecimal count;

    private Long surpluscount;

    private String source;

    private String memo;
    
    public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

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

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid == null ? null : materialid.trim();
    }

    public String getStockid() {
        return stockid;
    }

    public void setStockid(String stockid) {
        this.stockid = stockid == null ? null : stockid.trim();
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Long getSurpluscount() {
        return surpluscount;
    }

    public void setSurpluscount(Long surpluscount) {
        this.surpluscount = surpluscount;
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
}