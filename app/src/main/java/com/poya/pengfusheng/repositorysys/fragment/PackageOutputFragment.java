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
import com.poya.pengfusheng.repositorysys.pojo.BarCodeOut;
import com.poya.pengfusheng.repositorysys.pojo.LoginInfo;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class PackageOutputFragment extends Fragment {
    private static final String TAG = "PackageOutputFragment";
    public static final String EXTRA_DOMAIN_CODE = "com.poya.pengfusheng.repositorysys.fragment.domaincode";
    public static final String EXTRA_DOMAIN_NAME = "com.poya.pengfusheng.repositorysys.fragment.domainname";
    public static final String EXTRA_IS_CITY = "com.poya.pengfusheng.repositorysys.fragment.iscity";
    public static final String EXTRA_CAR_NO = "com.poya.pengfusheng.repositorysys.fragment.carno";

    private String mDomainCode;
    private String mDomainName;
    private String mCarNo;
    private int mIsCity;

    private String mBarcode;
    private String mAwbid;
    private String mAwbno;
    private String mAwbDes;
    private int mOutpic;
    private String mOper;
    private String mOpsto;
    private String mOpcity;

    private EditText mPackageBarcodeEditText;
    private EditText mItemBarcodeEditText;
    private EditText mDestinationEditText;
    private EditText mPackageSumEditText;

    private Button mButtonOk;
    private Button mButtonCancel;


    public static PackageOutputFragment newInstance(String domainCode, String domainName, String carNo, int isCity) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DOMAIN_CODE, domainCode);
        bundle.putSerializable(EXTRA_DOMAIN_NAME, domainName);
        bundle.putSerializable(EXTRA_IS_CITY, isCity);
        bundle.putSerializable(EXTRA_CAR_NO, carNo);
        PackageOutputFragment fragment = new PackageOutputFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_output, container, false);

        mDomainCode = (String) getArguments().getSerializable(EXTRA_DOMAIN_CODE);
        mDomainName = (String) getArguments().getSerializable(EXTRA_DOMAIN_NAME);
        mCarNo = (String) getArguments().getSerializable(EXTRA_CAR_NO);
        mIsCity = (Integer) getArguments().getSerializable(EXTRA_IS_CITY);

        mPackageBarcodeEditText = (EditText) view.findViewById(R.id.package_barcode);
        mPackageBarcodeEditText.requestFocus();
        mPackageBarcodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 6) {
                    mBarcode = mPackageBarcodeEditText.getText().toString();
                    new BarCodeOutQueryTask().execute(mBarcode);
                }

            }
        });

        mItemBarcodeEditText = (EditText) view.findViewById(R.id.item_barcode);
//        mItemBarcodeEditText.setBackgroundColor(getResources().getColor(R.color.color_non_editable_text));
        mDestinationEditText = (EditText) view.findViewById(R.id.destination);
//        mDestinationEditText.setBackgroundColor(getResources().getColor(R.color.color_non_editable_text));
        mPackageSumEditText = (EditText) view.findViewById(R.id.package_sum);

        mButtonOk = (Button) view.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String barcode = mPackageBarcodeEditText.getText().toString();
                String itemCode = mItemBarcodeEditText.getText().toString();
                String dest = mDestinationEditText.getText().toString();
                String sum = mPackageSumEditText.getText().toString();
                new CargoOutStoBarCodeTask().execute(barcode, itemCode, dest, sum);
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

    private class CargoOutStoBarCodeTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String barcode = params[0];
            String itemcode = params[1];
            String dest = params[2];
            String num = params[3];

            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_CargoOutStoBarCode";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("barcode", barcode);
            soapObject.addProperty("domaincode", mDomainCode);
            soapObject.addProperty("domainname", mDomainName);
            soapObject.addProperty("iscity", mIsCity);
            soapObject.addProperty("carno", mCarNo);
            soapObject.addProperty("awbid", mAwbid);
            soapObject.addProperty("awbno", itemcode);
            soapObject.addProperty("awbDes", dest);
            soapObject.addProperty("outpic", num);
            soapObject.addProperty("oper", LoginInfo.getInstance().getUserName());
            soapObject.addProperty("opsto", LoginInfo.getInstance().getSto());
            soapObject.addProperty("opcity", LoginInfo.getInstance().getAtCity());

            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            String stoResult = result == null ? null : result.getPropertyAsString("MD_CargoOutStoBarCodeResult");
            return stoResult;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if ("anyType{}".equals(s)) {
                Toast.makeText(getActivity(), R.string.tips_request_success, Toast.LENGTH_SHORT).show();
                mPackageBarcodeEditText.setText(null);
                mItemBarcodeEditText.setText(null);
                mDestinationEditText.setText(null);
                mPackageSumEditText.setText(String.valueOf(1));
            } else {
                Toast.makeText(getActivity(), R.string.tips_request_failed + s, Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class BarCodeOutQueryTask extends AsyncTask<String, Void, BarCodeOut> {
        @Override
        protected BarCodeOut doInBackground(String... params) {
            String barcode = params[0];

            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_BarCodeOutQuery";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("barcode", barcode);
            soapObject.addProperty("needchecked", true);
            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            BarCodeOut barCodeOut = null;
            try {
                List<BarCodeOut> barCodeOuts = WebserviceClientUtil.parseSoapResultSet((SoapObject) result.getProperty("MD_BarCodeOutQueryResult"), BarCodeOut.class);
                barCodeOut = LangUtil.isEmpty(barCodeOuts) ? null : barCodeOuts.get(0);
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return barCodeOut;
        }

        @Override
        protected void onPostExecute(BarCodeOut barCodeOut) {
            super.onPostExecute(barCodeOut);
            if (barCodeOut != null) {
                mItemBarcodeEditText.setText(barCodeOut.getAWBNO());
                mDestinationEditText.setText(barCodeOut.getDES());
//                mPackageSumEditText.setText(LangUtil.parseString(barCodeOut.getPIECE()));
                mOutpic = barCodeOut.getPIECE();
                mAwbid = LangUtil.parseString(barCodeOut.getAWBID());
                mAwbno = barCodeOut.getAWBNO();
            } else {
                mItemBarcodeEditText.setText(null);
                mDestinationEditText.setText(null);
                mOutpic = 0;
                mAwbid = null;
                mAwbno = null;
            }
        }
    }

}
