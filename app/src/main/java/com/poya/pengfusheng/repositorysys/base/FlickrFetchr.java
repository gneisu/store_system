package com.poya.pengfusheng.repositorysys.base;

import android.net.Uri;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pengfusheng on 2016/1/24.
 */
public class FlickrFetchr {
    private static final String TAG = "FlickrFetchr";
    private static final String ENDPOINT = "http://www.baidu.com";
    private static final String METHOD = "aa";


    public void fetchItems() {
        try {
            String url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("method", METHOD)
                    .build().toString();
            String xmlString = getUrl(url);
            Log.i(TAG, "Received xml: " + xmlString);
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch items", e);
        }
    }


    private byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public String getWSUrl() {
//        String url = "http://192.168.169.52:8001/PoyaService?wsdl";
//        String url = "http://192.168.169.52:8084/commInterface/cxf/Hello?wsdl";
//        String url = "http://121.40.52.83/PandaWS/PandaWebService.asmx";
//        String url = "http://121.40.52.83/PandaWS/PandaWebService.asmx?WSDL";
//        String url = "http://webservice.webxml.com.cn/webservices/qqOnlineWebService.asmx?wsdl";
//        String soapAction = "http://tempuri.org/";
//        String soapAction = "http://WebXml.com.cn/";
//        String soapAction = "http://tempuri.org/MD_DomainCodeQuery";
        String namespace = "http://tempuri.org/";

        String methodName = "MD_SaveCargoMainBarcode";
        String soapAction = "http://tempuri.org/MD_SaveCargoMainBarcode";
        String url = "http://121.40.52.83/PandaWS/PandaWebService.asmx";

        SoapObject soapObject = new SoapObject(namespace, methodName);
        soapObject.addProperty("barcode","55555");
        SoapObject sebarcodeObject = new SoapObject(namespace, methodName);
        sebarcodeObject.addProperty("string","123");
        sebarcodeObject.addProperty("string","321");
        soapObject.addProperty("sebarcode", sebarcodeObject);
        soapObject.addProperty("oper","test");



//        soapObject.addProperty("loginpwd","test");
//        soapObject.addProperty("sebarcode","654321");
//        PropertyInfo info = new PropertyInfo();
//        info.setName("sebarcode");
//        info.setValue(list);
//        soapObject.addProperty(info);
//        soapObject.addpro
//        soapObject.addProperty("oper","test");


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE ht = new HttpTransportSE(url);
        SoapObject result = null;
        try {
            ht.call(soapAction, envelope);
            Object o = envelope.getResponse();
            if (o != null) {
                result = (SoapObject) envelope.bodyIn;
            }
        } catch (IOException ioe) {
            Log.e(TAG, "Failed call webservice :", ioe);
        } catch (XmlPullParserException xmle) {
            Log.e(TAG, "Failed call webservice :", xmle);
        }
        String xml = result == null ? null : result.getProperty(0).toString();
        Log.i(TAG, "Get ws result :" + result);
        return xml;
    }

}
