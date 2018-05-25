package com.hpmont.util;

import com.spatial4j.core.io.GeohashUtils;

import java.util.Random;
import java.util.UUID;

/**
 * 公共方法
 * Created by Administrator on 2016/7/14.
 */
public class CommonUtil {
	
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}

	public static String getGeohasCode(Double latitude, Double longitude) {
		if (latitude != null && longitude != null) {
			try {
				return GeohashUtils.encodeLatLon(latitude, longitude);
			} catch (Exception ex) {
				return "";
			}
		} else {
			return "";
		}
	}
	
	/**生成32位的uid*/
	public static String getUid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String getOrderIdByUUId() {
		int machineId = 1;//最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {//有可能是负数
			hashCodeV = -hashCodeV;
		}
		return machineId + String.format("%015d", hashCodeV);
	}
	
}
