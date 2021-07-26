package com.ruoyi.system.domain.to;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombomealTo {
    /** 套餐id */
    private Long combomealId;
    /**
     * 房屋平米数
     */
    private String squareMeter;
    /**
     * 预估价格
     */
    private String price;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 地址
     */
    private String address;

    /**
     * 硬装商品集合
     */
    private List<JSONObject> combomealHards;

    /**
     * 软装商品集合
     */
    private List<JSONObject>  combomealSofts;

    /**
     * 智能家居商品集合
     */
    private List<JSONObject> combomealSmarts;

    /**
     * 家电商品集合
     */
    private List<JSONObject> combomealWirings;

    /**
     * 日用品商品集合
     */
    private List<JSONObject> commodityVos;


}
