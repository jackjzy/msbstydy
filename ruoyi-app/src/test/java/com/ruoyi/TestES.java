package com.ruoyi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.config.JwtTokenUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.search.FfwyProductSearch;
import com.ruoyi.system.domain.vo.WareSkuLockVo;
import com.ruoyi.system.service.IFfwyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestES {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void test1(){
        try{
            //1:创建索引库    相当于 Mysql中创建数据库
            elasticsearchRestTemplate.createIndex(FfwyProductSearch.class);
            //2:映射索引库   相当于 Mysql中创建表
            elasticsearchRestTemplate.putMapping(FfwyProductSearch.class);
        }catch (Exception e){
            System.err.println(JSONObject.toJSONString(e));
            System.err.println(JSONObject.toJSONString(e).contains("already exists"));
        }
    }


}
