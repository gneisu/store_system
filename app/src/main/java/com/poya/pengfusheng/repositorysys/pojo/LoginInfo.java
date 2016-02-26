package com.poya.pengfusheng.repositorysys.pojo;

import com.poya.pengfusheng.repositorysys.base.conf.FileConstants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 登录信息实体
 * Created by pengfusheng on 2016/2/20.
 */
public class LoginInfo {

    private String mUserName;
    private String mEname;
    private String mCname;
    private String mOrgName;
    private String mTel;
    private String mPhone;
    private String mAtCity;
    private String mSto;


    private boolean mLoginFlag;

    private static LoginInfo instance = null;
    private static JSONObject loginInfoJson = new JSONObject();

    private LoginInfo() {
    }

    public static LoginInfo getInstance() {
        if (instance == null) {
            instance = new LoginInfo();
        }
        return instance;
    }

    public JSONObject getLoginInfoJson() {
        return loginInfoJson;
    }

    public String getCname() {
        return mCname;
    }

    public String getEname() {
        return mEname;
    }

    public boolean isLoginFlag() {
        return mLoginFlag;
    }

    public String getOrgName() {
        return mOrgName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getTel() {
        return mTel;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setCname(String cname) {
        mCname = cname;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_CNAME, cname == null ? "" : cname);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setEname(String ename) {
        mEname = ename;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_ENAME, ename == null ? "" : ename);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setLoginFlag(boolean loginFlag) {
        mLoginFlag = loginFlag;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_FLAG, loginFlag);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setOrgName(String orgName) {
        mOrgName = orgName;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_ORGNAME, orgName == null ? "" : orgName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setPhone(String phone) {
        mPhone = phone;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_MPHONE, phone == null ? "" : phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setTel(String tel) {
        mTel = tel;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_TEL, tel == null ? "" : tel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setUserName(String userName) {
        mUserName = userName;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_USERNAEM, userName == null ? "" : userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getAtCity() {
        return mAtCity;
    }

    public String getSto() {
        return mSto;
    }

    public void setSto(String sto) {
        mSto = sto;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_STO, sto == null ? "" : sto);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setAtCity(String atCity) {
        mAtCity = atCity;
        try {
            loginInfoJson.put(FileConstants.LOGIN_INFO_ATCITY, atCity == null ? "" : atCity);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
