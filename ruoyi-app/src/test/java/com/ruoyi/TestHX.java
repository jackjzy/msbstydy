package com.ruoyi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ruoyi.app.util.em.Entitie;
import com.ruoyi.app.util.em.HXUtil;
import com.ruoyi.common.utils.jersey.Result;
import com.ruoyi.system.service.IFfwyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.parseObject;
import static com.ruoyi.app.util.em.HXUtil.getRandomString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestHX {

    @Resource
    private HXUtil hxUtil;

    // 对应环信平台Client ID
    @Value("${EN.clientId}")
    private  String CLIENT_ID;
    // 对应环信平台Client Secret
    @Value("${EN.clientSecret}")
    private  String CLIENT_SECRET;
    @Value("${EN.appKey}")
    private  String APP_KEY;

    //获取token
    @Test
    public void testgetToken(){
        hxUtil.setToken();
        System.err.println(hxUtil.getToken());
    }
    //增加用户
    @Test
    public void testAddUser(){

        String phone ="17634010197";
        String password ="123456";
        String res = hxUtil.addUser(phone, password, "海绵宝宝");

        JSONObject entities = parseObject(res).getJSONArray("entities").getJSONObject(0);

        String uuid = entities.getString("uuid");
        System.err.println("uuid"+uuid);

    }

    //获取用户
    @Test
    public void testFandAllUser(){

        String phone ="17634010196";

        Result result = hxUtil.getUser(phone);


        Entitie entitie= JSONObject.parseObject(result.getData().toString(), Entitie.class);
        //JSONObject entities = JSONObject.parseObject(res).getJSONArray("entities").getJSONObject(0);

        //String uuid = entities.getString("uuid");
        System.err.println("entities: "+entitie);

    }

    //添加用户
    @Test
    public void addFirend(){

        String phone ="17634010196";

        Result result = hxUtil.addFirend(phone,"17634010197",null);


        //System.err.println("entities: "+JSONObject.parseObject(result.getData().toString()));

    }

}
