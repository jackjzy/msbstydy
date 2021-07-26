package com.ruoyi.common.utils.jersey;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.*;

/**
 * JerseyAPi客户端
 */
public class JerseyClientUtil {


    /**
     * post方法
     *
     * @param url  链接
     * @param method 方法名
     * @param param  参数
     * @return 返回值
     */
    public static Result postMethod(String url,String method, String param,Map<String, Object> headerParam) {
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(url + method);
            Set<String> keys = headerParam.keySet();
            for (String key : keys) {
                resource.header(key, headerParam.get(key));
            }
            response = resource.type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, param);
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            if (status == 200) {
                return Result.ok(data);
            } else {
                return Result.build(status,data);
            }
        } catch (Exception e) {
            return Result.build(500,e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * post方法
     *
     * @param url  链接
     * @param method 方法名
     * @return 返回值
     */
    public static Result postMethod(String url,String method,Map<String, Object> headerParam) {
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(url + method);
            String key="Authorization";

            response = resource.header(key,headerParam.get(key)).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class);

            int status = response.getStatus();
            String data = response.getEntity(String.class);
            if (status == 200) {
                return Result.ok(data);
            } else {
                return Result.build(status,data);
            }
        } catch (Exception e) {
            return Result.build(500,e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


    /**
     * get方法
     * 例如：consultation/recommend?startDate=201412030253&endDate=201412020253
     * @param url  链接
     * @param method 方法名
     * @param param  参数
     * @return 返回值
     */
    public static Result getMethod(String url,String method, String param) {
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(url + method);
            response = resource.type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);
            int status = response.getStatus();
            String data = response.getEntity(String.class);
            if (status == 200) {
                return Result.ok(data);
            } else {
                return Result.build(status,data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500,e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * get方法 仅适用于环信,传入header的
     * 例如：consultation/recommend/A1000037B04B8C
     * @param url  链接
     * @param method 方法名
     * @return 返回值
     */
    public static Result getMethodOnIm(String url,String method,Map<String, Object> headerParam) {
        ClientResponse response = null;
        try {
            Client client = Client.create();
            WebResource resource = client.resource(url + method);

            String key="Authorization";

            response = resource.header(key,headerParam.get(key)).type(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

            int status = response.getStatus();
            String data = response.getEntity(String.class);
            if (status == 200) {
                return Result.ok(data);
            } else {
                return Result.build(status,data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(500,e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    public static MultivaluedMap parseJSON2Map(String jsonStr) {
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        //最外层解析
        JSONObject json = JSON.parseObject(jsonStr);
        for (Map.Entry<String, Object> entry : json.entrySet()) {
            Object v = entry.getValue();
            //如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Iterator<Object> it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = (JSONObject) it.next();
                    list.add(parseJSON2Map(json2.toJSONString()));
                }
                queryParams.add(entry.getKey(), list);
            } else {
                queryParams.add(entry.getKey(), v);
            }
        }
        return queryParams;
    }


    public static void main(String[] args) {

//        AjaxResult AjaxResult = postMethod("bfr/bfr_choices", "{\"userid\":\"00004\",\"createTime\":\"2014-09-23 16:19:23\",\"bmiScore\":\"80\",\"imageNum\":\"01\",\"type\":\"0\",\" info \":\"个人身体质量分析正常\"}");
//        AjaxResult AjaxResult = getMethod("recommendInfo/query", "{\"endDate\":\"201412020253\",\"startDate\":\"201410010253\"}");
////        AjaxResult AjaxResult = getMethodOnly("consultation/recommend/", "A1000037B04B8C");
//        System.out.println(AjaxResult.getStatus());
//        System.out.println(AjaxResult.getErrorMsg());

    }
}