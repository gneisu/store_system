package com.poya.pengfusheng.repositorysys.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poya.pengfusheng.repositorysys.R;
import com.poya.pengfusheng.repositorysys.base.conf.DotNetWebservice;
import com.poya.pengfusheng.repositorysys.base.utils.LangUtil;
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

    private String mDomainCode;
    private String mDomainName;
    private String mCarNo;
    private int mIsCity;

    private String mAwbNo;
    private String mAwbId;


    private EditText mAwbNoEditText;
    private EditText mDestEditText;
    private EditText mAwbSumEditText;

    private Button mButtonOk;
    private Button mButtonCancel;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_output, container, false);

        mDomainCode = (String) getArguments().getSerializable(EXTRA_DOMAIN_CODE);
        mDomainName = (String) getArguments().getSerializable(EXTRA_DOMAIN_NAME);
        mCarNo = (String) getArguments().getSerializable(EXTRA_CAR_NO);
        mIsCity = (Integer) getArguments().getSerializable(EXTRA_IS_CITY);

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
                String mAwbNo = mAwbNoEditText.getText().toString();
                String mAwbSum = mAwbSumEditText.getText().toString();
                String dest = mDestEditText.getText().toString();
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
                mAwbSumEditText.setText(LangUtil.parseString(1));
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
            } else {
                mDestEditText.setText(null);
                mAwbId = null;
            }

        }
    }

}
