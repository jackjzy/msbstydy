package com.ruoyi.common.utils.ucpaasSms;

import cn.jiguang.common.utils.Preconditions;
import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.lang.Strings;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: shihuajun
 * @Date: 2019/3/6 11:12
 * @Description
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger("HttpUtil");
    /**
     * http get 请求。
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        return httpGet(url, -1);
    }

    /**
     * http get 请求。
     *
     * @param url
     * @return
     */
    public static String httpGet(String url, int timeOut) {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            RequestConfig.Builder defaultRequestConfigBuilder = RequestConfig.custom();

            if (timeOut == -1) {
                timeOut = 3000;
            }

            defaultRequestConfigBuilder
                    .setConnectTimeout(timeOut)
                    .setConnectionRequestTimeout(timeOut);

            RequestConfig defaultRequestConfig = defaultRequestConfigBuilder.build();

            // 创建httpget.
            HttpGet httpget = new HttpGet(url);

            httpget.setConfig(defaultRequestConfig);

            logger.debug("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);

            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                logger.debug("--------------------------------------");
                // 打印响应状态
                logger.debug(response.getStatusLine().getReasonPhrase());
                if (entity != null) {
                    result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error("url:{}，ex:{}，errorInfos:",url,e.getMessage(),e);
        } catch (IOException e) {
            logger.error("url:{}，ex:{}，errorInfos:",url,e.getMessage(),e);
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("url:{}，ex:{}，errorInfos:",url,e.getMessage(),e);
            }
        }
        return result;
    }

    /**
     * http post 请求。
     *
     * @param url
     * @param postData
     * @return
     */
    public static String httpPost(String url, Map<String, Object> postData) {
        return httpPost(url, postData, null);
    }

    /**
     * http post 请求。
     *
     * @param url
     * @param postData
     * @return
     */
    public static String httpPost(String url, Map<String, Object> postData, Header[] headers) {
        return httpPost(url, postData, headers,-1);
    }

    public static String httpPost(String url, Map<String, Object> postData, Header[] headers,int timeOut){

        //Preconditions.checkArgument(!Strings.isNullOrEmpty(url), "url is null ~!");

        Preconditions.checkArgument(postData != null, "postData is null ~!");

        String result = null;

        RequestConfig.Builder defaultRequestConfigBuilder = RequestConfig.custom();

        if (timeOut == -1) {
            timeOut = 3000;
        }

        defaultRequestConfigBuilder
                .setConnectTimeout(timeOut)
                .setConnectionRequestTimeout(timeOut);

        RequestConfig defaultRequestConfig = defaultRequestConfigBuilder.build();

        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(url);

        httppost.setConfig(defaultRequestConfig);

        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        for (Map.Entry<String, Object> entry : postData.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);

            if (headers != null && headers.length > 0) {
                httppost.setHeaders(headers);
            }

            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, "UTF-8");
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error(e.getMessage(), e);
        } catch (UnsupportedEncodingException e1) {
            logger.error(e1.getMessage(), e1);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return result;
    }

    public static String httpPostJson(String url, JSONObject json) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
            StringEntity entity = new StringEntity(json.toString(),"UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);

            CloseableHttpResponse response = client.execute(post);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(response.getEntity());
                }
            } finally {
                response.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
