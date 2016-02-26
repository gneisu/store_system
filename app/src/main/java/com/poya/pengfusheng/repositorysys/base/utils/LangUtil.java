package com.poya.pengfusheng.repositorysys.base.utils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by pengfusheng on 2016/2/22.
 */
public class LangUtil {
    /**
     * 判断对象是否为空，对象可为字符串、对象数组、Collection。对于字符串会先去除前后的空字符。
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().equals("");
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        return false;
    }

    /**
     * 判断对象是否为非空，对象可为字符串、对象数组、Collection。对于字符串会先去除前后的空字符。
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        return !LangUtil.isEmpty(obj);
    }

    /**
     * md5加密
     */
    public static String md5(String src) {
        if (src == null)
            return null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(src.getBytes());
            StringBuilder dist = new StringBuilder();
            for (byte b : md.digest()) {
                String s = Integer.toHexString(0xFF & b);
                if (s.length() == 1) dist.append("0");
                dist.append(s);
            }
            return dist.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 去掉前后空白字符，包括中文空格
     */
    public static String trimCn(String str) {
        if (str == null)
            return null;
        str = str.trim();
        if (str.length() == 0)
            return str;
        int st = 0;
        int len = str.length();
        while ((st < len) && str.charAt(st) == '　')
            st++;
        while ((st < len) && str.charAt(len - 1) == '　')
            len--;
        if (st > 0 || len < str.length())
            str = str.substring(st, len);
        return str.trim();
    }

    public static boolean contains(Object[] array, Object item) {
        for (Object obj : array) {
            if (obj.equals(item)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 将字符串格式成sql查询的like形式，即在前后分别加上%，空格也替换成%。
     *
     * @return 返回sql模糊查询的字符串，如果字符串为空则返回null
     */
    public static String makeSqlLike(String key) {
        return LangUtil.isNotEmpty(key) ? "%" + key.trim().replaceAll("\\s", "%") + "%" : null;
    }

    public static String isoToUtf8(String str) {
        if (str == null) return null;
        try {
            return new String(str.getBytes("iso-8859-1"), "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将日期转换成字符串，格式为yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate(Date date) {
        if (date == null) return null;
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 将日期字符串转换成日期，只支持如下格式：
     * yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
     */
    public static Date parseDate(String str) {
        if (str == null) return null;
        String str1 = str;
        try {
            String f = (str.length() == 10 ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss");
            if (str.indexOf("T") > 0 && str.length()>=19) {
                str1 = str.substring(0,19).replace("T"," ");
            }
            return new SimpleDateFormat(f).parse(str1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转换为Long，转换失败返回null
     */
    public static Long parseLong(String str) {
        return LangUtil.parseLong(str, null);
    }

    /**
     * 将字符串转换为Long，转换失败返回defaultValue
     */
    public static Long parseLong(String str, Long defaultValue) {
        if (str == null) return null;
        try {
            return Long.parseLong(str);
        } catch (Exception e) {

        }
        return defaultValue;
    }

    /**
     * 将字符串转换为Integer，转换失败返回null
     */
    public static Integer parseInteger(String str) {
        return LangUtil.parseInteger(str, null);
    }

    /**
     * 将字符串转换为Integer，转换失败返回defaultValue
     */
    public static Integer parseInteger(String str, Integer defaultValue) {
        if (str == null) return null;
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {

        }
        return defaultValue;
    }

    /**
     * 将字符串转换为Float，转换失败返回null
     */
    public static Float parseFloat(String str) {
        return LangUtil.parseFloat(str, null);
    }

    /**
     * 将字符串转换为Float，转换失败返回defaultValue
     */
    public static Float parseFloat(String str, Float defaultValue) {
        if (str == null) return null;
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {

        }
        return defaultValue;
    }

    /**
     * 将字符串转换为Double，转换失败返回null
     */
    public static Double parseDouble(String str) {
        return LangUtil.parseDouble(str, null);
    }

    /**
     * 将字符串转换为Double，转换失败返回defaultValue
     */
    public static Double parseDouble(String str, Double defaultValue) {
        if (str == null) return null;
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {

        }
        return defaultValue;
    }

    public static Short parseShort(String str) {
        return parseShort(str, null);
    }

    public static Short parseShort(String str, Short defaultValue) {
        if (str == null) return null;
        try {
            return Short.parseShort(str);
        } catch (Exception e) {

        }
        return defaultValue;
    }

    public static String parseString(Object object) {
        if (object == null) return null;
        return object.toString();
    }

    public static Boolean parseBoolean(Object object) {
        return parseBoolean(object, false);
    }
    public static Boolean parseBoolean(Object object, Boolean defaultValue) {
        if (LangUtil.isEmpty(object)) return false;
        try {
            return Boolean.parseBoolean(object.toString());
        } catch (Exception e) {

        }
        return defaultValue;
    }

    public static String escapeHtml(String html) {
        if (html == null) return null;
        return html.replaceAll("<", "&lt;");
    }


    /**
     * 生成order by sortName sortOrder语句
     *
     * @param sortName
     * @param sortOrder
     * @return
     */
    public static String makeSqlOrderBy(String sortName, String sortOrder) {
        if (isNotEmpty(sortName) && isNotEmpty(sortOrder)) {
            return "  order by " + sortName.trim() + "  " + sortOrder.trim();
        } else {
            return null;
        }
    }

    /**
     * 保留两位小数
     *
     * @param num
     * @return
     */
    public static Double decimalFormat(Double num) {
        DecimalFormat df = new DecimalFormat("#.00");
        return parseDouble(df.format(num));
    }
}
