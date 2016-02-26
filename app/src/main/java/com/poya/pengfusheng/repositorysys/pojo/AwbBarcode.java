package com.poya.pengfusheng.repositorysys.pojo;

import java.util.Date;

/**
 * 签收实体
 * Created by pengfusheng on 2016/2/25.
 */
public class AwbBarcode {
    private String BarCode;
    private int AwbId;
    private Date MatchTime;
    private Byte IsMatch;
    private String GetOper;
    private Date GetTime;
    private int BarcodeId;
    private String AWBNO;
    private String DEP;
    private String DES;
    private Date AWBDATE;
    private int PIECE;
    private Date DEPOPTIME;
    private String SIGNER;
    private Date SIGNTIME;
    private String CONSIGNEE;
    private String CONSIGNEEPHONE;
    private float DEPCOLLCHARGE;
    private float DEPSUBCHARGE;

    public Date getAWBDATE() {
        return AWBDATE;
    }

    public int getAwbId() {
        return AwbId;
    }

    public String getAWBNO() {
        return AWBNO;
    }

    public String getBarCode() {
        return BarCode;
    }

    public int getBarcodeId() {
        return BarcodeId;
    }

    public String getCONSIGNEE() {
        return CONSIGNEE;
    }

    public String getCONSIGNEEPHONE() {
        return CONSIGNEEPHONE;
    }

    public String getDEP() {
        return DEP;
    }

    public float getDEPCOLLCHARGE() {
        return DEPCOLLCHARGE;
    }

    public Date getDEPOPTIME() {
        return DEPOPTIME;
    }

    public float getDEPSUBCHARGE() {
        return DEPSUBCHARGE;
    }

    public String getDES() {
        return DES;
    }

    public String getGetOper() {
        return GetOper;
    }

    public Date getGetTime() {
        return GetTime;
    }

    public Byte getIsMatch() {
        return IsMatch;
    }

    public Date getMatchTime() {
        return MatchTime;
    }

    public int getPIECE() {
        return PIECE;
    }

    public String getSIGNER() {
        return SIGNER;
    }

    public Date getSIGNTIME() {
        return SIGNTIME;
    }

    public void setAWBDATE(Date AWBDATE) {
        this.AWBDATE = AWBDATE;
    }

    public void setAwbId(int awbId) {
        AwbId = awbId;
    }

    public void setAWBNO(String AWBNO) {
        this.AWBNO = AWBNO;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public void setBarcodeId(int barcodeId) {
        BarcodeId = barcodeId;
    }

    public void setCONSIGNEE(String CONSIGNEE) {
        this.CONSIGNEE = CONSIGNEE;
    }

    public void setCONSIGNEEPHONE(String CONSIGNEEPHONE) {
        this.CONSIGNEEPHONE = CONSIGNEEPHONE;
    }

    public void setDEP(String DEP) {
        this.DEP = DEP;
    }

    public void setDEPCOLLCHARGE(float DEPCOLLCHARGE) {
        this.DEPCOLLCHARGE = DEPCOLLCHARGE;
    }

    public void setDEPOPTIME(Date DEPOPTIME) {
        this.DEPOPTIME = DEPOPTIME;
    }

    public void setDEPSUBCHARGE(float DEPSUBCHARGE) {
        this.DEPSUBCHARGE = DEPSUBCHARGE;
    }

    public void setDES(String DES) {
        this.DES = DES;
    }

    public void setGetOper(String getOper) {
        GetOper = getOper;
    }

    public void setGetTime(Date getTime) {
        GetTime = getTime;
    }

    public void setIsMatch(Byte isMatch) {
        IsMatch = isMatch;
    }

    public void setMatchTime(Date matchTime) {
        MatchTime = matchTime;
    }

    public void setPIECE(int PIECE) {
        this.PIECE = PIECE;
    }

    public void setSIGNER(String SIGNER) {
        this.SIGNER = SIGNER;
    }

    public void setSIGNTIME(Date SIGNTIME) {
        this.SIGNTIME = SIGNTIME;
    }
}
