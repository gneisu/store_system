package com.poya.pengfusheng.repositorysys.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.poya.pengfusheng.repositorysys.MainActivity;
import com.poya.pengfusheng.repositorysys.R;
import com.poya.pengfusheng.repositorysys.base.ActivityPool;
import com.poya.pengfusheng.repositorysys.base.conf.DomainSettingConstants;
import com.poya.pengfusheng.repositorysys.base.conf.FileConstants;
import com.poya.pengfusheng.repositorysys.base.utils.FileUtil;
import com.poya.pengfusheng.repositorysys.pojo.LoginInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class UserFragment extends Fragment {
    private static final String TAG = "UserFragment";

    private TextView mUserNameTextView;
    private TextView mEnameTextView;
    private TextView mCnameTextView;
    private TextView mOrgNameTextView;
    private TextView mTelTextView;
    private TextView mPhoneTextView;
    private TextView mLogoutTextView;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        mUserNameTextView = (TextView) view.findViewById(R.id.self_username);
        mEnameTextView = (TextView) view.findViewById(R.id.self_ename);
        mCnameTextView = (TextView) view.findViewById(R.id.self_cname);
        mOrgNameTextView = (TextView) view.findViewById(R.id.self_orgname);
        mTelTextView = (TextView) view.findViewById(R.id.self_tel);
        mPhoneTextView = (TextView) view.findViewById(R.id.self_mphone);

        mUserNameTextView.setText(LoginInfo.getInstance().getUserName());
        mEnameTextView.setText(LoginInfo.getInstance().getEname());
        mCnameTextView.setText(LoginInfo.getInstance().getCname());
        mOrgNameTextView.setText(LoginInfo.getInstance().getOrgName());
        mTelTextView.setText(LoginInfo.getInstance().getTel());
        mPhoneTextView.setText(LoginInfo.getInstance().getPhone());

        mLogoutTextView = (TextView) view.findViewById(R.id.self_logout);
        mLogoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "logout", Toast.LENGTH_SHORT).show();
                LoginInfo.getInstance().setLoginFlag(false);
                FileUtil.saveFile(FileConstants.PASSWD_FILE, LoginInfo.getInstance().getLoginInfoJson().toString());
                mPreferences = getActivity().getSharedPreferences(DomainSettingConstants.FILE_NAME_PACKAGE_INPUT, Context.MODE_PRIVATE);
                mEditor = mPreferences.edit();
                mEditor.clear();
                mEditor.commit();
                mPreferences = getActivity().getSharedPreferences(DomainSettingConstants.FILE_NAME_PACKAGE_OUTPUT, Context.MODE_PRIVATE);
                mEditor = mPreferences.edit();
                mEditor.clear();
                mEditor.commit();
                mPreferences = getActivity().getSharedPreferences(DomainSettingConstants.FILE_NAME_ITEM_OUTPUT, Context.MODE_PRIVATE);
                mEditor = mPreferences.edit();
                mEditor.clear();
                mEditor.commit();
                ActivityPool.finishAll();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
