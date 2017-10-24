package com.benqzl.pojo.system;

public class Menu {
    private String menuid;

    private String menuname;

    private String menuicon;

    private String menuurl;

    private String pareid;

    private Long isshow;

    private Long menuorder;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenuicon() {
        return menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon == null ? null : menuicon.trim();
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    public String getPareid() {
        return pareid;
    }

    public void setPareid(String pareid) {
        this.pareid = pareid == null ? null : pareid.trim();
    }

    public Long getIsshow() {
        return isshow;
    }

    public void setIsshow(Long isshow) {
        this.isshow = isshow;
    }

    public Long getMenuorder() {
        return menuorder;
    }

    public void setMenuorder(Long menuorder) {
        this.menuorder = menuorder;
    }
}