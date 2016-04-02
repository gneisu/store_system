package com.poya.pengfusheng.repositorysys.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poya.pengfusheng.repositorysys.DomainActivity;
import com.poya.pengfusheng.repositorysys.R;
import com.poya.pengfusheng.repositorysys.base.conf.DomainSettingConstants;
import com.poya.pengfusheng.repositorysys.base.conf.DotNetWebservice;
import com.poya.pengfusheng.repositorysys.base.utils.LangUtil;
import com.poya.pengfusheng.repositorysys.base.utils.SoundAlert;
import com.poya.pengfusheng.repositorysys.base.utils.WebserviceClientUtil;
import com.poya.pengfusheng.repositorysys.pojo.AwbOut;
import com.poya.pengfusheng.repositorysys.pojo.BarCodeOut;
import com.poya.pengfusheng.repositorysys.pojo.LoginInfo;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class ItemOutputFragment extends Fragment {
    private static final String TAG = "ItemOutputFragment";
    public static final String EXTRA_DOMAIN_CODE = "com.poya.pengfusheng.repositorysys.fragment.domaincode";
    public static final String EXTRA_DOMAIN_NAME = "com.poya.pengfusheng.repositorysys.fragment.domainname";
    public static final String EXTRA_IS_CITY = "com.poya.pengfusheng.repositorysys.fragment.isCity";
    public static final String EXTRA_CAR_NO = "com.poya.pengfusheng.repositorysys.fragment.carno";

    public static final int REQUEST_ITEM_OUTPUT = 3;

    private String mDomainCode;
    private String mDomainName;
    private String mCarNo;
    private int mIsCity;

    private String mAwbNo;
    private String mAwbId;
    private boolean mIsRemember;

    private int piece;

    private EditText mAwbNoEditText;
    private EditText mDestEditText;
    private EditText mAwbSumEditText;

    private Button mButtonOk;
    private Button mButtonCancel;

    private SharedPreferences mPreferences;


    public static ItemOutputFragment newInstance(String domainCode, String domainName, String carNo, int isCity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DOMAIN_CODE, domainCode);
        bundle.putSerializable(EXTRA_DOMAIN_NAME, domainName);
        bundle.putSerializable(EXTRA_IS_CITY, isCity);
        bundle.putSerializable(EXTRA_CAR_NO, carNo);
        ItemOutputFragment fragment = new ItemOutputFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mPreferences = getActivity().getSharedPreferences(DomainSettingConstants.FILE_NAME_ITEM_OUTPUT, Context.MODE_PRIVATE);
        mIsRemember = mPreferences.getBoolean(DomainSettingConstants.REMEMBER_SETTING, false);
        if (mIsRemember) {
            mDomainCode = mPreferences.getString(DomainSettingConstants.REMEMBER_SETTING_DOMAIN_CODE, "");
            mDomainName = mPreferences.getString(DomainSettingConstants.REMEMBER_SETTING_DOMAIN_NAME, "");
            mIsCity = mPreferences.getInt(DomainSettingConstants.REMEMBER_SETTING_IS_CITY, 0);
            mCarNo = mPreferences.getString(DomainSettingConstants.REMEMBER_SETTING_CAR_NO, "");
        } else {
            startDomainSettingActivity();
        }
    }

    private void startDomainSettingActivity() {
        Intent intent = new Intent(getActivity(), DomainActivity.class);
        intent.putExtra(DomainFragment.REQUEST_TYPE, DomainFragment.REQUEST_ITEM_OUTPUT);
        intent.putExtra(DomainFragment.EXTRA_DOMAIN_CODE, mDomainCode);
        intent.putExtra(DomainFragment.EXTRA_DOMAIN_NAME, mDomainName);
        intent.putExtra(DomainFragment.EXTRA_CAR_NO, mCarNo);
        intent.putExtra(DomainFragment.EXTRA_IS_CITY, mIsCity);
        intent.putExtra(DomainFragment.EXTRA_IS_REMEMBER, mIsRemember);
        startActivityForResult(intent, REQUEST_ITEM_OUTPUT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ITEM_OUTPUT && data != null && data.getSerializableExtra(DomainFragment.EXTRA_DOMAIN_CODE) != null) {
            mDomainCode = data.getStringExtra(DomainFragment.EXTRA_DOMAIN_CODE);
            mDomainName = data.getStringExtra(DomainFragment.EXTRA_DOMAIN_NAME);
            mIsCity = data.getIntExtra(DomainFragment.EXTRA_IS_CITY, 0);
            mCarNo = data.getStringExtra(DomainFragment.EXTRA_CAR_NO);
            mIsRemember = data.getBooleanExtra(DomainFragment.EXTRA_IS_REMEMBER, false);
        } else {

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item_output, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_output_setting:
                startDomainSettingActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_output, container, false);

//        mDomainCode = (String) getArguments().getSerializable(EXTRA_DOMAIN_CODE);
//        mDomainName = (String) getArguments().getSerializable(EXTRA_DOMAIN_NAME);
//        mCarNo = (String) getArguments().getSerializable(EXTRA_CAR_NO);
//        mIsCity = (Integer) getArguments().getSerializable(EXTRA_IS_CITY);

        mAwbNoEditText = (EditText) view.findViewById(R.id.item_barcode);
        mAwbNoEditText.requestFocus();
        mAwbNoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 6) {
                    mAwbNo = s.toString();
                    new QueryAwbByNoTask().execute(mAwbNo);
                } else {
                    mDestEditText.setText(null);
                }

            }
        });

        mDestEditText = (EditText) view.findViewById(R.id.destination);
//        mDestEditText.setBackgroundColor(getResources().getColor(R.color.color_non_editable_text));
        mAwbSumEditText = (EditText) view.findViewById(R.id.package_sum);

        mButtonOk = (Button) view.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LangUtil.isEmpty(mDomainCode) || LangUtil.isEmpty(mCarNo)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_setting, Toast.LENGTH_SHORT).show();
                    getActivity().openOptionsMenu();
                    return;
                }
                String mAwbNo = mAwbNoEditText.getText().toString();
                String mAwbSum = mAwbSumEditText.getText().toString();
                String dest = mDestEditText.getText().toString();

                if (LangUtil.isEmpty(mAwbNo) || LangUtil.isEmpty(dest)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_awbno, Toast.LENGTH_LONG).show();
                    return;
                }

                if (!mDomainName.equals(dest)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_dest, Toast.LENGTH_LONG).show();
                    SoundAlert.getInstance().soundAlert();
                    return;
                }

                if (LangUtil.parseInteger(mAwbSum, 0) <= 0 || LangUtil.parseInteger(mAwbSum) > piece) {
                    Toast.makeText(getActivity(), R.string.error_invalid_sum, Toast.LENGTH_LONG).show();
                    return;
                }

                new CargoOutStoAwbNoTask().execute(mAwbNo, dest, mAwbSum);
            }
        });
//        mButtonCancel = (Button) view.findViewById(R.id.button_cancel);
//        mButtonCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFragmentManager().popBackStack();//返回上一fragment
//            }
//        });
        return view;
    }

    private class CargoOutStoAwbNoTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String awbNo = params[0];
            String dest = params[1];
            String awbSum = params[2];

            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_CargoOutStoAwbNo";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("domaincode", mDomainCode);
            soapObject.addProperty("domainname", mDomainName);
            soapObject.addProperty("iscity", mIsCity);
            soapObject.addProperty("carno", mCarNo);
            soapObject.addProperty("awbid", mAwbId);
            soapObject.addProperty("awbno", awbNo);
            soapObject.addProperty("awbDes", dest);
            soapObject.addProperty("outpic", LangUtil.parseInteger(awbSum));
            soapObject.addProperty("oper", LoginInfo.getInstance().getUserName());
            soapObject.addProperty("opsto", LoginInfo.getInstance().getSto());
            soapObject.addProperty("opcity", LoginInfo.getInstance().getAtCity());
            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            String stoResult = result == null ? null : result.getPropertyAsString("MD_CargoOutStoAwbNoResult");
            return stoResult;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ("anyType{}".equals(s)) {
                Toast.makeText(getActivity(), R.string.tips_request_success, Toast.LENGTH_SHORT).show();
                mAwbNoEditText.setText(null);
                mDestEditText.setText(null);
                mAwbSumEditText.setText(String.valueOf(1));
            } else {
                Toast.makeText(getActivity(), R.string.tips_request_failed + s, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class QueryAwbByNoTask extends AsyncTask<String, Void, AwbOut> {

        @Override
        protected AwbOut doInBackground(String... params) {
            String awbNo = params[0];

            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_QueryAwbByNo";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("awbno", awbNo);
            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            AwbOut awbOut = null;
            try {
                List<AwbOut> awbOuts = WebserviceClientUtil.parseSoapResultSet((SoapObject) result.getProperty("MD_QueryAwbByNoResult"), AwbOut.class);
                awbOut = LangUtil.isEmpty(awbOuts) ? null : awbOuts.get(0);
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return awbOut;
        }

        @Override
        protected void onPostExecute(AwbOut awbOut) {
            super.onPostExecute(awbOut);
            if (awbOut != null) {
                mDestEditText.setText(awbOut.getDES());
                mAwbId = LangUtil.parseString(awbOut.getID());
                piece = awbOut.getPIECE();
            } else {
                mDestEditText.setText(null);
                mAwbId = null;
                piece = 0;
            }

        }
    }

}
