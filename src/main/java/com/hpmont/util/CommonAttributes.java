package com.hpmont.util;

/**
 * Created by Administrator on 2016/6/8.
 * 公共参数
 *
 * @version 1.0
 */
public final class CommonAttributes {

    /** 日期格式配比 */
    public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

    /** easyshopping.xml文件路径 */
    public static final String SHOP_XML_PATH = "/edu.xml";

    /** easyshopping.properties文件路径 */
    public static final String SHOP_PROPERTIES_PATH = "/edu.properties";

    /**
     * 不可实例化
     */
    private CommonAttributes() {
    }

}
