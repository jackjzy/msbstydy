package com.ruoyi;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.app.config.JwtTokenUtil;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.vo.WareSkuLockVo;
import com.ruoyi.system.service.IFfwyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestJwt {
    String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IuaUvuWBhyIsImV4cCI6MTYyNDM1MDU1OSwidXNlcklkIjoiNCJ9.aEVT2tHRsfJxZ8k2Vk5DRtGeozgjPkVAn2u14-B4G_s";
    @Autowired
    IFfwyUserService userService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test1(){
        FfwyUser user = userService.selectFfwyUserById((long) 4);
        String appToke = JwtTokenUtil.getAppToke(user);
        System.err.println(appToke);
        Long id = JWTUtils.getId(token);
        System.err.println("解压后的："+id);

//        System.out.println(toke);

//        Long id = JWTUtils.getId(toke);
//        System.err.println(id);
    }

    @Test
    public void test2(){
        WareSkuLockVo lockVo = new WareSkuLockVo();
        lockVo.setOrderSn("12345ftghj677");

        List<FfwyMaterial> list = new ArrayList<>();
        FfwyMaterial ffwyMaterial = new FfwyMaterial();
        ffwyMaterial.setMaterialName("dcfghj");
        list.add(ffwyMaterial);
        lockVo.setComLocks(list);

        //给MQ发送消息
        rabbitTemplate.convertAndSend("order-event-exchange","order.create.order", JSONObject.toJSON(lockVo));

    }


    public static void main(String[] args) {
        String date ="{\"total\":1,\"rows\":[{\"afterSaleid\":1,\"afterType\":\"退货退款\",\"orderNumber\":\"27197119171231\",\"problemDescription\":\"与图片不符合，货物质量差\",\"afterStatus\":null,\"createTime\":\"2021-06-18T15:41:39.000+08:00\",\"merchantLeave\":null,\"kddh\":null,\"kdgs\":null,\"ffwyAfterStatuses\":[{\"searchValue\":null,\"createBy\":null,\"createTime\":\"2021-06-28T14:29:05.000+08:00\",\"updateBy\":null,\"updateTime\":null,\"remark\":null,\"beginTime\":null,\"endTime\":null,\"params\":{},\"statusId\":null,\"saleId\":null,\"auditStatusId\":null,\"auditName\":\"审核中\"}],\"phone\":\"1231141\"}],\"code\":200,\"msg\":\"查询成功\"}";

        JSONObject jsonObject = JSONObject.parseObject(date);
        System.err.println(jsonObject);
//        Entity_WaitAfter_Details entity_waitAfter_details = JSONObject.parseObject(date,Entity_WaitAfter_Details.class);
//
//        Entity_WaitAfter_Details.RowsBean rowsBean = entity_waitAfter_details.getRows().get(0);
//        if (rowsBean != null){
//            System.err.println("++++++++++"+rowsBean);
//        }
//        System.err.println("------"+rowsBean);

    }


}
