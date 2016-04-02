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
import com.poya.pengfusheng.repositorysys.pojo.AwbBarcode;
import com.poya.pengfusheng.repositorysys.pojo.AwbOut;
import com.poya.pengfusheng.repositorysys.pojo.LoginInfo;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class PackageSignatureFragment extends Fragment {
    private static final String TAG = "PackageSignatureFragment";

    private int mAwbId;
    private String mAwbNo;
    private String mDep;
    private String mDes;
    private String mSigner;

    private EditText mAwbBarcodeEditText;
    private EditText mSignatureEditText;
    private EditText mChargeEditText;
    private EditText mSubChargeEditText;

    private Button mButtonOk;
    private Button mButtonCancel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_signature, container, false);

        mAwbBarcodeEditText = (EditText) view.findViewById(R.id.item_code);
        mAwbBarcodeEditText.requestFocus();
        mAwbBarcodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 6) {
                    new CargoAwbBarcoQueryTask().execute(s.toString());
                }
            }
        });

        mSignatureEditText = (EditText) view.findViewById(R.id.signature);
//        mSignatureEditText.setBackgroundColor(getResources().getColor(R.color.color_non_editable_text));
        mChargeEditText = (EditText) view.findViewById(R.id.pay);
//        mChargeEditText.setBackgroundColor(getResources().getColor(R.color.color_non_editable_text));
        mSubChargeEditText = (EditText) view.findViewById(R.id.pay_other);
//        mSubChargeEditText.setBackgroundColor(getResources().getColor(R.color.color_non_editable_text));

        mButtonOk = (Button) view.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemCode = mAwbBarcodeEditText.getText().toString();
                String sig = mSignatureEditText.getText().toString();
                if (LangUtil.isEmpty(itemCode) || LangUtil.isEmpty(sig)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_item_code, Toast.LENGTH_LONG).show();
                    return;
                }
                new CargoSignedTask().execute();
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

    private class CargoSignedTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {

            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_CargoSigned";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("awbid", mAwbId);
            soapObject.addProperty("awbno", mAwbNo);
            soapObject.addProperty("dep", mDep);
            soapObject.addProperty("des", mDes);
            soapObject.addProperty("signer", mSigner);
            soapObject.addProperty("oper", LoginInfo.getInstance().getUserName());
            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            String stoResult = result == null ? null : result.getPropertyAsString("MD_CargoSignedResult");
            return stoResult;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ("anyType{}".equals(s)) {
                Toast.makeText(getActivity(), R.string.tips_request_success, Toast.LENGTH_SHORT).show();
                mAwbBarcodeEditText.setText(null);
                mSubChargeEditText.setText(null);
                mSignatureEditText.setText(null);
                mChargeEditText.setText(null);
            } else {
                Toast.makeText(getActivity(), R.string.tips_request_failed + s, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class CargoAwbBarcoQueryTask extends AsyncTask<String, Void, AwbBarcode> {
        @Override
        protected AwbBarcode doInBackground(String... params) {
            String barcode = params[0];

            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_CargoAwbBarcoQuery";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("barcode", barcode);
            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            AwbBarcode awbBarcode = null;
            try {
                List<AwbBarcode> awbBarcodeList = WebserviceClientUtil.parseSoapResultSet((SoapObject) result.getProperty("MD_CargoAwbBarcoQueryResult"), AwbBarcode.class);
                awbBarcode = LangUtil.isEmpty(awbBarcodeList) ? null : awbBarcodeList.get(0);
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return awbBarcode;
        }

        @Override
        protected void onPostExecute(AwbBarcode awbBarcode) {
            super.onPostExecute(awbBarcode);
            if (awbBarcode != null) {
                mSignatureEditText.setText(awbBarcode.getCONSIGNEE());
                mChargeEditText.setText(LangUtil.parseString(awbBarcode.getDEPCOLLCHARGE()));
                mSubChargeEditText.setText(LangUtil.parseString(awbBarcode.getDEPSUBCHARGE()));
                mAwbId = awbBarcode.getAwbId();
                mAwbNo = awbBarcode.getAWBNO();
                mDep = awbBarcode.getDEP();
                mDes = awbBarcode.getDES();
                mSigner = awbBarcode.getSIGNER();
            } else {
                mSignatureEditText.setText(null);
                mChargeEditText.setText(null);
                mSubChargeEditText.setText(null);
                mAwbId = 0;
                mAwbNo = null;
                mDep = null;
                mDes = null;
                mSigner = null;
            }
        }
    }

}
