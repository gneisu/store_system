package com.poya.pengfusheng.repositorysys.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.poya.pengfusheng.repositorysys.ItemOutputActivity;
import com.poya.pengfusheng.repositorysys.LoginActivity;
import com.poya.pengfusheng.repositorysys.PackageGetActivity;
import com.poya.pengfusheng.repositorysys.DomainActivity;
import com.poya.pengfusheng.repositorysys.PackageInputActivity;
import com.poya.pengfusheng.repositorysys.PackageOutputActivity;
import com.poya.pengfusheng.repositorysys.PackageSignatureActivity;
import com.poya.pengfusheng.repositorysys.R;
import com.poya.pengfusheng.repositorysys.UserActivity;
import com.poya.pengfusheng.repositorysys.base.conf.FileConstants;
import com.poya.pengfusheng.repositorysys.base.utils.FileUtil;
import com.poya.pengfusheng.repositorysys.base.utils.LangUtil;
import com.poya.pengfusheng.repositorysys.pojo.LoginInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by pengfusheng on 2016/1/27.
 */
public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    public static final int REQUEST_CODE_LONIN = 0;
    public static final String LOGIN_USER_NAME = "com.poya.pengfusheng.repositorysys.fragment.loginUserName";
    public static final String LOGIN_FLAG = "com.poya.pengfusheng.repositorysys.fragment.loginFlag";

    private Button mButtonGet;
    private Button mButtonInput;
    private Button mButtonOutput;
    private Button mButtonItemOut;
    private Button mButtonSignature;
    private Button mButtonUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean loginFlag = LoginInfo.getInstance().isLoginFlag();
        if (!loginFlag) {
            Boolean f = false;
            String username = null;
            String ename = null;
            String cname = null;
            String orgName = null;
            String tel = null;
            String phone = null;
            String sto = null;
            String atCity = null;
            String data = FileUtil.readFile(FileConstants.PASSWD_FILE);
            if (LangUtil.isNotEmpty(data)) {
                try {
                    JSONTokener tokener = new JSONTokener(data);
                    JSONObject jsonObject = (JSONObject) tokener.nextValue();
                    f = LangUtil.parseBoolean(jsonObject.get(FileConstants.LOGIN_INFO_FLAG));
                    username = LangUtil.parseString(jsonObject.get(FileConstants.LOGIN_INFO_USERNAEM));
                    ename = LangUtil.parseString(jsonObject.get(FileConstants.LOGIN_INFO_ENAME));
                    cname = LangUtil.parseString(jsonObject.get(FileConstants.LOGIN_INFO_CNAME));
                    orgName = LangUtil.parseString(jsonObject.get(FileConstants.LOGIN_INFO_ORGNAME));
                    tel = LangUtil.parseString(jsonObject.get(FileConstants.LOGIN_INFO_TEL));
                    phone = LangUtil.parseString(jsonObject.get(FileConstants.LOGIN_INFO_MPHONE));
                    sto = LangUtil.parseString(jsonObject.get(FileConstants.LOGIN_INFO_STO));
                    atCity = LangUtil.parseString(jsonObject.get(FileConstants.LOGIN_INFO_ATCITY));
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
            if (f) {// 已登录
                LoginInfo.getInstance().setLoginFlag(true);
                LoginInfo.getInstance().setUserName(username);
                LoginInfo.getInstance().setEname(ename);
                LoginInfo.getInstance().setCname(cname);
                LoginInfo.getInstance().setOrgName(orgName);
                LoginInfo.getInstance().setTel(tel);
                LoginInfo.getInstance().setPhone(phone);
                LoginInfo.getInstance().setSto(sto);
                LoginInfo.getInstance().setAtCity(atCity);
            } else {//跳转到登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_CODE_LONIN);
            }

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LONIN) {
            boolean flag = false;
            String userName = null;
            if (data != null && data.getSerializableExtra(LOGIN_FLAG) != null) {
                flag = (boolean) data.getSerializableExtra(LOGIN_FLAG);
                userName = (String) data.getSerializableExtra(LOGIN_USER_NAME);
            }

            if (flag) {
                Toast.makeText(getActivity(), userName + " " + this.getString(R.string.tips_login_success), Toast.LENGTH_SHORT).show();
            } else {
                getActivity().finish();
            }
        } else {
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_main, container, false);
        View view = inflater.inflate(R.layout.fragment_main_table, container, false);
        // 收货扫描
        mButtonGet = (Button) view.findViewById(R.id.button_main_package_get);
        mButtonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "get", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PackageGetActivity.class);
                startActivity(intent);
            }
        });

        // 货物入库
        mButtonInput = (Button) view.findViewById(R.id.button_main_package_input);
        mButtonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DomainActivity.class);
//                intent.putExtra(DomainFragment.REQUEST_TYPE, DomainFragment.REQUEST_PACKAGE_INPUT);
                Intent intent = new Intent(getActivity(), PackageInputActivity.class);
                startActivity(intent);
            }
        });

        // 货物出库
        mButtonOutput = (Button) view.findViewById(R.id.button_main_package_output);
        mButtonOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DomainActivity.class);
//                intent.putExtra(DomainFragment.REQUEST_TYPE, DomainFragment.REQUEST_PACKAGE_OUTPUT);
                Intent intent = new Intent(getActivity(), PackageOutputActivity.class);
                startActivity(intent);
            }
        });

        // 单号出库
        mButtonItemOut = (Button) view.findViewById(R.id.button_main_item_output);
        mButtonItemOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DomainActivity.class);
//                intent.putExtra(DomainFragment.REQUEST_TYPE, DomainFragment.REQUEST_ITEM_OUTPUT);
                Intent intent = new Intent(getActivity(), ItemOutputActivity.class);
                startActivity(intent);
            }
        });

        // 签收
        mButtonSignature = (Button) view.findViewById(R.id.button_main_package_signature);
        mButtonSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PackageSignatureActivity.class);
                startActivity(intent);
            }
        });

        // 个人信息
        mButtonUser = (Button) view.findViewById(R.id.button_main_user);
        mButtonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
