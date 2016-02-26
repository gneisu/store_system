package com.poya.pengfusheng.repositorysys.pojo;

import java.util.Date;

/**
 * 货物出库实体
 * Created by pengfusheng on 2016/2/24.
 */
public class BarCodeOut {
    private int ID;
    private int AWBID;
    private int BARCODE;
    private int PIECE;
    private String ATCITY;
    private String OPPOS;
    private String AWBNO;
    private String DEP;
    private String DES;
    private String DEPOPER;
    private Date AWBDATE;
    private Date DEPOPTIME;

    public String getATCITY() {
        return ATCITY;
    }

    public Date getAWBDATE() {
        return AWBDATE;
    }

    public int getAWBID() {
        return AWBID;
    }

    public String getAWBNO() {
        return AWBNO;
    }

    public int getBARCODE() {
        return BARCODE;
    }

    public String getDEP() {
        return DEP;
    }

    public String getDEPOPER() {
        return DEPOPER;
    }

    public Date getDEPOPTIME() {
        return DEPOPTIME;
    }

    public String getDES() {
        return DES;
    }

    public int getID() {
        return ID;
    }

    public String getOPPOS() {
        return OPPOS;
    }

    public int getPIECE() {
        return PIECE;
    }

    public void setATCITY(String ATCITY) {
        this.ATCITY = ATCITY;
    }

    public void setAWBDATE(Date AWBDATE) {
        this.AWBDATE = AWBDATE;
    }

    public void setAWBID(int AWBID) {
        this.AWBID = AWBID;
    }

    public void setAWBNO(String AWBNO) {
        this.AWBNO = AWBNO;
    }

    public void setBARCODE(int BARCODE) {
        this.BARCODE = BARCODE;
    }

    public void setDEP(String DEP) {
        this.DEP = DEP;
    }

    public void setDEPOPER(String DEPOPER) {
        this.DEPOPER = DEPOPER;
    }

    public void setDEPOPTIME(Date DEPOPTIME) {
        this.DEPOPTIME = DEPOPTIME;
    }

    public void setDES(String DES) {
        this.DES = DES;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setOPPOS(String OPPOS) {
        this.OPPOS = OPPOS;
    }

    public void setPIECE(int PIECE) {
        this.PIECE = PIECE;
    }
}