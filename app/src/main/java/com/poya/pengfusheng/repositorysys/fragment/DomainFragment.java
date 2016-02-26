package com.poya.pengfusheng.repositorysys.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.poya.pengfusheng.repositorysys.DomainActivity;
import com.poya.pengfusheng.repositorysys.R;
import com.poya.pengfusheng.repositorysys.base.conf.DotNetWebservice;
import com.poya.pengfusheng.repositorysys.base.utils.WebserviceClientUtil;
import com.poya.pengfusheng.repositorysys.pojo.Domain;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

/**
 * Created by pengfusheng on 2016/1/30.
 */
public class DomainFragment extends Fragment {

    public static final String REQUEST_TYPE = "com.poya.pengfusheng.repositorysys.fragment.requestType";
    public static final int REQUEST_PACKAGE_INPUT = 1;
    public static final int REQUEST_PACKAGE_OUTPUT = 2;
    public static final int REQUEST_ITEM_OUTPUT = 3;
    private static final String TAG_DOMAIN = "DomainFragment";
    private int mCurRequest = 0;

    private EditText mDomainCodeEditText;
    private EditText mDomainNameEditText;
    private EditText mCarNoEditText;
    private Button mButtonOk;
    private Button mButtonCancel;

    private String mDomainCode;
    private String mDomainName;
    private String mCarNo;
    private int mIsCity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_package_domain, container, false);

        mCurRequest = (Integer) getActivity().getIntent().getSerializableExtra(REQUEST_TYPE);

        mDomainCodeEditText = (EditText) view.findViewById(R.id.domain_code);
        mDomainCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 5 && !s.equals(mDomainCode)) {
                    mDomainCode = mDomainCodeEditText.getText().toString();
                    new QueryDomainTask().execute();
                }
            }
        });

        mDomainNameEditText = (EditText) view.findViewById(R.id.domain_name);
//        mDomainNameEditText.setBackgroundColor(getResources().getColor(R.color.color_non_editable_text));
        mCarNoEditText = (EditText) view.findViewById(R.id.car_no);

        mButtonOk = (Button) view.findViewById(R.id.button_next);
        mButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDomainName = mDomainCodeEditText.getText().toString();
                mCarNo = mCarNoEditText.getText().toString();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                Fragment fragment = newFragment(mDomainCodeEditText.getText().toString(), mDomainName, mCarNo);
                transaction.replace(R.id.fragmentContainer, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
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
//            Log.i(TAG_DOMAIN, "webservice result : " + d);
            if (d != null) {
                mDomainName = d.getDOMAINNAME();
                mIsCity = d.getISCITY();
                mCarNoEditText.requestFocus();
            } else {
                mDomainName = null;
                mIsCity = 0;
            }
            mDomainNameEditText.setText(mDomainName);
        }
    }

}
