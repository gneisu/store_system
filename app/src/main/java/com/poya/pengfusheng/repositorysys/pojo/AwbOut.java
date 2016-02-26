package com.poya.pengfusheng.repositorysys.pojo;

import java.util.Date;

/**
 * 单号出库实体
 * Created by pengfusheng on 2016/2/24.
 */
public class AwbOut {
    private int ID;
    private String ATCITY;
    private String OPPOS;
    private String DESOPPOS;
    private String AWBNO;
    private Date AWBDATE;
    private String DEP;
    private String DES;
    private String TRANS1;
    private String TRANS2;
    private String SHIPTYPE;
    private String CARDNO;
    private String SHIPPER;
    private String SHIPPERPHONE;
    private String CONSIGNEE;
    private String CONSIGNEEPHONE;
    private int PIECE;
    private String CARGONAME;
    private String OPERNO;
    private String DISOPERNO;
    private float DEPCASH;
    private float DEPRECCHARGE;
    private float DEPCOLLCHARGE;
    private float DEPSUBCHARGE;
    private float DEPDISCHARGE;
    private float DESDISCHARGE;
    private float DEPINSCHARGE;
    private float DEPPAYCHARGE;
    private int DEPINPIECE;
    private Date DEPINTIME;
    private int DEPOUTPIECE;
    private Date DEPOUTTIME;
    private int DESINPIECE;
    private Date DESINTIME;
    private int DESOUTPIECE;
    private String DESOUTTIME;
    private int TRANSINPIECE;
    private String TRANSINTIME;
    private int TRANSOUTPIECE;
    private String TRANSOUTTIME;
    private String MEMO;
    private Byte STATUS;
    private Byte DEPSTATUS;
    private Byte DESSTATUS;
    private Byte TRANSSTATUS;
    private Byte DATASOURCE;
    private Byte ISDESSURE;
    private String DESSUREOPER;
    private Date DESSUREOPTIME;
    private String DEPOPER;
    private Date DEPOPTIME;
    private String DESOPER;
    private Date DESOPTIME;
    private String TRANSOPER;
    private Date TRANSOPTIME;
    private Byte ISLOCKED;
    private Byte ISSPEC;
    private Date SPBACKDATE;
    private String SPBACKOPER;
    private Date SPBACKOPTIME;
    private String SIGNER;
    private Date SIGNTIME;
    private String SPSTATUS;
    private String SPRETURNOPER;
    private Date SPRETURNOPTIME;
    private String SPMEMO;
    private Byte ISDEPRECBAL;
    private float DEPRECREAL;
    private String DEPRECBALMEMO;
    private String DEPRECBALOPER;
    private Date DEPRECBALOPTIME;
    private Byte DEPCOLLSTATUS;
    private float DEPCOLLREAL;
    private String DEPCOLLBALOPER;
    private Date DEPCOLLBALOPTIME;
    private Byte DEPSUBSTATUS;
    private float DEPSUBREAL;
    private String DEPSUBBALOPER;
    private Date DEPSUBBALOPTIME;
    private String DEPSUBBALMEMO;
    private Byte DEPSUBASTATUS;
    private float DEPSUBAREAL;
    private String DEPSUBABALOPPOS;
    private String DEPSUBABALOPER;
    private Date DEPSUBABALOPTIME;
    private String DEPSUBABALMEMO;
    private Byte ISBROKEN;
    private Byte ISBROKENOK;
    private String BROKENMEMO;
    private String BROKENOPINFO;
    private float BROKENCOST;
    private String BROKENOPER;
    private Date BROKENOPTIME;
    private Byte ISDESTICARR;
    private String DESTICARROPER;
    private Date DESTICARROPTIME;
    private Byte ISBACK;
    private String BACKNO;
    private String BACKRESON;
    private String BACKMEMO;
    private Date BACKDATE;
    private String BACKOPER;
    private Date BACKOPTIME;
    private Byte DESCOLLSTATUS;
    private float DESCOLLREAL;
    private String DESCOLLPAYMENT;
    private String DESCOLLBALOPER;
    private Date DESCOLLBALOPTIME;
    private String DESCOLLPOS;
    private String DESCOLLBALMEMO;
    private Byte DESSUBSTATUS;
    private float DESSUBREAL;
    private String DESSUBBALOPER;
    private Date DESSUBBALOPTIME;
    private String DESSUBBALMEMO;
    private Byte DESSUBASTATUS;
    private float DESSUBAREAL;
    private String DESSUBABALOPPOS;
    private String DESSUBABALOPER;
    private Date DESSUBABALOPTIME;
    private String DESSUBABALMEMO;
    private String DESTICSIGNER;
    private float DESTICCASH;
    private float DESTICREC;
    private String DESCASHMEMO;
    private Byte ISDIS;
    private Byte ISDEPCASHBAL;
    private String DEPCASHBALOPER;
    private Date DEPCASHBALOPTIME;
    private Byte ISDESCASHBAL;
    private String DESCASHBALOPER;
    private Date DESCASHBALOPTIME;
    private int HAWBOUTPIECE;
    private int HAWBINPIECE;
    private String HAWBABNORMALINFO;
    private Byte DEPBACKSTATUS;
    private float DEPBACKREAL;
    private String DEPBACKOPER;
    private Date DEPBACKOPTIME;
    private String DEPBACKMEMO;
    private Byte ISWEBCHECKED;
    private String WEBCHECKOPER;
    private Date WEBCHECKTIME;
    private Byte ISSHIPPERSURE;
    private String SHIPPEROPER;
    private Date SHIPPEROPTIME;

    public String getATCITY() {
        return ATCITY;
    }

    public Date getAWBDATE() {
        return AWBDATE;
    }

    public String getAWBNO() {
        return AWBNO;
    }

    public Date getBACKDATE() {
        return BACKDATE;
    }

    public String getBACKMEMO() {
        return BACKMEMO;
    }

    public String getBACKNO() {
        return BACKNO;
    }

    public String getBACKOPER() {
        return BACKOPER;
    }

    public Date getBACKOPTIME() {
        return BACKOPTIME;
    }

    public String getBACKRESON() {
        return BACKRESON;
    }

    public float getBROKENCOST() {
        return BROKENCOST;
    }

    public String getBROKENMEMO() {
        return BROKENMEMO;
    }

    public String getBROKENOPER() {
        return BROKENOPER;
    }

    public String getBROKENOPINFO() {
        return BROKENOPINFO;
    }

    public Date getBROKENOPTIME() {
        return BROKENOPTIME;
    }

    public String getCARDNO() {
        return CARDNO;
    }

    public String getCARGONAME() {
        return CARGONAME;
    }

    public String getCONSIGNEE() {
        return CONSIGNEE;
    }

    public String getCONSIGNEEPHONE() {
        return CONSIGNEEPHONE;
    }

    public Byte getDATASOURCE() {
        return DATASOURCE;
    }

    public String getDEP() {
        return DEP;
    }

    public String getDEPBACKMEMO() {
        return DEPBACKMEMO;
    }

    public String getDEPBACKOPER() {
        return DEPBACKOPER;
    }

    public Date getDEPBACKOPTIME() {
        return DEPBACKOPTIME;
    }

    public float getDEPBACKREAL() {
        return DEPBACKREAL;
    }

    public Byte getDEPBACKSTATUS() {
        return DEPBACKSTATUS;
    }

    public float getDEPCASH() {
        return DEPCASH;
    }

    public String getDEPCASHBALOPER() {
        return DEPCASHBALOPER;
    }

    public Date getDEPCASHBALOPTIME() {
        return DEPCASHBALOPTIME;
    }

    public String getDEPCOLLBALOPER() {
        return DEPCOLLBALOPER;
    }

    public Date getDEPCOLLBALOPTIME() {
        return DEPCOLLBALOPTIME;
    }

    public float getDEPCOLLCHARGE() {
        return DEPCOLLCHARGE;
    }

    public float getDEPCOLLREAL() {
        return DEPCOLLREAL;
    }

    public Byte getDEPCOLLSTATUS() {
        return DEPCOLLSTATUS;
    }

    public float getDEPDISCHARGE() {
        return DEPDISCHARGE;
    }

    public int getDEPINPIECE() {
        return DEPINPIECE;
    }

    public float getDEPINSCHARGE() {
        return DEPINSCHARGE;
    }

    public Date getDEPINTIME() {
        return DEPINTIME;
    }

    public String getDEPOPER() {
        return DEPOPER;
    }

    public Date getDEPOPTIME() {
        return DEPOPTIME;
    }

    public int getDEPOUTPIECE() {
        return DEPOUTPIECE;
    }

    public Date getDEPOUTTIME() {
        return DEPOUTTIME;
    }

    public float getDEPPAYCHARGE() {
        return DEPPAYCHARGE;
    }

    public String getDEPRECBALMEMO() {
        return DEPRECBALMEMO;
    }

    public String getDEPRECBALOPER() {
        return DEPRECBALOPER;
    }

    public Date getDEPRECBALOPTIME() {
        return DEPRECBALOPTIME;
    }

    public float getDEPRECCHARGE() {
        return DEPRECCHARGE;
    }

    public float getDEPRECREAL() {
        return DEPRECREAL;
    }

    public Byte getDEPSTATUS() {
        return DEPSTATUS;
    }

    public String getDEPSUBABALMEMO() {
        return DEPSUBABALMEMO;
    }

    public String getDEPSUBABALOPER() {
        return DEPSUBABALOPER;
    }

    public String getDEPSUBABALOPPOS() {
        return DEPSUBABALOPPOS;
    }

    public Date getDEPSUBABALOPTIME() {
        return DEPSUBABALOPTIME;
    }

    public float getDEPSUBAREAL() {
        return DEPSUBAREAL;
    }

    public Byte getDEPSUBASTATUS() {
        return DEPSUBASTATUS;
    }

    public String getDEPSUBBALMEMO() {
        return DEPSUBBALMEMO;
    }

    public String getDEPSUBBALOPER() {
        return DEPSUBBALOPER;
    }

    public Date getDEPSUBBALOPTIME() {
        return DEPSUBBALOPTIME;
    }

    public float getDEPSUBCHARGE() {
        return DEPSUBCHARGE;
    }

    public float getDEPSUBREAL() {
        return DEPSUBREAL;
    }

    public Byte getDEPSUBSTATUS() {
        return DEPSUBSTATUS;
    }

    public String getDES() {
        return DES;
    }

    public String getDESCASHBALOPER() {
        return DESCASHBALOPER;
    }

    public Date getDESCASHBALOPTIME() {
        return DESCASHBALOPTIME;
    }

    public String getDESCASHMEMO() {
        return DESCASHMEMO;
    }

    public String getDESCOLLBALMEMO() {
        return DESCOLLBALMEMO;
    }

    public String getDESCOLLBALOPER() {
        return DESCOLLBALOPER;
    }

    public Date getDESCOLLBALOPTIME() {
        return DESCOLLBALOPTIME;
    }

    public String getDESCOLLPAYMENT() {
        return DESCOLLPAYMENT;
    }

    public String getDESCOLLPOS() {
        return DESCOLLPOS;
    }

    public float getDESCOLLREAL() {
        return DESCOLLREAL;
    }

    public Byte getDESCOLLSTATUS() {
        return DESCOLLSTATUS;
    }

    public float getDESDISCHARGE() {
        return DESDISCHARGE;
    }

    public int getDESINPIECE() {
        return DESINPIECE;
    }

    public Date getDESINTIME() {
        return DESINTIME;
    }

    public String getDESOPER() {
        return DESOPER;
    }

    public String getDESOPPOS() {
        return DESOPPOS;
    }

    public Date getDESOPTIME() {
        return DESOPTIME;
    }

    public int getDESOUTPIECE() {
        return DESOUTPIECE;
    }

    public String getDESOUTTIME() {
        return DESOUTTIME;
    }

    public Byte getDESSTATUS() {
        return DESSTATUS;
    }

    public String getDESSUBABALMEMO() {
        return DESSUBABALMEMO;
    }

    public String getDESSUBABALOPER() {
        return DESSUBABALOPER;
    }

    public String getDESSUBABALOPPOS() {
        return DESSUBABALOPPOS;
    }

    public Date getDESSUBABALOPTIME() {
        return DESSUBABALOPTIME;
    }

    public float getDESSUBAREAL() {
        return DESSUBAREAL;
    }

    public Byte getDESSUBASTATUS() {
        return DESSUBASTATUS;
    }

    public String getDESSUBBALMEMO() {
        return DESSUBBALMEMO;
    }

    public String getDESSUBBALOPER() {
        return DESSUBBALOPER;
    }

    public Date getDESSUBBALOPTIME() {
        return DESSUBBALOPTIME;
    }

    public float getDESSUBREAL() {
        return DESSUBREAL;
    }

    public Byte getDESSUBSTATUS() {
        return DESSUBSTATUS;
    }

    public String getDESSUREOPER() {
        return DESSUREOPER;
    }

    public Date getDESSUREOPTIME() {
        return DESSUREOPTIME;
    }

    public String getDESTICARROPER() {
        return DESTICARROPER;
    }

    public Date getDESTICARROPTIME() {
        return DESTICARROPTIME;
    }

    public float getDESTICCASH() {
        return DESTICCASH;
    }

    public float getDESTICREC() {
        return DESTICREC;
    }

    public String getDESTICSIGNER() {
        return DESTICSIGNER;
    }

    public String getDISOPERNO() {
        return DISOPERNO;
    }

    public String getHAWBABNORMALINFO() {
        return HAWBABNORMALINFO;
    }

    public int getHAWBINPIECE() {
        return HAWBINPIECE;
    }

    public int getHAWBOUTPIECE() {
        return HAWBOUTPIECE;
    }

    public int getID() {
        return ID;
    }

    public Byte getISBACK() {
        return ISBACK;
    }

    public Byte getISBROKEN() {
        return ISBROKEN;
    }

    public Byte getISBROKENOK() {
        return ISBROKENOK;
    }

    public Byte getISDEPCASHBAL() {
        return ISDEPCASHBAL;
    }

    public Byte getISDEPRECBAL() {
        return ISDEPRECBAL;
    }

    public Byte getISDESCASHBAL() {
        return ISDESCASHBAL;
    }

    public Byte getISDESSURE() {
        return ISDESSURE;
    }

    public Byte getISDESTICARR() {
        return ISDESTICARR;
    }

    public Byte getISDIS() {
        return ISDIS;
    }

    public Byte getISLOCKED() {
        return ISLOCKED;
    }

    public Byte getISSHIPPERSURE() {
        return ISSHIPPERSURE;
    }

    public Byte getISSPEC() {
        return ISSPEC;
    }

    public Byte getISWEBCHECKED() {
        return ISWEBCHECKED;
    }

    public String getMEMO() {
        return MEMO;
    }

    public String getOPERNO() {
        return OPERNO;
    }

    public String getOPPOS() {
        return OPPOS;
    }

    public int getPIECE() {
        return PIECE;
    }

    public String getSHIPPER() {
        return SHIPPER;
    }

    public String getSHIPPEROPER() {
        return SHIPPEROPER;
    }

    public Date getSHIPPEROPTIME() {
        return SHIPPEROPTIME;
    }

    public String getSHIPPERPHONE() {
        return SHIPPERPHONE;
    }

    public String getSHIPTYPE() {
        return SHIPTYPE;
    }

    public String getSIGNER() {
        return SIGNER;
    }

    public Date getSIGNTIME() {
        return SIGNTIME;
    }

    public Date getSPBACKDATE() {
        return SPBACKDATE;
    }

    public String getSPBACKOPER() {
        return SPBACKOPER;
    }

    public Date getSPBACKOPTIME() {
        return SPBACKOPTIME;
    }

    public String getSPMEMO() {
        return SPMEMO;
    }

    public String getSPRETURNOPER() {
        return SPRETURNOPER;
    }

    public Date getSPRETURNOPTIME() {
        return SPRETURNOPTIME;
    }

    public String getSPSTATUS() {
        return SPSTATUS;
    }

    public Byte getSTATUS() {
        return STATUS;
    }

    public String getTRANS1() {
        return TRANS1;
    }

    public String getTRANS2() {
        return TRANS2;
    }

    public int getTRANSINPIECE() {
        return TRANSINPIECE;
    }

    public String getTRANSINTIME() {
        return TRANSINTIME;
    }

    public String getTRANSOPER() {
        return TRANSOPER;
    }

    public Date getTRANSOPTIME() {
        return TRANSOPTIME;
    }

    public int getTRANSOUTPIECE() {
        return TRANSOUTPIECE;
    }

    public String getTRANSOUTTIME() {
        return TRANSOUTTIME;
    }

    public Byte getTRANSSTATUS() {
        return TRANSSTATUS;
    }

    public String getWEBCHECKOPER() {
        return WEBCHECKOPER;
    }

    public Date getWEBCHECKTIME() {
        return WEBCHECKTIME;
    }

    public void setATCITY(String ATCITY) {
        this.ATCITY = ATCITY;
    }

    public void setAWBDATE(Date AWBDATE) {
        this.AWBDATE = AWBDATE;
    }

    public void setAWBNO(String AWBNO) {
        this.AWBNO = AWBNO;
    }

    public void setBACKDATE(Date BACKDATE) {
        this.BACKDATE = BACKDATE;
    }

    public void setBACKMEMO(String BACKMEMO) {
        this.BACKMEMO = BACKMEMO;
    }

    public void setBACKNO(String BACKNO) {
        this.BACKNO = BACKNO;
    }

    public void setBACKOPER(String BACKOPER) {
        this.BACKOPER = BACKOPER;
    }

    public void setBACKOPTIME(Date BACKOPTIME) {
        this.BACKOPTIME = BACKOPTIME;
    }

    public void setBACKRESON(String BACKRESON) {
        this.BACKRESON = BACKRESON;
    }

    public void setBROKENCOST(float BROKENCOST) {
        this.BROKENCOST = BROKENCOST;
    }

    public void setBROKENMEMO(String BROKENMEMO) {
        this.BROKENMEMO = BROKENMEMO;
    }

    public void setBROKENOPER(String BROKENOPER) {
        this.BROKENOPER = BROKENOPER;
    }

    public void setBROKENOPINFO(String BROKENOPINFO) {
        this.BROKENOPINFO = BROKENOPINFO;
    }

    public void setBROKENOPTIME(Date BROKENOPTIME) {
        this.BROKENOPTIME = BROKENOPTIME;
    }

    public void setCARDNO(String CARDNO) {
        this.CARDNO = CARDNO;
    }

    public void setCARGONAME(String CARGONAME) {
        this.CARGONAME = CARGONAME;
    }

    public void setCONSIGNEE(String CONSIGNEE) {
        this.CONSIGNEE = CONSIGNEE;
    }

    public void setCONSIGNEEPHONE(String CONSIGNEEPHONE) {
        this.CONSIGNEEPHONE = CONSIGNEEPHONE;
    }

    public void setDATASOURCE(Byte DATASOURCE) {
        this.DATASOURCE = DATASOURCE;
    }

    public void setDEP(String DEP) {
        this.DEP = DEP;
    }

    public void setDEPBACKMEMO(String DEPBACKMEMO) {
        this.DEPBACKMEMO = DEPBACKMEMO;
    }

    public void setDEPBACKOPER(String DEPBACKOPER) {
        this.DEPBACKOPER = DEPBACKOPER;
    }

    public void setDEPBACKOPTIME(Date DEPBACKOPTIME) {
        this.DEPBACKOPTIME = DEPBACKOPTIME;
    }

    public void setDEPBACKREAL(float DEPBACKREAL) {
        this.DEPBACKREAL = DEPBACKREAL;
    }

    public void setDEPBACKSTATUS(Byte DEPBACKSTATUS) {
        this.DEPBACKSTATUS = DEPBACKSTATUS;
    }

    public void setDEPCASH(float DEPCASH) {
        this.DEPCASH = DEPCASH;
    }

    public void setDEPCASHBALOPER(String DEPCASHBALOPER) {
        this.DEPCASHBALOPER = DEPCASHBALOPER;
    }

    public void setDEPCASHBALOPTIME(Date DEPCASHBALOPTIME) {
        this.DEPCASHBALOPTIME = DEPCASHBALOPTIME;
    }

    public void setDEPCOLLBALOPER(String DEPCOLLBALOPER) {
        this.DEPCOLLBALOPER = DEPCOLLBALOPER;
    }

    public void setDEPCOLLBALOPTIME(Date DEPCOLLBALOPTIME) {
        this.DEPCOLLBALOPTIME = DEPCOLLBALOPTIME;
    }

    public void setDEPCOLLCHARGE(float DEPCOLLCHARGE) {
        this.DEPCOLLCHARGE = DEPCOLLCHARGE;
    }

    public void setDEPCOLLREAL(float DEPCOLLREAL) {
        this.DEPCOLLREAL = DEPCOLLREAL;
    }

    public void setDEPCOLLSTATUS(Byte DEPCOLLSTATUS) {
        this.DEPCOLLSTATUS = DEPCOLLSTATUS;
    }

    public void setDEPDISCHARGE(float DEPDISCHARGE) {
        this.DEPDISCHARGE = DEPDISCHARGE;
    }

    public void setDEPINPIECE(int DEPINPIECE) {
        this.DEPINPIECE = DEPINPIECE;
    }

    public void setDEPINSCHARGE(float DEPINSCHARGE) {
        this.DEPINSCHARGE = DEPINSCHARGE;
    }

    public void setDEPINTIME(Date DEPINTIME) {
        this.DEPINTIME = DEPINTIME;
    }

    public void setDEPOPER(String DEPOPER) {
        this.DEPOPER = DEPOPER;
    }

    public void setDEPOPTIME(Date DEPOPTIME) {
        this.DEPOPTIME = DEPOPTIME;
    }

    public void setDEPOUTPIECE(int DEPOUTPIECE) {
        this.DEPOUTPIECE = DEPOUTPIECE;
    }

    public void setDEPOUTTIME(Date DEPOUTTIME) {
        this.DEPOUTTIME = DEPOUTTIME;
    }

    public void setDEPPAYCHARGE(float DEPPAYCHARGE) {
        this.DEPPAYCHARGE = DEPPAYCHARGE;
    }

    public void setDEPRECBALMEMO(String DEPRECBALMEMO) {
        this.DEPRECBALMEMO = DEPRECBALMEMO;
    }

    public void setDEPRECBALOPER(String DEPRECBALOPER) {
        this.DEPRECBALOPER = DEPRECBALOPER;
    }

    public void setDEPRECBALOPTIME(Date DEPRECBALOPTIME) {
        this.DEPRECBALOPTIME = DEPRECBALOPTIME;
    }

    public void setDEPRECCHARGE(float DEPRECCHARGE) {
        this.DEPRECCHARGE = DEPRECCHARGE;
    }

    public void setDEPRECREAL(float DEPRECREAL) {
        this.DEPRECREAL = DEPRECREAL;
    }

    public void setDEPSTATUS(Byte DEPSTATUS) {
        this.DEPSTATUS = DEPSTATUS;
    }

    public void setDEPSUBABALMEMO(String DEPSUBABALMEMO) {
        this.DEPSUBABALMEMO = DEPSUBABALMEMO;
    }

    public void setDEPSUBABALOPER(String DEPSUBABALOPER) {
        this.DEPSUBABALOPER = DEPSUBABALOPER;
    }

    public void setDEPSUBABALOPPOS(String DEPSUBABALOPPOS) {
        this.DEPSUBABALOPPOS = DEPSUBABALOPPOS;
    }

    public void setDEPSUBABALOPTIME(Date DEPSUBABALOPTIME) {
        this.DEPSUBABALOPTIME = DEPSUBABALOPTIME;
    }

    public void setDEPSUBAREAL(float DEPSUBAREAL) {
        this.DEPSUBAREAL = DEPSUBAREAL;
    }

    public void setDEPSUBASTATUS(Byte DEPSUBASTATUS) {
        this.DEPSUBASTATUS = DEPSUBASTATUS;
    }

    public void setDEPSUBBALMEMO(String DEPSUBBALMEMO) {
        this.DEPSUBBALMEMO = DEPSUBBALMEMO;
    }

    public void setDEPSUBBALOPER(String DEPSUBBALOPER) {
        this.DEPSUBBALOPER = DEPSUBBALOPER;
    }

    public void setDEPSUBBALOPTIME(Date DEPSUBBALOPTIME) {
        this.DEPSUBBALOPTIME = DEPSUBBALOPTIME;
    }

    public void setDEPSUBCHARGE(float DEPSUBCHARGE) {
        this.DEPSUBCHARGE = DEPSUBCHARGE;
    }

    public void setDEPSUBREAL(float DEPSUBREAL) {
        this.DEPSUBREAL = DEPSUBREAL;
    }

    public void setDEPSUBSTATUS(Byte DEPSUBSTATUS) {
        this.DEPSUBSTATUS = DEPSUBSTATUS;
    }

    public void setDES(String DES) {
        this.DES = DES;
    }

    public void setDESCASHBALOPER(String DESCASHBALOPER) {
        this.DESCASHBALOPER = DESCASHBALOPER;
    }

    public void setDESCASHBALOPTIME(Date DESCASHBALOPTIME) {
        this.DESCASHBALOPTIME = DESCASHBALOPTIME;
    }

    public void setDESCASHMEMO(String DESCASHMEMO) {
        this.DESCASHMEMO = DESCASHMEMO;
    }

    public void setDESCOLLBALMEMO(String DESCOLLBALMEMO) {
        this.DESCOLLBALMEMO = DESCOLLBALMEMO;
    }

    public void setDESCOLLBALOPER(String DESCOLLBALOPER) {
        this.DESCOLLBALOPER = DESCOLLBALOPER;
    }

    public void setDESCOLLBALOPTIME(Date DESCOLLBALOPTIME) {
        this.DESCOLLBALOPTIME = DESCOLLBALOPTIME;
    }

    public void setDESCOLLPAYMENT(String DESCOLLPAYMENT) {
        this.DESCOLLPAYMENT = DESCOLLPAYMENT;
    }

    public void setDESCOLLPOS(String DESCOLLPOS) {
        this.DESCOLLPOS = DESCOLLPOS;
    }

    public void setDESCOLLREAL(float DESCOLLREAL) {
        this.DESCOLLREAL = DESCOLLREAL;
    }

    public void setDESCOLLSTATUS(Byte DESCOLLSTATUS) {
        this.DESCOLLSTATUS = DESCOLLSTATUS;
    }

    public void setDESDISCHARGE(float DESDISCHARGE) {
        this.DESDISCHARGE = DESDISCHARGE;
    }

    public void setDESINPIECE(int DESINPIECE) {
        this.DESINPIECE = DESINPIECE;
    }

    public void setDESINTIME(Date DESINTIME) {
        this.DESINTIME = DESINTIME;
    }

    public void setDESOPER(String DESOPER) {
        this.DESOPER = DESOPER;
    }

    public void setDESOPPOS(String DESOPPOS) {
        this.DESOPPOS = DESOPPOS;
    }

    public void setDESOPTIME(Date DESOPTIME) {
        this.DESOPTIME = DESOPTIME;
    }

    public void setDESOUTPIECE(int DESOUTPIECE) {
        this.DESOUTPIECE = DESOUTPIECE;
    }

    public void setDESOUTTIME(String DESOUTTIME) {
        this.DESOUTTIME = DESOUTTIME;
    }

    public void setDESSTATUS(Byte DESSTATUS) {
        this.DESSTATUS = DESSTATUS;
    }

    public void setDESSUBABALMEMO(String DESSUBABALMEMO) {
        this.DESSUBABALMEMO = DESSUBABALMEMO;
    }

    public void setDESSUBABALOPER(String DESSUBABALOPER) {
        this.DESSUBABALOPER = DESSUBABALOPER;
    }

    public void setDESSUBABALOPPOS(String DESSUBABALOPPOS) {
        this.DESSUBABALOPPOS = DESSUBABALOPPOS;
    }

    public void setDESSUBABALOPTIME(Date DESSUBABALOPTIME) {
        this.DESSUBABALOPTIME = DESSUBABALOPTIME;
    }

    public void setDESSUBAREAL(float DESSUBAREAL) {
        this.DESSUBAREAL = DESSUBAREAL;
    }

    public void setDESSUBASTATUS(Byte DESSUBASTATUS) {
        this.DESSUBASTATUS = DESSUBASTATUS;
    }

    public void setDESSUBBALMEMO(String DESSUBBALMEMO) {
        this.DESSUBBALMEMO = DESSUBBALMEMO;
    }

    public void setDESSUBBALOPER(String DESSUBBALOPER) {
        this.DESSUBBALOPER = DESSUBBALOPER;
    }

    public void setDESSUBBALOPTIME(Date DESSUBBALOPTIME) {
        this.DESSUBBALOPTIME = DESSUBBALOPTIME;
    }

    public void setDESSUBREAL(float DESSUBREAL) {
        this.DESSUBREAL = DESSUBREAL;
    }

    public void setDESSUBSTATUS(Byte DESSUBSTATUS) {
        this.DESSUBSTATUS = DESSUBSTATUS;
    }

    public void setDESSUREOPER(String DESSUREOPER) {
        this.DESSUREOPER = DESSUREOPER;
    }

    public void setDESSUREOPTIME(Date DESSUREOPTIME) {
        this.DESSUREOPTIME = DESSUREOPTIME;
    }

    public void setDESTICARROPER(String DESTICARROPER) {
        this.DESTICARROPER = DESTICARROPER;
    }

    public void setDESTICARROPTIME(Date DESTICARROPTIME) {
        this.DESTICARROPTIME = DESTICARROPTIME;
    }

    public void setDESTICCASH(float DESTICCASH) {
        this.DESTICCASH = DESTICCASH;
    }

    public void setDESTICREC(float DESTICREC) {
        this.DESTICREC = DESTICREC;
    }

    public void setDESTICSIGNER(String DESTICSIGNER) {
        this.DESTICSIGNER = DESTICSIGNER;
    }

    public void setDISOPERNO(String DISOPERNO) {
        this.DISOPERNO = DISOPERNO;
    }

    public void setHAWBABNORMALINFO(String HAWBABNORMALINFO) {
        this.HAWBABNORMALINFO = HAWBABNORMALINFO;
    }

    public void setHAWBINPIECE(int HAWBINPIECE) {
        this.HAWBINPIECE = HAWBINPIECE;
    }

    public void setHAWBOUTPIECE(int HAWBOUTPIECE) {
        this.HAWBOUTPIECE = HAWBOUTPIECE;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setISBACK(Byte ISBACK) {
        this.ISBACK = ISBACK;
    }

    public void setISBROKEN(Byte ISBROKEN) {
        this.ISBROKEN = ISBROKEN;
    }

    public void setISBROKENOK(Byte ISBROKENOK) {
        this.ISBROKENOK = ISBROKENOK;
    }

    public void setISDEPCASHBAL(Byte ISDEPCASHBAL) {
        this.ISDEPCASHBAL = ISDEPCASHBAL;
    }

    public void setISDEPRECBAL(Byte ISDEPRECBAL) {
        this.ISDEPRECBAL = ISDEPRECBAL;
    }

    public void setISDESCASHBAL(Byte ISDESCASHBAL) {
        this.ISDESCASHBAL = ISDESCASHBAL;
    }

    public void setISDESSURE(Byte ISDESSURE) {
        this.ISDESSURE = ISDESSURE;
    }

    public void setISDESTICARR(Byte ISDESTICARR) {
        this.ISDESTICARR = ISDESTICARR;
    }

    public void setISDIS(Byte ISDIS) {
        this.ISDIS = ISDIS;
    }

    public void setISLOCKED(Byte ISLOCKED) {
        this.ISLOCKED = ISLOCKED;
    }

    public void setISSHIPPERSURE(Byte ISSHIPPERSURE) {
        this.ISSHIPPERSURE = ISSHIPPERSURE;
    }

    public void setISSPEC(Byte ISSPEC) {
        this.ISSPEC = ISSPEC;
    }

    public void setISWEBCHECKED(Byte ISWEBCHECKED) {
        this.ISWEBCHECKED = ISWEBCHECKED;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }

    public void setOPERNO(String OPERNO) {
        this.OPERNO = OPERNO;
    }

    public void setOPPOS(String OPPOS) {
        this.OPPOS = OPPOS;
    }

    public void setPIECE(int PIECE) {
        this.PIECE = PIECE;
    }

    public void setSHIPPER(String SHIPPER) {
        this.SHIPPER = SHIPPER;
    }

    public void setSHIPPEROPER(String SHIPPEROPER) {
        this.SHIPPEROPER = SHIPPEROPER;
    }

    public void setSHIPPEROPTIME(Date SHIPPEROPTIME) {
        this.SHIPPEROPTIME = SHIPPEROPTIME;
    }

    public void setSHIPPERPHONE(String SHIPPERPHONE) {
        this.SHIPPERPHONE = SHIPPERPHONE;
    }

    public void setSHIPTYPE(String SHIPTYPE) {
        this.SHIPTYPE = SHIPTYPE;
    }

    public void setSIGNER(String SIGNER) {
        this.SIGNER = SIGNER;
    }

    public void setSIGNTIME(Date SIGNTIME) {
        this.SIGNTIME = SIGNTIME;
    }

    public void setSPBACKDATE(Date SPBACKDATE) {
        this.SPBACKDATE = SPBACKDATE;
    }

    public void setSPBACKOPER(String SPBACKOPER) {
        this.SPBACKOPER = SPBACKOPER;
    }

    public void setSPBACKOPTIME(Date SPBACKOPTIME) {
        this.SPBACKOPTIME = SPBACKOPTIME;
    }

    public void setSPMEMO(String SPMEMO) {
        this.SPMEMO = SPMEMO;
    }

    public void setSPRETURNOPER(String SPRETURNOPER) {
        this.SPRETURNOPER = SPRETURNOPER;
    }

    public void setSPRETURNOPTIME(Date SPRETURNOPTIME) {
        this.SPRETURNOPTIME = SPRETURNOPTIME;
    }

    public void setSPSTATUS(String SPSTATUS) {
        this.SPSTATUS = SPSTATUS;
    }

    public void setSTATUS(Byte STATUS) {
        this.STATUS = STATUS;
    }

    public void setTRANS1(String TRANS1) {
        this.TRANS1 = TRANS1;
    }

    public void setTRANS2(String TRANS2) {
        this.TRANS2 = TRANS2;
    }

    public void setTRANSINPIECE(int TRANSINPIECE) {
        this.TRANSINPIECE = TRANSINPIECE;
    }

    public void setTRANSINTIME(String TRANSINTIME) {
        this.TRANSINTIME = TRANSINTIME;
    }

    public void setTRANSOPER(String TRANSOPER) {
        this.TRANSOPER = TRANSOPER;
    }

    public void setTRANSOPTIME(Date TRANSOPTIME) {
        this.TRANSOPTIME = TRANSOPTIME;
    }

    public void setTRANSOUTPIECE(int TRANSOUTPIECE) {
        this.TRANSOUTPIECE = TRANSOUTPIECE;
    }

    public void setTRANSOUTTIME(String TRANSOUTTIME) {
        this.TRANSOUTTIME = TRANSOUTTIME;
    }

    public void setTRANSSTATUS(Byte TRANSSTATUS) {
        this.TRANSSTATUS = TRANSSTATUS;
    }

    public void setWEBCHECKOPER(String WEBCHECKOPER) {
        this.WEBCHECKOPER = WEBCHECKOPER;
    }

    public void setWEBCHECKTIME(Date WEBCHECKTIME) {
        this.WEBCHECKTIME = WEBCHECKTIME;
    }
}
