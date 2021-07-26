package com.ruoyi.common.utils.iosLogin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwk.Jwk;
import io.jsonwebtoken.*;
import lombok.SneakyThrows;
import okhttp3.internal.http2.ErrorCode;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Map;

public class AppleUtil {

    private static final Logger logger = LoggerFactory.getLogger(AppleUtil.class);
    private final static String APPLE_AUTH_URL = "https://appleid.apple.com/auth/keys";

    private final static String ISS = "https://appleid.apple.com";
    public static boolean verify(JSONObject body, JSONObject result) {
        //这里传过来的identityToken应该是三个.分割,解密之后
        String identityToken2 = body.getString("identityToken");
        String  identityToken = new String(Base64.decodeBase64(identityToken2));
        try {
            if (identityToken.split("\\.").length > 1){
                String firstDate = new String( Base64.decodeBase64(identityToken.split("\\.")[0]),"UTF-8");
                String claim = new String(Base64.decodeBase64(identityToken.split("\\.")[1]), "UTF-8");
                String kid = JSONObject.parseObject(firstDate).get("kid").toString();
                String aud = JSONObject.parseObject(claim).get("aud").toString();
                String sub = JSONObject.parseObject(claim).get("sub").toString();
                PublicKey publicKey = getPublicKey(kid);
                if (publicKey == null) {
                    result.put("description", "Apple have no info data!");
                    return false;
                }
                boolean reuslt = verify(publicKey, identityToken, aud, sub, result);
                if (reuslt) {
                    logger.info("苹果登录授权成功");
                    return true;
                }
            }
        } catch (Exception e) {

            logger.error("苹果登录授权异常", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    private static PublicKey getPublicKey(String kid) {
        try {
            JSONObject debugInfo = getHttp(APPLE_AUTH_URL);
            if (debugInfo == null) {
                return null;
            }
            JSONObject jsonObject = debugInfo.getJSONObject("body");
            String keys = jsonObject.getString("keys");
            JSONArray jsonArray = JSONObject.parseArray(keys);
            if (jsonArray.isEmpty()) {
                return null;
            }
            for (Object object : jsonArray) {
                JSONObject json = ((JSONObject) object);
                if (json.getString("kid").equals(kid)) {
                    String n = json.getString("n");
                    String e = json.getString("e");
                    BigInteger modulus = new BigInteger(1, Base64.decodeBase64(n));
                    BigInteger publicExponent = new BigInteger(1, Base64.decodeBase64(e));
                    RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
                    KeyFactory kf = KeyFactory.getInstance("RSA");
                    return kf.generatePublic(spec);
                }
            }
        } catch (Exception e) {
            logger.error("getPublicKey异常!", e.getMessage());
            e.printStackTrace();
        }
        return null;

    }
    private static boolean verify(PublicKey key, String jwt, String audience, String subject, JSONObject resultJson) throws Exception {
        boolean result = false;
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        jwtParser.requireIssuer(ISS);
        jwtParser.requireAudience(audience);
        jwtParser.requireSubject(subject);
        try {
            Jws<Claims> claim = jwtParser.parseClaimsJws(jwt);
            if (claim != null && claim.getBody().containsKey("auth_time")) {
                return true;
            }
        } catch (ExpiredJwtException e) {
            resultJson.put("description", "Apple identityToken expired");
            logger.error("getPublicKey异常{苹果identityToken过期}", e.getMessage());
        } catch (SignatureException e) {
            resultJson.put("description", "Apple identityToken expired");
            logger.error("getPublicKey异常{苹果identityToken非法}", e.getMessage());

        }
        return result;
    }
    private static JSONObject getHttp(String url) {
        logger.info("[请求地址]： " + url);
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", -1);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpPost = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {

                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getStatusCode() == 200) {
                    String resp = EntityUtils.toString(entity);
                    logger.info("[返回内容]： " + resp);
                    resultJson.put("code", 0);
                    resultJson.put("body", JSONObject.parseObject(resp));
                } else {
                    resultJson.put("code", response.getStatusLine().getStatusCode());
                    logger.error("[错误码] ：" + response.getStatusLine().getStatusCode());
                    logger.error("[请求地址] ：" + url);
                }

            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            logger.error("[异常] :" + e);
        } catch (IOException e) {
            logger.error("[异常] :" + e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("[httpclient 关闭异常] ： " + e);
            }
        }
        return resultJson;
    }

    @SneakyThrows
    public static void main(String[] args) {
        String identityToken="ZXlKcmFXUWlPaUpsV0dGMWJtMU1JaXdpWVd4bklqb2lVbE15TlRZaWZRLmV5SnBjM01pT2lKb2RIUndjem92TDJGd2NHeGxhV1F1WVhCd2JHVXVZMjl0SWl3aVlYVmtJam9pWTI5dExubHBibWR4ZFM1VGFXZHVTVzVYYVhSb1FYQndiR1V4TWlJc0ltVjRjQ0k2TVRZd01qUXdNRGd6TWl3aWFXRjBJam94TmpBeU16RTBORE15TENKemRXSWlPaUl3TURFeU1EZ3VNRGd3WmpCbVptWmlZVEZrTkdJNU9XRXhaV1V6WXpkaE1qaGxaRE0zTTJRdU1ESXdNQ0lzSW1OZmFHRnphQ0k2SW0wdGNrWnRjekJoU0d0cFVHeEZVMFZ0VjJVNWMyY2lMQ0psYldGcGJDSTZJbmRoYm0xMVgyaGxRSE5rYldOMFpXTm9MbU52YlNJc0ltVnRZV2xzWDNabGNtbG1hV1ZrSWpvaWRISjFaU0lzSW1GMWRHaGZkR2x0WlNJNk1UWXdNak14TkRRek1pd2libTl1WTJWZmMzVndjRzl5ZEdWa0lqcDBjblZsZlEuSlg2NjViRmxabXRacjY2czNxNlV6UE1qdW4yZmxpd21nUUlFY01SeHR3SmRMUFJ6Rk9SVlVRTS1DTWR2T2lvSEFoMTFVVWV1Nzc3YTBCdTlhQlJBQVpJNUVqWEE5dVI4cjQyTjljV3FmaWdMQUt3RUtjVnBJdXNRMXNrb1JZUWgtTldxSnc1eFNBc0N0TXFmbE5tcVZ5QnE0a3BoQVAwaV95Q1hYTlFBZ1NzYjRIUUVMU29Kb1VGUndXTFM4WldkZy1hMXJ4cUs3OHEyUXZBUkJhNURuYVUxMXpZZ3BiYWRIWHdGVEtuaTFINjBpeHBsYjRzM1E4M2w4SGZiTVJhWW5OQ1lVUXY1OWc1SGloeUpBZERTS3I1M1laYkpvcDZIRUpvc3hIU2U2ejM4eW4yVTU5MmJIN0FGcVJjd0R4ejRPZTBhUkdtcHZOZFpWVkJGQjN4OU13";
        //验证identityToken
        JSONObject jsonObject = new JSONObject();jsonObject.put("identityToken",identityToken);


        System.err.println(AppleUtil.verify(jsonObject, new JSONObject()));


    }
}
