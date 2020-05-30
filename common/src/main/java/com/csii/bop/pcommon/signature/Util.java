package com.csii.bop.pcommon.signature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author X
 * @常用的关于日期和字符串处理的工具�?
 */

public class Util {

    /**
     *
     */
    private Util() {

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

		/*
		String date = "20120401";
		Calendar cal = CsiiUtils.bocmDateToCal(date);
		System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		//		System.out.println(Util.isWeekDay(date));
		System.out.println(Util.rolMonth(date, 3));
		//		System.out.println(Util.isSeasonEnd(date));
		//		System.out.println(Util.isHalfYearEnd(date));
		//		System.out.println(Util.isYearEnd(date));
		
		//		create calendar instance :: now
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,2006);
		//		month 0 is january
		calendar.set(Calendar.MONTH,2);
		int lastDateofMonth = calendar.getActualMaximum(Calendar.DATE);
		System.out.println(lastDateofMonth);
		
		*/
//		System.out.println(Util.isPeriodDay("20061220", "4"));

    }

    /**
     * 获取�?个对象的string形式并去除空�?
     *
     * @param object
     * @return
     */

    public static String toStringAndTrim(Object object) {
        if (object == null) {
            return null;
        } else {
            return object.toString().trim();
        }

    }

    /**
     * 比较两个对象是否相等
     *
     * @param firstStr
     * @param secondStr
     * @return
     */

    public static boolean trimAndEquals(Object firstStr, Object secondStr) {
        if (firstStr == null && secondStr == null) {
            return true;
        } else if (firstStr == null || secondStr == null) {
            return false;
        } else {
            return toStringAndTrim(firstStr).equals(toStringAndTrim(secondStr));
        }

    }

    /**
     * 判断输入的字符串是否是空
     *
     * @param inStr 输入字符�?
     * @return
     */
    public static boolean isNullOrEmpty(String inStr) {
        return (inStr == null || inStr.trim().length() == 0);
    }


    /**
     * 以输入周期类型为准，判断该类型是否是合法的手续费周期
     *
     * @param period :周期类型
     */
    public static boolean isValidFeePeriod(String period) {

        if (Util.isNullOrEmpty(period))
            return false;
        return true;
    }

    /**
     * 根据输入的周期类型以及商户的服务类型，产生表表文件名
     *
     * @param period      :周期类型
     * @param serviceType :商户服务类型
     */
    public static String getReportFileNamePrefix(
            Object period,
            Object serviceType) {
        StringBuffer sb = new StringBuffer();

        if (Constants.PERIOD_DAY.equals(period)) {
            sb.append("d");
        } else if (Constants.PERIOD_MONTH.equals(period)) {
            sb.append("m");
        } else if (Constants.PERIOD_SEASON.equals(period)) {
            sb.append("q");
        } else if (Constants.PERIOD_HALFYEAR.equals(period)) {
            sb.append("h");
        } else if (Constants.PERIOD_YEAR.equals(period)) {
            sb.append("y");
        } else {
            sb.append("");
        }

        return sb.toString();
    }

    /**
     * 以指定分隔符拆分输入的字符串并根据传入的清楚标志判断是否清除空格
     *
     * @param toSplit   :待拆分的字符�?
     * @param delimiter :分隔�?
     * @param trim      :是否�?要清除空格的标志
     */
    public static String[] split(
            String toSplit,
            char delimiter,
            boolean trim) {
        if (toSplit == null) {
            return null;
        }
        int len = toSplit.length();
        if (len == 0) {
            return null;
        }
        List list = new ArrayList();
        int i = 0, start = 0;
        boolean match = false;
        while (i < len) {

            if (toSplit.charAt(i) == delimiter) {
                if (match) {
                    if (trim)
                        list.add(toStringAndTrim(toSplit.substring(start, i)));
                    else
                        list.add(toSplit.substring(start, i));
                }
                start = ++i;
                match = true;
                continue;
            }
            match = true;
            i++;
        }
        if (match && toSplit.charAt(len - 1) != '|') {

            if (trim)
                list.add(toStringAndTrim(toSplit.substring(start, i)));
            else
                list.add(toSplit.substring(start, i));
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * 以指定分隔符拆分输入的字符串并清除空�?
     *
     * @param toSplit   :待拆分的字符�?
     * @param delimiter :分隔�?
     */
    public static String[] split(String toSplit, char delimiter) {

        return split(toSplit, delimiter, true);
    }

    /**
     * 把二进制的串转化为十六进制的字符�?
     * Creation date: (00-6-9 17:06:35)
     *
     * @param inbuf byte[]
     * @return java.lang.String
     */
    public static String byteToHex(byte[] inbuf) {

        int i;
        String byteStr;
        StringBuffer strBuf = new StringBuffer();

        for (i = 0; i < inbuf.length; i++) {

            byteStr = Integer.toHexString(inbuf[i] & 0x00ff);
            if (byteStr.length() != 2) {
                strBuf.append('0').append(byteStr);
            } else {
                strBuf.append(byteStr);
            }

        }

        return new String(strBuf);
    }

    /**
     * 将十六进制的字符串转化为二进制的�?
     * Creation date: (00-6-9 17:06:35)
     *
     * @param inbuf byte[]
     * @return java.lang.String
     */
    public static byte[] hexToByte(String inbuf) {
        int len = inbuf.length() / 2;
        byte outbuf[] = new byte[len];

        for (int i = 0; i < len; i++) {
            String tmpbuf = inbuf.substring(i * 2, i * 2 + 2);
            outbuf[i] = (byte) Integer.parseInt(tmpbuf, 16);
        }
        return outbuf;
    }

    /**
     * 取报文长度算法，转换�?4位数字，左补0
     *
     * @param len
     * @return
     */
    public static String getNumstr(int len) {
        StringBuffer length = new StringBuffer();
        String temp = String.valueOf(len);
        for (int i = 0; i < 4 - temp.length(); i++) {
            length.append("0");
        }
        length.append(String.valueOf(len));
        return length.toString();
    }


    /**
     * 判断字符串是否为�?
     * 判断是否�?""时，会将参数字符串行进忽略前导空白和尾部空白处理
     *
     * @param string 输入字符�?
     * @return 如果为null或去除空格后字符串长度为0 返回true
     */
    public static boolean isEmptywithTrim(String string) {
        return (string == null || string.trim().length() == 0);
    }

    /**
     * 判断字符串是否为�??
     * Util.isEmpty(null) = false
     * Util.isEmpty("") = false
     * Util.isEmpty(" ") = true
     * Util.isEmpty("Hello") = true
     *
     * @return 如果为null或去字符串长度为0 返回true
     */
    public static boolean isEmpty(String string) {
        return (string == null || string.length() == 0);
    }

    /**
     * 判断数组是否为空
     *
     * @param array 输入数组
     * @return 如果为null或数组长度为0 返回true
     */
    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 输入集合
     * @return 如果为null或集合为�?? 返回true
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 判断键�?�对是否为空
     *
     * @param map 输入键�?�对
     * @return 如果为null或键值对为空 返回true
     */
    public static boolean isEmpty(Map map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判断IP是IPV6亦或是IPV4
     *
     * @param ip
     * @return 如果为ipv6, 则返回ipv6，否则返回ipv4
     */
    public static String isIpv4OrIpv6(String ip) {
        String[] ips = ip.split("\\.");

        if (ips != null && ips.length == 4) {
            return "ipv4";
        } else {
            return "ipv6";
        }
    }

}
