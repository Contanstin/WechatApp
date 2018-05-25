package com.hpmont.util;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/23.
 */
public class ArithUtil {
    private static final int DEF_DIV_SCALE=10;

    /**
     * 加运算
     * @param d1
     * @param d2
     * @return
     */
    public static double add(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 减运算
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 减运算
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal bgdsub(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.subtract(b2);
    }

    /**
     * 乘运算
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 乘运算
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal mul(BigDecimal d1,Integer d2){
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return d1.multiply(b2);
    }

    /**
     * 乘运算
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal bgdmul(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.multiply(b2);
    }

    /**
     * 除运算
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1,double d2){
        return div(d1,d2,DEF_DIV_SCALE);
    }

    /**
     * 除运算
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal bgdiv(double d1,double d2){
        return bgdiv(d1,d2,DEF_DIV_SCALE);
    }

    /**
     * 除运算
     * @param d1
     * @param d2
     * @param scale
     * @return
     */
    public static double div(double d1,double d2,int scale){
        return bgdiv(d1,d2,scale).doubleValue();

    }

    public static BigDecimal bgdiv(double d1,double d2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP);

    }
    /**
     * 字符串转换为double
     * @param strdou
     * @return
     */
    public static Double str2Double(String strdou) {
        try {
            return Double.parseDouble(strdou);
        } catch (Exception ex) {
            return 0d;
        }
    }

    public static Integer str2Integer(String strdou) {
        try {
            return Integer.parseInt(strdou);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static Long str2Long(String strdou) {
        try {
            return Long.parseLong(strdou);
        } catch (Exception ex) {
            return 0l;
        }
    }
}
