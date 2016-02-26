package com.poya.pengfusheng.repositorysys.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poya.pengfusheng.repositorysys.R;
import com.poya.pengfusheng.repositorysys.base.conf.DotNetWebservice;
import com.poya.pengfusheng.repositorysys.base.utils.WebserviceClientUtil;

import org.ksoap2.serialization.SoapObject;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class PackageInputFragment extends Fragment {
    private static final String TAG = "PackageInputFragment";
    public static final String EXTRA_DOMAIN_CODE = "com.poya.pengfusheng.repositorysys.fragment.domaincode";
    public static final String EXTRA_DOMAIN_NAME = "com.poya.pengfusheng.repositorysys.fragment.domainname";
    public static final String EXTRA_IS_CITY = "com.poya.pengfusheng.repositorysys.fragment.iscity";
    public static final String EXTRA_CAR_NO = "com.poya.pengfusheng.repositorysys.fragment.carno";

    private String mDomainCode;
    private String mDomainName;
    private String mCarNo;
    private int mIsCity;


    private EditText mAwbNoEditText;
    private EditText mAwbSumEditText;
    private Button mButtonOk;
    private Button mButtonCancel;


    public static PackageInputFragment newInstance(String domainCode, String domainName, String carNo,int isCity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DOMAIN_CODE, domainCode);
        bundle.putSerializable(EXTRA_DOMAIN_NAME, domainName);
        bundle.putSerializable(EXTRA_IS_CITY, isCity);
        bundle.putSerializable(EXTRA_CAR_NO, carNo);
        PackageInputFragment fragment = new PackageInputFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_input, container, false);

        mDomainCode = (String) getArguments().getSerializable(EXTRA_DOMAIN_CODE);
        mDomainName = (String) getArguments().getSerializable(EXTRA_DOMAIN_NAME);
        mIsCity = (Integer) getArguments().getSerializable(EXTRA_IS_CITY);
        mCarNo = (String) getArguments().getSerializable(EXTRA_CAR_NO);

        mAwbNoEditText = (EditText) view.findViewById(R.id.awb_no);
        mAwbNoEditText.requestFocus();
        mAwbSumEditText = (EditText) view.findViewById(R.id.awb_sum);

        mButtonOk = (Button) view.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mAwbNo = mAwbNoEditText.getText().toString();
                String mAwbSum = mAwbSumEditText.getText().toString();
                new CargoImStoAwbNoTask().execute(mAwbNo, mAwbSum);
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

    private class CargoImStoAwbNoTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String awbNo = params[0];
            String awbSum = params[1];

            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_CargoImStoAwbNo";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("domaincode", mDomainCode);
            soapObject.addProperty("domainname", mDomainName);
            soapObject.addProperty("iscity", mIsCity);
            soapObject.addProperty("carno", mCarNo);
            soapObject.addProperty("awbno", awbNo);
            soapObject.addProperty("impic", awbSum);
            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            String stoResult = result == null ? null : result.getPropertyAsString("MD_CargoImStoAwbNoResult");
            return stoResult;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Log.i(TAG, s);
            if (s != null) {
                Toast.makeText(getActivity(), R.string.tips_request_success, Toast.LENGTH_SHORT).show();
                mAwbNoEditText.setText(null);
                mAwbSumEditText.setText(null);
                mAwbNoEditText.setFocusable(true);
                mAwbNoEditText.requestFocus();
            } else {
                Toast.makeText(getActivity(), R.string.tips_request_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
