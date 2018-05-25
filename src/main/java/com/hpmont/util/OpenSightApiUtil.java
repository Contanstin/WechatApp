package com.hpmont.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * 调用趣录播接口
 */
public class OpenSightApiUtil {

    private final static String ISS="OnMot_RYtLeRGs0w2qVB";
    private final static String SECRET="qaYWZezo5XquFj9ewf5dEly0k_XgcXaFIHdgVy6h";
    private final static String AUD ="liuhong";
    private final static String UUID = "uaqaU5BXRRSoSnW4X5BmIA";
    private final static String PROJECT_NAME = "zhangxinjiaoyu";

    /**
     * 获取趣录播服务器Token、不安全、测试使用
     */
    public static String getToken(){
        Map<String, String> param = new HashedMap();
        param.put("secret",SECRET);
        param.put("iss",ISS);
        param.put("aud",AUD);
        param.put("exp", String.valueOf(OpenSightApiUtil.getTimestamp()+3600));
        String rs = HttpClientUtil.post(param,"http://api.opensight.cn/api/ivc/v1/jwts");
        Map<String, Object> ar  = (Map)JSON.parseObject(rs,Map.class);
        if(ar!=null){
            return "Bearer " + (String)ar.get("jwt");
        }else{
            return null;
        }
    }

    /**
     * 获取趣录播服务器 时间戳
     */
    public static Integer getTimestamp(){
        String rs =  HttpClientUtil.get("http://api.opensight.cn/api/ivc/v1/server_timestamp",null);
        Map<String, Object> ar  = (Map)JSON.parseObject(rs,Map.class);
        if(ar!=null){
            return (Integer)ar.get("now");
        }else{
            return null;
        }
    }

    /**
     * 设置推流地址（推到腾讯云）
     */
    public static void setRtmpPublish(String pushUrl){
        Map<String, String> params = new HashedMap();
        Map<String, Object> header = new HashedMap();
        params.put("enable","true");
        params.put("quality","hd");
        params.put("url",pushUrl);
        header.put("Authorization",OpenSightApiUtil.getToken());
        String rs =  HttpClientUtil.put(params,header,"http://api.opensight.cn/api/ivc/v1"+
                "/projects/"+PROJECT_NAME+"/cameras/"+UUID+"/remote_config/rtmp_publish");
    }

}
