package com.hpmont.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28.
 */
public class SignUtil {
    public static String sign(List<String> paramNames, Map<String, String> paramValues, String secret) {
        StringBuilder sb = new StringBuilder();
        Collections.sort(paramNames);
        sb.append(secret);
        for (String paramName : paramNames) {
            sb.append(paramName).append(paramValues.get(paramName));
        }
        sb.append(secret);
        return CodeGenerator.getSHADigest(sb.toString()).toUpperCase();
    }
}
