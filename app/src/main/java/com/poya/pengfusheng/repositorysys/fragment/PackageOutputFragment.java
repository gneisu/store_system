package com.poya.pengfusheng.repositorysys.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
import com.poya.pengfusheng.repositorysys.pojo.BarCodeOut;
import com.poya.pengfusheng.repositorysys.pojo.LoginInfo;

import org.ksoap2.serialization.SoapObject;

import java.io.IOException;
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

    public static final int REQUEST_PACKAGE_OUTPUT = 2;

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
    private boolean mIsRemember;

    private EditText mPackageBarcodeEditText;
    private EditText mItemBarcodeEditText;
    private EditText mDestinationEditText;
    private EditText mPackageSumEditText;

    private Button mButtonOk;
    private Button mButtonCancel;

    private SharedPreferences mPreferences;


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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 注册默认音频通道
//        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setHasOptionsMenu(true);
        mPreferences = getActivity().getSharedPreferences(DomainSettingConstants.FILE_NAME_PACKAGE_OUTPUT, Context.MODE_PRIVATE);
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
        intent.putExtra(DomainFragment.REQUEST_TYPE, DomainFragment.REQUEST_PACKAGE_OUTPUT);
        intent.putExtra(DomainFragment.EXTRA_DOMAIN_CODE, mDomainCode);
        intent.putExtra(DomainFragment.EXTRA_DOMAIN_NAME, mDomainName);
        intent.putExtra(DomainFragment.EXTRA_CAR_NO, mCarNo);
        intent.putExtra(DomainFragment.EXTRA_IS_CITY, mIsCity);
        intent.putExtra(DomainFragment.EXTRA_IS_REMEMBER, mIsRemember);
        startActivityForResult(intent, REQUEST_PACKAGE_OUTPUT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PACKAGE_OUTPUT && data != null && data.getSerializableExtra(DomainFragment.EXTRA_DOMAIN_CODE) != null) {
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
        inflater.inflate(R.menu.menu_package_output, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_package_output_setting:
                startDomainSettingActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_output, container, false);

//        mDomainCode = (String) getArguments().getSerializable(EXTRA_DOMAIN_CODE);
//        mDomainName = (String) getArguments().getSerializable(EXTRA_DOMAIN_NAME);
//        mCarNo = (String) getArguments().getSerializable(EXTRA_CAR_NO);
//        mIsCity = (Integer) getArguments().getSerializable(EXTRA_IS_CITY);

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
                } else {
                    mDestinationEditText.setText(null);
                    mItemBarcodeEditText.setText(null);
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
                if (LangUtil.isEmpty(mDomainCode) || LangUtil.isEmpty(mCarNo)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_setting, Toast.LENGTH_SHORT).show();
                    getActivity().openOptionsMenu();
                    return;
                }
                String barcode = mPackageBarcodeEditText.getText().toString();
                String itemCode = mItemBarcodeEditText.getText().toString();
                String dest = mDestinationEditText.getText().toString();
                String sum = mPackageSumEditText.getText().toString();

                if (LangUtil.isEmpty(barcode) || LangUtil.isEmpty(dest) || LangUtil.isEmpty(itemCode)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_barcode, Toast.LENGTH_LONG).show();
                    return;
                }

                if (!mDomainName.equals(dest)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_dest, Toast.LENGTH_LONG).show();
                    SoundAlert.getInstance().soundAlert();
                    return;
                }

                if (LangUtil.parseInteger(sum, 0) <= 0 || LangUtil.parseInteger(sum) > mOutpic) {
                    Toast.makeText(getActivity(), R.string.error_invalid_sum, Toast.LENGTH_LONG).show();
                    return;
                }

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
            if (result == null) {
                return null;
            }
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
