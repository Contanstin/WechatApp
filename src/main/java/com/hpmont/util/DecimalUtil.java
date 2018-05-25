package com.hpmont.util;

/**
 * Created by Administrator on 2016/11/30.
 */
public class DecimalUtil {
    public static Integer str2Integer(String str){
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return 0;
        }

    }

    public static Long str2Long(String str){
        try {
            return Long.parseLong(str);
        } catch (Exception ex) {
            return 0l;
        }

    }

    /**
     * 将数字转换为大写，此方法只支持单位数转换
     * @param indata
     * @return
     */
    public static String int2Str(Integer indata) {
        String[] str = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        if(indata!=null && indata.intValue()<10){
            return str[indata];
        }else{
            return "";
        }
    }
}
