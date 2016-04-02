package com.poya.pengfusheng.repositorysys.fragment;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
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
import com.poya.pengfusheng.repositorysys.pojo.LoginInfo;

import org.ksoap2.serialization.SoapObject;

/**
 * Created by pengfusheng on 2016/1/27.
 */
public class PackageGetFragment extends Fragment {

    private static final String TAG = "PackageGetFragment";

    private Button mButtonOk;
    private Button mButtonCancel;
    private EditText mItemCode;
    private EditText mLabelCode;

    @TargetApi(11)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_get, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mItemCode = (EditText) view.findViewById(R.id.package_get_item_code);
        mLabelCode = (EditText) view.findViewById(R.id.package_get_label_code);

        mItemCode.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    mLabelCode.setFocusable(true);
                    mLabelCode.requestFocus();
                    return true;
                }
                return false;
            }
        });

        mButtonOk = (Button) view.findViewById(R.id.button_ok);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String itemCode = mItemCode.getText().toString();
                final String labelCodes = mLabelCode.getText().toString();
                if (LangUtil.isEmpty(itemCode)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_item_code, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (LangUtil.isEmpty(labelCodes)) {
                    Toast.makeText(getActivity(), R.string.error_invalid_label_code, Toast.LENGTH_SHORT).show();
                    return;
                }
                new SaveCargoTask().execute(itemCode, labelCodes);
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

    private class SaveCargoTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            if (params == null || params.length <= 0) {
                Log.w(TAG, "传入的参数异常：" + params);
                return null;
            }
            String itemCode = params[0];
            String labelCodes = params[1];
            String[] labelCodeArr = labelCodes == null ? null : labelCodes.split("\n");
            String namespace = DotNetWebservice.NAMESPACE;
            String url = DotNetWebservice.HTTP_URL;
            String methodName = "MD_SaveCargoMainBarcode";
            String soapAction = namespace + methodName;

            SoapObject soapObject = new SoapObject(namespace, methodName);
            soapObject.addProperty("barcode", itemCode);
            SoapObject sebarcodeObject = null;
            if (labelCodeArr != null) {
                sebarcodeObject = new SoapObject(namespace, methodName);
                for (String label : labelCodeArr) {
                    if (label != null && label.length() > 0)
                        sebarcodeObject.addProperty("string", label);
                }
            }
            soapObject.addProperty("sebarcode", sebarcodeObject);
            soapObject.addProperty("oper", LoginInfo.getInstance().getUserName());
            SoapObject result = WebserviceClientUtil.sendDotNetWebservice(url, soapObject, soapAction);
            Log.i(TAG, "webservice result : " + result);
            return result == null ? null : result.getProperty(0).toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                Toast.makeText(getActivity(), R.string.tips_request_success, Toast.LENGTH_SHORT).show();
                mItemCode.setText(null);
                mLabelCode.setText(null);
                mItemCode.setFocusable(true);
                mItemCode.requestFocus();
            } else {
                Toast.makeText(getActivity(), R.string.tips_request_failed + s, Toast.LENGTH_SHORT).show();
            }
        }

    }

}
