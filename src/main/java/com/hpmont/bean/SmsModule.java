package com.hpmont.bean;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.net.URLEncoder;

/*
 * 以下采用云片网提供的API接口实现的发短信功能
 * */

public class SmsModule {
	//查账户信息的http地址
    //private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //智能匹配模版发送接口的http地址(单条发送)
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    //private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";
    
    //发送语音验证码接口的http地址
    //private static String URI_SEND_VOICE = "https://voice.yunpian.com/v2/voice/send.json";//不需要

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
    
    //apiKey。目前使用的是我的测试账号
    //private static String APIKEY="e38783d5fb5e69ab050a4f19c4434cdc";//账户：zyc992@126.com
    private static String APIKEY="552ba66ecdc4654a59bea6ce0b36711f";//账户：zhangyongchao@hpmont.com 密码：hpmontking
    
    /**
	 *给指定的手机号发送短消息
	 * @param 
	 * @return 关于用户代码、产品型号、产品信息、价格、时间、备注的LIST
	 */
    public static String sendMessage(String tempPhoneNumber, String tempMsg)
    {
    	
    	Map tempParams = new HashMap();
    	
    	//添加apiKey
    	tempParams.put("apikey", APIKEY);
    	
    	//添加短信消息内容
    	tempParams.put("text", tempMsg);
    	
    	//添加短信号码
    	tempParams.put("mobile", tempPhoneNumber);
    	
        return post(URI_SEND_SMS, tempParams);
    }
    
    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */
    public static String post(String url, Map paramsMap)
    {
    	CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try 
        {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List tempParamList = new ArrayList();
                Iterator entries = paramsMap.entrySet().iterator();
                while (entries.hasNext()) {  
                	  
                    Map.Entry tempEntry = (Map.Entry) entries.next();
                    NameValuePair tempPair = new BasicNameValuePair(tempEntry.getKey().toString(), tempEntry.getValue().toString());
                    tempParamList.add(tempPair);
                }
                
                method.setEntity(new UrlEncodedFormEntity(tempParamList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) 
            {
                responseText = EntityUtils.toString(entity);
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        } 
        finally 
        {
            try 
            {
                response.close();
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return responseText;
    }
}
