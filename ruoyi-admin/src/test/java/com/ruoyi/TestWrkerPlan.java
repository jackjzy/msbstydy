package com.ruoyi;

import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.to.AllocationWokers;
import com.ruoyi.system.service.IFfwyOrderCombomealService;
import com.ruoyi.system.service.IFfwyUserService;
import com.ruoyi.system.service.IFfwyWorkplanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestWrkerPlan {

    @Autowired
    private IFfwyUserService iFfwyUserService;

    @Autowired
    private IFfwyOrderCombomealService iFfwyOrderCombomealService;

    @Autowired
    private IFfwyWorkplanService iFfwyWorkplanService;

    @Test
    public void test2(){
        String str="1:2%/2:52%/3:46%";
        String[] split = str.split("/");
        for (String s : split) {
            System.err.println(s);
            String[] split1 = s.split(":");
            for (String s1 : split1) {

                System.err.println(s1);
            }
        }
    }

    @Test
    public void test(){
        FfwyUser ffwyUser = new FfwyUser();
        ffwyUser.setUserName("赵钱孙");
        FfwyOrderCombomeal ffwyOrderCombomeal = new FfwyOrderCombomeal();
        ffwyOrderCombomeal.setWorkerName("赵钱孙");

        List<FfwyUser> ffwyUsers = iFfwyUserService.selectFfwyUserList(ffwyUser);
        ffwyUsers.forEach(user -> {
            System.err.println(user);
        });
        List<FfwyOrderCombomeal> orderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);
        orderCombomeals.forEach(order ->{
            System.err.println(order);
        });

        AllocationWokers allocationWokers = new AllocationWokers(ffwyUsers,orderCombomeals);
        int allocations = iFfwyWorkplanService.allocations(allocationWokers);
    }
    @Test
    public void test3(){
        FfwyUser ffwyUser = new FfwyUser();
        ffwyUser.setBetweenAge(1);
        toAge(iFfwyUserService.selectFfwyUserList(ffwyUser));
        ffwyUser.setBetweenAge(2);
        toAge(iFfwyUserService.selectFfwyUserList(ffwyUser));
        ffwyUser.setBetweenAge(3);
        toAge(iFfwyUserService.selectFfwyUserList(ffwyUser));
        ffwyUser.setBetweenAge(4);
        toAge(iFfwyUserService.selectFfwyUserList(ffwyUser));
        ffwyUser.setBetweenAge(5);
        toAge(iFfwyUserService.selectFfwyUserList(ffwyUser));

    }
    public void toAge(List<FfwyUser> ffwyUsers){
        for (FfwyUser ffwyUser : ffwyUsers) {
            System.err.print(ffwyUser.getUserId()+"--------"+ffwyUser.getAge()+",");
        }
        System.err.println();
    }
}