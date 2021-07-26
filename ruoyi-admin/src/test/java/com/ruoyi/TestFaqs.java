package com.ruoyi;

import com.ruoyi.system.domain.FfwyFaqs;
import com.ruoyi.system.domain.vo.FaqsVo;
import com.ruoyi.system.domain.vo.FfwyFaqsVo;
import com.ruoyi.system.service.IFfwyFaqsService;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestFaqs {

    @Autowired
    private IFfwyFaqsService iFfwyFaqsService;


    @Test
    public void test1(){
//        iFfwyFaqsService.insertFfwyFaqs(new FaqsVo("张三提出的问题","解决张三提出的问题"));
        List<FfwyFaqs> ffwyFaqs = iFfwyFaqsService.selectFfwyFaqsAndReplyList(null);
        ffwyFaqs.forEach(ffwyFaqs1 -> {
            System.err.println(ffwyFaqs1);
        });
    }
}
