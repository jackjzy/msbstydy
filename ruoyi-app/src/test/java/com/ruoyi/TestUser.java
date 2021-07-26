package com.ruoyi;

import com.ruoyi.common.utils.sms.SendSmsUtils;
import com.ruoyi.common.utils.sms.Template;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyUserService;
import com.vdurmont.emoji.EmojiParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestUser {

    @Autowired
    private IFfwyUserService iFfwyUserService;

    @Test
    public void test44(){
        String str = "Here is a boy: :boy|type_6:!";
        System.err.println("原始字符为：\n" + str);

        System.err.println("to aliases 之后：");

        System.err.println(EmojiParser.parseToAliases(str));
        System.err.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.PARSE));
        System.err.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.REMOVE));
        System.err.println(EmojiParser.parseToAliases(str, EmojiParser.FitzpatrickAction.IGNORE));

    }

    @Test
    public void test2(){
        String str="https://ffwy-1300383620.cos.ap-beijing.myqcloud.com/images/2021/07/09/dae55c34-249c-4649-ad28-7e716d645ab8.jpg";
        String images = str.substring(str.indexOf("images"), str.length());
        System.err.println(images);

    }

    @Test
    public void testtoPhone(){

        System.err.println(
                SendSmsUtils.sendsms("13261903163", Template.CODE.getCode(), "888888")
        );

    }
    @Test
    public void test1(){

        FfwyUser ffwyUser = iFfwyUserService.selectFfwyUserById((long) 4);
        System.err.println(ffwyUser);
        FfwyUser ffwyUser1 = iFfwyUserService.selectFfwyUserByToken(ffwyUser.getToken());
        System.err.println(ffwyUser1);
        iFfwyUserService.updateFfwyUser(ffwyUser1);
        iFfwyUserService.insertFfwyUser(ffwyUser1);

    }
}
