package com.jianlong.youtv.params;

public class Subject {
    private String departname;
    private long departid;

    public Subject(String departname, long departid) {
        this.departname = departname;
        this.departid = departid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public long getDepartid() {
        return departid;
    }

    public void setDepartid(long departid) {
        this.departid = departid;
    }
}
