package com.hpmont.util;

import com.google.common.base.Strings;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11.
 */
public class HttpClientUtil {
	
	private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	private static PoolingHttpClientConnectionManager connManager = null;
	private static CloseableHttpClient httpclient = null;
	public final static int connectTimeout = 60000;

	static {
		try {
			connManager = new PoolingHttpClientConnectionManager();
			connManager.setMaxTotal(200);// 设置整个连接池最大连接数 根据自己的场景决定
			connManager.setDefaultMaxPerRoute(connManager.getMaxTotal());
			httpclient = HttpClients.custom().setConnectionManager(connManager).build();
		} catch (Exception e) {
			logger.error("NoSuchAlgorithmException", e);
		}
	}

	/**
	 * 发送 post请求
	 * @param params
	 * @return
	 */
	public static String post(Map<String, String> params, String url) {
		String resultStr = "";
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connectTimeout)
				.setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectTimeout).build();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		// 绑定到请求 Entry
		for (Map.Entry<String, String> entry : params.entrySet()) {
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			uefEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(uefEntity);
			httpPost.setConfig(requestConfig);
			logger.info("executing request " + httpPost.getURI());
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			try {
				if (entity != null) {
					resultStr = EntityUtils.toString(entity, "UTF-8");
					logger.info(" httpClient response string " + resultStr);
				}
			} finally {
				response.close();
				if (entity != null) {
					EntityUtils.consume(entity);// 关闭
				}
			}
		} catch (ClientProtocolException e) {
			logger.error("http post error " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("http post error " + e.getMessage());
		} catch (IOException e) {
			logger.error("http post error " + e.getMessage());
		} finally {
			// 关闭连接,释放资源
			httpPost.releaseConnection();
		}
		return resultStr;
	}

	/**
	 * 
	 */
	public static String post(String content, String url, String method, String v) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appKey", "00003");
		params.put("method", method);
		params.put("v", v);
		params.put("content", content);
		params.put("format", "json");
		String sign = SignUtil.sign(new ArrayList<String>(params.keySet()), params,
				"xzse3r3434dsfgvsd445dfg34534534523d");
		params.put("sign", sign);
		return post(params, url);
	}

	/**
	 * 默认超时为5S 发送 post请求
	 * @param content
	 * @param url
	 * @param method
	 * @param v
	 * @param deviceId
	 * @return
	 */
	public static String post(String content, String url,String method,String v,String deviceId) {
		String resultStr = "";
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(connectTimeout)
				.setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(connectTimeout).build();
		Map<String,String> params = new HashMap<String,String>();
		params.put("appKey","00003");
		params.put("method",method);
		params.put("v",v);
		params.put("content",content);
		params.put("format","json");
		String sign = SignUtil.sign(new ArrayList<String>(
				params.keySet()), params, "xzse3r3434dsfgvsd445dfg34534534523d");
		params.put("sign",sign);
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		// 创建参数队列
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		// 绑定到请求 Entry
		for (Map.Entry<String, String> entry : params.entrySet()) {
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			if(!Strings.isNullOrEmpty(deviceId)) {
				httpPost.setHeader("deviceId", deviceId);
			}
			uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			uefEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(uefEntity);
			httpPost.setConfig(requestConfig);
			logger.info("executing request " + httpPost.getURI());
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			try {
				if (entity != null) {
					resultStr = EntityUtils.toString(entity, "UTF-8");
					logger.info(" httpClient response string " + resultStr);
				}
			} finally {
				response.close();
				if (entity != null) {
					EntityUtils.consume(entity);// 关闭
				}
			}
		} catch (ClientProtocolException e) {
			logger.error("http post error " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("http post error " + e.getMessage());
		} catch (IOException e) {
			logger.error("http post error " + e.getMessage());
		} finally {
			// 关闭连接,释放资源
			httpPost.releaseConnection();
		}
		return resultStr;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String get(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送 put请求
	 * @param params
	 * @param header
	 * @return
	 */
	public static String put(Map<String, String> params, Map<String, Object> header, String url) {
		String resultStr = "";
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(connectTimeout)
				.setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(connectTimeout).build();
		// 创建httpPut
		HttpPut httpPut = new HttpPut(url);
		// 创建参数队列
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		// 绑定到请求 Entry
		for (Map.Entry<String, String> entry : params.entrySet()) {
			formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity uefEntity;
		try {
			if( header != null) {
				for(String key : header.keySet()){
					httpPut.setHeader(key, header.get(key).toString());
				}
			}

			uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			uefEntity.setContentType("application/x-www-form-urlencoded");
			httpPut.setEntity(uefEntity);
			httpPut.setConfig(requestConfig);
			logger.info("executing request " + httpPut.getURI());
			CloseableHttpResponse response = httpclient.execute(httpPut);
			HttpEntity entity = response.getEntity();
			try {
				if (entity != null) {
					resultStr = EntityUtils.toString(entity, "UTF-8");
					logger.info(" httpClient response string " + resultStr);
				}
			} finally {
				response.close();
				if (entity != null) {
					EntityUtils.consume(entity);// 关闭
				}
			}
		} catch (ClientProtocolException e) {
			logger.error("http put error " + e.getMessage());
		} catch (UnsupportedEncodingException e) {
			logger.error("http put error " + e.getMessage());
		} catch (IOException e) {
			logger.error("http put error " + e.getMessage());
		} finally {
			// 关闭连接,释放资源
			httpPut.releaseConnection();
		}
		return resultStr;
	}
}
