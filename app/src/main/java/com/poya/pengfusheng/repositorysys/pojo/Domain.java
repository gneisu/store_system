package com.poya.pengfusheng.repositorysys.pojo;

/**
 * 区域实体
 * Created by pengfusheng on 2016/2/2.
 */
public class Domain {
    private int ID;
    private String CODE;
    private String DOMAINNAME;
    private int ISCITY;

    public String getCODE() {
        return CODE;
    }

    public String getDOMAINNAME() {
        return DOMAINNAME;
    }

    public int getID() {
        return ID;
    }

    public int getISCITY() {
        return ISCITY;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public void setDOMAINNAME(String DOMAINNAME) {
        this.DOMAINNAME = DOMAINNAME;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setISCITY(int ISCITY) {
        this.ISCITY = ISCITY;
    }
}
