package com.poya.pengfusheng.repositorysys.base.utils;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pengfusheng on 2016/1/29.
 */
public class WebserviceClientUtil {

    private static final String TAG = "WebserviceClientUtil";

    public static SoapObject sendDotNetWebservice(String url, SoapObject soapObject, String soapAction) {
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
//        String xml = result == null ? null : result.getProperty(0).toString();
//        Log.d(TAG, "Get ws result :" + result);
        return result;
    }


    public static <T> List<T> parseSoapResultSet(SoapObject soapResult, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> results = new ArrayList<>();
        if (soapResult == null || !(soapResult instanceof SoapObject)) return results;
        for (int i = 0; i < soapResult.getPropertyCount(); i++) {
            SoapObject O1 = (SoapObject) soapResult.getProperty(i);
            for (int j = 0; j < O1.getPropertyCount(); j++) {
                SoapObject O2 = (SoapObject) O1.getProperty(j);
                for (int k = 0; k < O2.getPropertyCount(); k++) {

                    PropertyInfo tablePropertyInfo = new PropertyInfo();
                    O2.getPropertyInfo(k, tablePropertyInfo);
                    String n = tablePropertyInfo.getName();
                    if (!"Table".equals(n)) continue;//过虑Table节点
                    SoapObject O3 = (SoapObject) O2.getProperty(k);
                    T instance = clazz.newInstance();
                    for (int l = 0; l < O3.getPropertyCount(); l++) {
                        PropertyInfo info = new PropertyInfo();
                        O3.getPropertyInfo(l, info);
//                        Log.i(TAG, info.getName());
//                        Log.i(TAG, info.getValue().toString());
                        String value = info.getValue().toString();
                        if (!"anyType{}".equals(value))
                            setPropValue(instance, info.getName(), value);
                    }
                    results.add(instance);
                }
            }
        }
        return results;
    }


    /**
     * 反射设置对象的特定属性的值
     *
     * @param targetObj
     * @param propName
     * @param propValue
     */
    public static void setPropValue(Object targetObj, String propName, Object propValue) {
        Class targetClass = targetObj.getClass();
        try {
            Class targetC = Class.forName(targetClass.getName());
            Field field = targetC.getDeclaredField(propName);
            field.setAccessible(true);
            if (field.getType().equals(Integer.class)) {
                field.set(targetObj, LangUtil.parseInteger(propValue.toString(), 0));
            }
            if (field.getType().equals(int.class)) {
                field.setInt(targetObj, LangUtil.parseInteger(propValue.toString(), 0));
            }
            if (field.getType().equals(String.class)) {
                field.set(targetObj, propValue.toString());
            }
            if (field.getType().equals(double.class)) {
                field.setDouble(targetObj, LangUtil.parseDouble(propValue.toString(), 0D));
            }
            if (field.getType().equals(Double.class)) {
                field.set(targetObj, LangUtil.parseDouble(propValue.toString(), 0D));
            }
            if (field.getType().equals(float.class)) {
                field.setFloat(targetObj, LangUtil.parseFloat(propValue.toString(), 0f));
            }
            if (field.getType().equals(Float.class)) {
                field.set(targetObj, LangUtil.parseFloat(propValue.toString(), 0f));
            }
            if (field.getType().equals(Long.class)) {
                field.set(targetObj, LangUtil.parseLong(propValue.toString(), 0L));
            }
            if (field.getType().equals(long.class)) {
                field.setFloat(targetObj, LangUtil.parseLong(propValue.toString(), 0L));
            }
            if (field.getType().equals(short.class)) {
                Short s = 0;
                field.setShort(targetObj, LangUtil.parseShort(propValue.toString(), s));
            }
            if (field.getType().equals(Short.class)) {
                Short s = 0;
                field.set(targetObj, LangUtil.parseShort(propValue.toString(), s));
            }
            if (field.getType().equals(java.util.Date.class)) {
                field.set(targetObj, LangUtil.parseDate(propValue.toString()));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
