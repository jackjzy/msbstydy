package com.ruoyi;

import com.ruoyi.system.domain.FfwyFeedback;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyFeedbackService;
import com.ruoyi.system.service.IFfwyUserService;
import lombok.ToString;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestFeedBack {

    @Autowired
    private IFfwyFeedbackService iFfwyFeedbackService;
    @Autowired
    IFfwyUserService userService;
    @Test
    public void test1(){


//        List<FfwyFeedback> ffwyFeedbacks = iFfwyFeedbackService.selectFfwyFeedback(null);
//        ffwyFeedbacks.forEach(ffwyFeedback -> {
//            System.err.println(ffwyFeedback);
//        });

    }

}
