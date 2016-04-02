package com.poya.pengfusheng.repositorysys.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.poya.pengfusheng.repositorysys.DomainActivity;
import com.poya.pengfusheng.repositorysys.R;
import com.poya.pengfusheng.repositorysys.base.conf.DomainSettingConstants;
import com.poya.pengfusheng.repositorysys.base.conf.DotNetWebservice;
import com.poya.pengfusheng.repositorysys.base.utils.LangUtil;
import com.poya.pengfusheng.repositorysys.base.utils.WebserviceClientUtil;
import com.poya.pengfusheng.repositorysys.pojo.Domain;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class DomainFragment extends Fragment {

    private static final String TAG_DOMAIN = "DomainFragment";

    public static final String REQUEST_TYPE = "com.poya.pengfusheng.repositorysys.fragment.requestType";
    public static final int REQUEST_PACKAGE_INPUT = 1;
    public static final int REQUEST_PACKAGE_OUTPUT = 2;
    public static final int REQUEST_ITEM_OUTPUT = 3;

    public static final String EXTRA_DOMAIN_CODE = "com.poya.pengfusheng.repositorysys.fragment.domaincode";
    public static final String EXTRA_DOMAIN_NAME = "com.poya.pengfusheng.repositorysys.fragment.domainname";
    public static final String EXTRA_IS_CITY = "com.poya.pengfusheng.repositorysys.fragment.iscity";
    public static final String EXTRA_CAR_NO = "com.poya.pengfusheng.repositorysys.fragment.carno";
    public static final String EXTRA_IS_REMEMBER = "com.poya.pengfusheng.repositorysys.fragment.isremember";

    private int mCurRequest = 0;

    private EditText mDomainCodeEditText;
    private EditText mDomainNameEditText;
    private EditText mCarNoEditText;
    private Button mButtonOk;
    private Button mButtonCancel;
    private CheckBox mRememberCheckBox;

    private String mDomainCode;
    private String mDomainName;
    private String mCarNo;
    private int mIsCity;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private void setActionBarTitle(int curRequest) {
        switch (curRequest) {
            case REQUEST_PACKAGE_INPUT:
                getActivity().getActionBar().setTitle(R.string.menu_package_input_setting);
                mPreferences = getActivity().getSharedPreferences(DomainSettingConstants.FILE_NAME_PACKAGE_INPUT, Context.MODE_PRIVATE);
                return;
            case REQUEST_PACKAGE_OUTPUT:
                getActivity().getActionBar().setTitle(R.string.menu_package_output_setting);
                mPreferences = getActivity().getSharedPreferences(DomainSettingConstants.FILE_NAME_PACKAGE_OUTPUT, Context.MODE_PRIVATE);
                return;
            case REQUEST_ITEM_OUTPUT:
                getActivity().getActionBar().setTitle(R.string.menu_item_output_setting);
                mPreferences = getActivity().getSharedPreferences(DomainSettingConstants.FILE_NAME_ITEM_OUTPUT, Context.MODE_PRIVATE);
                return;
            default:
                getActivity().getActionBar().setTitle(R.string.app_name);
                mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                mEditor = mPreferences.edit();
                return;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_domain, container, false);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        mCurRequest = (Integer) getActivity().getIntent().getSerializableExtra(REQUEST_TYPE);
        setActionBarTitle(mCurRequest);

        mDomainCodeEditText = (EditText) view.findViewById(R.id.domain_code);
        mDomainCodeEditText.requestFocus();
        mDomainCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mDomainName = null;
                mDomainNameEditText.setText(mDomainName);
                if (s.length() >= 5) {
                    mDomainCode = mDomainCodeEditText.getText().toString();
                    new QueryDomainTask().execute();
                } else {
                    mDomainName = null;
                    mDomainNameEditText.setText(null);
                    mIsCity = 0;
                }
            }
        });

        mDomainNameEditText = (EditText) view.findViewById(R.id.domain_name);
        mCarNoEditText = (EditText) view.findViewById(R.id.car_no);
        mRememberCheckBox = (CheckBox) view.findViewById(R.id.remember_setting);

//        final boolean isRemember = mPreferences.getBoolean(DomainSettingConstants.REMEMBER_SETTING, false);
//        if (isRemember) {
//            mDomainCode = mPreferences.getString(DomainSettingConstants.REMEMBER_SETTING_DOMAIN_CODE, "");
//            mDomainName = mPreferences.getString(DomainSettingConstants.REMEMBER_SETTING_DOMAIN_NAME, "");
//            mIsCity = mPreferences.getInt(DomainSettingConstants.REMEMBER_SETTING_IS_CITY, 0);
//            mCarNo = mPreferences.getString(DomainSettingConstants.REMEMBER_SETTING_CAR_NO, "");
//            mRememberCheckBox.setChecked(true);
//        } else {
//        }
        mDomainCode = getActivity().getIntent().getStringExtra(EXTRA_DOMAIN_CODE);
        mDomainName = getActivity().getIntent().getStringExtra(EXTRA_DOMAIN_NAME);
        mCarNo = getActivity().getIntent().getStringExtra(EXTRA_CAR_NO);
        mIsCity = getActivity().getIntent().getIntExtra(EXTRA_IS_CITY, 0);
        mDomainNameEditText.setText(mDomainName);
        mCarNoEditText.setText(mCarNo);
        mDomainCodeEditText.setText(mDomainCode);
        final boolean isRemember = getActivity().getIntent().getBooleanExtra(EXTRA_IS_REMEMBER, false);
        if (isRemember) {
            mRememberCheckBox.setChecked(true);
        }

        mButtonOk = (Button) view.findViewById(R.id.button_next);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDomainCode = mDomainCodeEditText.getText().toString();
                mDomainName = mDomainNameEditText.getText().toString();
                mCarNo = mCarNoEditText.getText().toString();
                // 校验
                if (LangUtil.isEmpty(mDomainCode) || LangUtil.isEmpty(mDomainName) || mDomainCode.length() < 5) {
                    Toast.makeText(getActivity(), R.string.error_invalid_domain_code, Toast.LENGTH_SHORT).show();
                    mDomainCodeEditText.requestFocus();
                    return;
                }

                if (LangUtil.isEmpty(mCarNo)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_car_no, Toast.LENGTH_SHORT).show();
                    mCarNoEditText.requestFocus();
                    return;
                }


//                FragmentManager manager = getActivity().getSupportFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                Fragment fragment = newFragment(mDomainCodeEditText.getText().toString(), mDomainName, mCarNo);
//                transaction.replace(R.id.fragmentContainer, fragment);
//                transaction.addToBackStack(null);
//                transaction.commit();


                // 保存配置数据
                mEditor = mPreferences.edit();
                if (mRememberCheckBox.isChecked()) {
                    mEditor.putBoolean(DomainSettingConstants.REMEMBER_SETTING, true);
                    mEditor.putString(DomainSettingConstants.REMEMBER_SETTING_DOMAIN_CODE, mDomainCode);
                    mEditor.putString(DomainSettingConstants.REMEMBER_SETTING_DOMAIN_NAME, mDomainName);
                    mEditor.putString(DomainSettingConstants.REMEMBER_SETTING_CAR_NO, mCarNo);
                    mEditor.putInt(DomainSettingConstants.REMEMBER_SETTING_IS_CITY, mIsCity);
                } else {
                    mEditor.clear();
                }
                mEditor.commit();

                // 传递结果
                Intent intent = new Intent();
                intent.putExtra(EXTRA_DOMAIN_CODE, mDomainCode);
                intent.putExtra(EXTRA_DOMAIN_NAME, mDomainName);
                intent.putExtra(EXTRA_IS_CITY, mIsCity);
                intent.putExtra(EXTRA_CAR_NO, mCarNo);
                intent.putExtra(EXTRA_IS_REMEMBER, mRememberCheckBox.isChecked());
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });

//        mButtonCancel = (Button) view.findViewById(R.id.button_cancel);
//        mButtonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });

        return view;
    }

    private Fragment newFragment(String domainCode, String domainName, String carNo) {
        Fragment fragment = null;
        switch (mCurRequest) {
            case REQUEST_PACKAGE_INPUT:
                fragment = PackageInputFragment.newInstance(domainCode, domainName == null ? mDomainName : domainName, carNo, mIsCity);
                break;
            case REQUEST_PACKAGE_OUTPUT:
                fragment = PackageOutputFragment.newInstance(domainCode, domainName == null ? mDomainName : domainName, carNo, mIsCity);
                break;
            case REQUEST_ITEM_OUTPUT:
                fragment = ItemOutputFragment.newInstance(domainCode, domainName == null ? mDomainName : domainName, carNo, mIsCity);
                break;
            default:
                fragment = null;
        }
        return fragment;
    }


    private class QueryDomainTask extends AsyncTask<Void, Void, Domain> {
        @Override
        protected Domain doInBackground(Void... params) {
            if (mDomainCode == null) return null;
            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_DomainCodeQuery";
            String soapAction = namespace + methodName;
            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("domaincode", mDomainCode);
            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            Domain domain = null;
            try {
                List<Domain> list = WebserviceClientUtil.parseSoapResultSet((SoapObject) result.getProperty("MD_DomainCodeQueryResult"), Domain.class);
//                Log.d(TAG_DOMAIN, "解析结果：" + list);
                domain = list.size() > 0 ? list.get(0) : null;
            } catch (Exception e) {
                Log.e(TAG_DOMAIN, "结果解析出现异常", e);
            }
            return domain;
        }

        @Override
        protected void onPostExecute(Domain d) {
            super.onPostExecute(d);
            if (d != null) {
                mDomainName = d.getDOMAINNAME();
                mIsCity = d.getISCITY();
                mCarNoEditText.requestFocus();
                mButtonOk.setEnabled(true);
            } else {
                mDomainName = null;
                mIsCity = 0;
                mButtonOk.setEnabled(false);
            }
            mDomainNameEditText.setText(mDomainName);
        }
    }

}
