package com.bzj.java.utils.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 *
 * @author aaronbai
 * @create 2018-03-01 18:33
 **/
public class HTTPUtil {

    private static final String DEFAULT_CHARSET = "UTF-8";// 默认请求编码
    private static final int DEFAULT_SOCKET_TIMEOUT = 5000;// 默认等待响应时间(毫秒)
    private static final int DEFAULT_RETRY_TIMES = 0;// 默认执行重试的次数

    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(1000)
            .setConnectTimeout(1000)
            .setConnectionRequestTimeout(5000)
            .build();


    /**
     * URL 转义
     *
     * @param url
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String uelTransferred(String url) throws UnsupportedEncodingException {
        return URLEncoder.encode(url, DEFAULT_CHARSET);
    }

    /**
     * 获取代理client
     *
     * @param host
     * @param port
     * @return
     */
    public CloseableHttpClient getProxyClient(String host, int port) {
        HttpHost proxy = new HttpHost(host, port);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setRoutePlanner(routePlanner)
                .build();
        return httpclient;
    }


    /**
     * get 请求
     *
     * @param url
     * @param client
     * @param parameters
     * @return
     * @throws IOException
     */
    public String doGet(String url, CloseableHttpClient client, Map<String, String> parameters) throws IOException {
        if (client == null) {
            client = HttpClients.createDefault();
        }
        if (parameters != null) {
            StringBuilder buder = new StringBuilder(url);
            buder.append("?");
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                buder.append(entry.getKey());
                buder.append("=");
                buder.append(entry.getValue());
                buder.append("&");
            }
            buder.deleteCharAt(buder.lastIndexOf("&"));
            url = buder.toString();
        }
        HttpGet get = new HttpGet(url);
        get.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }
        } finally {
            client.close();
        }

        return null;
    }

    /**
     * post 请求
     *
     * @param url
     * @param client
     * @param parameters
     * @return
     * @throws UnsupportedEncodingException
     */
    public String doPost(String url, CloseableHttpClient client, Map<String, String> parameters) throws IOException {
        if (client == null) {
            client = HttpClients.createDefault();
        }
        HttpPost post = new HttpPost(url);
        post.setConfig(requestConfig);
        try {
            if (parameters != null) {
                List<NameValuePair> naps = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    naps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                post.setEntity(new UrlEncodedFormEntity(naps));
            }
            CloseableHttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }
        } finally {
            client.close();
        }
        return null;
    }
}