package com.ruoyi.common.utils.redis;

import com.ruoyi.common.constant.AuthServerConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/6/17
 **/
public class RedisHashUtil {

    public static final String TAG = ":";
//    private static final String key = "key";
//    private static final String hashKey = "hashKey";
//
    public static String getKey(Long userId,Long combomealId ,Integer category){
        return AuthServerConstant.COMBOMAEAL_USER_CACHE_PREFIX + userId + TAG + combomealId + TAG + category;

    }

    public static String getHashKey(String skuId,String skuName,String skuSpec){
        if (StringUtils.isEmpty(skuSpec)){
            return skuId + TAG + skuName;
        }else {
            return skuId + TAG + skuName + "(" +skuSpec+ ")";
        }
    }

}
