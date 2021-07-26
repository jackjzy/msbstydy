package com.ruoyi;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.back.service.IFfwyInformationCommentService;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyConsigneeAddrService;
import com.ruoyi.system.service.IFfwyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.text.ParseException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class Tests {
    @Autowired
    IFfwyInformationCommentService iFfwyInformationCommentService;
    @Autowired
    IFfwyUserService iFfwyUserService;
    @Autowired
    IFfwyConsigneeAddrService iFfwyConsigneeAddrService;
    String path="C:\\Users\\Administrator\\Desktop\\疑难杂症\\爱大家说对啦会加大花时间阿达撒啊啊啊啊啊是收到货就爱好家.doc";
    @Test
    public void Test1() throws ParseException {


    }








}
