package com.poya.pengfusheng.repositorysys.pojo;

/**
 * 用户实体
 * Created by pengfusheng on 2016/1/29.
 */
public class User {
    private Integer ID;
    private String ENAME;
    private String CNAME;
    private String USERNAME;
    private String PWD;
    private String ORGCODE;
    private String ORGNAME;
    private String SEX;
    private String TEL;
    private String MPHONE;
    private String ATCITY;
    private String STO;

    public User(String CNAME, String ENAME, Integer ID, String MPHONE, String ORGCODE, String ORGNAME, String PWD, String SEX, String TEL, String USERNAME) {
        this.CNAME = CNAME;
        this.ENAME = ENAME;
        this.ID = ID;
        this.MPHONE = MPHONE;
        this.ORGCODE = ORGCODE;
        this.ORGNAME = ORGNAME;
        this.PWD = PWD;
        this.SEX = SEX;
        this.TEL = TEL;
        this.USERNAME = USERNAME;
    }

    public User() {
    }

    public String getCNAME() {
        return CNAME;
    }

    public String getENAME() {
        return ENAME;
    }

    public Integer getID() {
        return ID;
    }

    public String getMPHONE() {
        return MPHONE;
    }

    public String getORGCODE() {
        return ORGCODE;
    }

    public String getORGNAME() {
        return ORGNAME;
    }

    public String getPWD() {
        return PWD;
    }

    public String getSEX() {
        return SEX;
    }

    public String getTEL() {
        return TEL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }

    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setMPHONE(String MPHONE) {
        this.MPHONE = MPHONE;
    }

    public void setORGCODE(String ORGCODE) {
        this.ORGCODE = ORGCODE;
    }

    public void setORGNAME(String ORGNAME) {
        this.ORGNAME = ORGNAME;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getATCITY() {
        return ATCITY;
    }

    public String getSTO() {
        return STO;
    }

    public void setATCITY(String ATCITY) {
        this.ATCITY = ATCITY;
    }

    public void setSTO(String STO) {
        this.STO = STO;
    }
}
