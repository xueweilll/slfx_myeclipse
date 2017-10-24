package com.benqzl.pojo.patrol;

public class PatrolEnumid {
    private String enumid;

    private String text;

    private String contents;

    public String getEnumid() {
        return enumid;
    }

    public void setEnumid(String enumid) {
        this.enumid = enumid == null ? null : enumid.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }
}