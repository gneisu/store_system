package com.poya.pengfusheng.repositorysys.base.utils;

import com.poya.pengfusheng.repositorysys.base.Constants;
import com.poya.pengfusheng.repositorysys.base.conf.DotNetWebservice;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by pengfusheng on 2016/1/29.
 */

public class WebServiceUtil {
    // 定义Web Service的命名空间
    static final String SERVICE_NAMESPACE = DotNetWebservice.NAMESPACE;
    // 定义Web Service提供服务的URL
    static final String SERVICE_URL = DotNetWebservice.HTTP_URL;

    /**
     * 发送webservice请求，并返回特定对象链表
     *
     * @param method        方法名
     * @param paramNames    参数列表
     * @param paramValues   参数值列表
     * @param responseClass 返回类
     * @return
     */
    public static List<Object> callService(String soapAction,String method, String[] paramNames, String[] paramValues, Class responseClass, boolean isArray) {
        // 创建HttpTransportSE传输对象
        HttpTransportSE httpTranstation = new HttpTransportSE(SERVICE_URL);
        httpTranstation.debug = true;

        // 使用SOAP1.1协议创建Envelop对象
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        // 实例化SoapObject对象
        SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, method);

        // 设置webservice方法参数值
        for (int i = 0; i < paramNames.length; i++) {
            soapObject.addProperty(paramNames[i], paramValues[i]);
        }
        envelope.dotNet = true;
        envelope.bodyOut = soapObject;
        envelope.setOutputSoapObject(soapObject);
        try {
            // 调用Web Service
            httpTranstation.call(soapAction, envelope);
            if (envelope.getResponse() != null) {
                // 获取服务器响应返回的SOAP消息
                SoapObject bodyIn = (SoapObject) envelope.bodyIn;
                SoapObject detail = (SoapObject) bodyIn.getProperty(method + "Return");
                return parseResponse(detail, responseClass, method, isArray);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理webservice回复，并返回对象列表
     *
     * @param result
     * @param classes
     * @param method
     * @return List<Object>
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static List<Object> parseResponse(SoapObject result, Class classes, String method, boolean isArray)
            throws InstantiationException,
            IllegalAccessException {
        List<Object> results = new ArrayList<Object>();

        if (isArray) {
            for (int i = 0; i < result.getPropertyCount(); i++) {
                Object instance = classes.newInstance();
                Field[] fields = classes.getDeclaredFields();

                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    String value = ((SoapObject) result.getProperty(i)).getProperty(fields[j].getName()).toString();
                    setPropValue(instance, fields[j].getName(), value);
                }
                results.add(instance);
            }
        } else {
            Object instance = classes.newInstance();
            Field[] fields = classes.getDeclaredFields();

            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                String value = result.getProperty(fields[j].getName()).toString();
                setPropValue(instance, fields[j].getName(), value);
            }
            results.add(instance);
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
                field.set(targetObj,
                        new Integer(Integer.valueOf(propValue.toString())));
            }
            if (field.getType().equals(int.class)) {
                field.setInt(targetObj, Integer.valueOf(propValue.toString()));
            }
            if (field.getType().equals(String.class)) {
                field.set(targetObj, propValue.toString());
            }
            if (field.getType().equals(double.class)) {
                field.setDouble(targetObj, Double.valueOf(propValue.toString()));
            }
            if (field.getType().equals(Double.class)) {
                field.set(targetObj,
                        new Double(Double.valueOf(propValue.toString())));
            }
            if (field.getType().equals(float.class)) {
                field.setFloat(targetObj, Float.valueOf(propValue.toString()));
            }
            if (field.getType().equals(Float.class)) {
                field.set(targetObj,
                        new Float(Float.valueOf(propValue.toString())));
            }
            if (field.getType().equals(Long.class)) {
                field.set(targetObj,
                        new Long(Long.valueOf(propValue.toString())));
            }
            if (field.getType().equals(long.class)) {
                field.setFloat(targetObj, Long.valueOf(propValue.toString()));
            }
            if (field.getType().equals(short.class)) {
                field.setShort(targetObj, Short.valueOf(propValue.toString()));
            }
            if (field.getType().equals(Short.class)) {
                field.set(targetObj,
                        new Short(Short.valueOf(propValue.toString())));
            }
            if (field.getType().equals(java.util.Date.class)) {
                field.set(targetObj, new Date(Date.parse(propValue.toString())));
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