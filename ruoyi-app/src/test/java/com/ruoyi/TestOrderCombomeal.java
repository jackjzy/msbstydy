package com.ruoyi;

import com.ruoyi.system.domain.combomealorders.*;
import com.ruoyi.system.service.IFfwyMaterialService;
import com.ruoyi.system.service.IFfwyOrderCombomealService;
import com.ruoyi.system.service.IFfwyPhaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestOrderCombomeal {

    @Autowired
    IFfwyOrderCombomealService iFfwyOrderCombomealService;
    @Autowired
    FfwyOrderCombomeal ffwyOrderCombomeal;

    @Autowired
    IFfwyPhaseService iFfwyPhaseService;
    @Autowired
    IFfwyMaterialService iFfwyMaterialService;


    @Test
    public void test10(){
        FfwyOrderCombomeal ffwyOrderCombomeal = iFfwyOrderCombomealService.selectFfwyOrderCombomealById((long) 1);
        List<FfwyMaterial> ffwyMaterials = iFfwyMaterialService.selectFfwyMaterialList(null);
        FfwyPhase ffwyPhase = new FfwyPhase();
        ffwyPhase.setOrderId((long) 1);
        List<FfwyPhase> ffwyPhases = iFfwyPhaseService.selectFfwParent(ffwyPhase);
        ffwyPhases.forEach(ffwyPhase1 -> {
            System.err.println("父节点");
            System.err.println(ffwyPhase1);
            List<FfwyPhase> ffwyPhaseList = ffwyPhase1.getFfwyPhaseList();
            ffwyPhaseList.forEach(list -> {
                System.err.println("子节点");
                System.err.println(list);
            });
        });
//        Materials materials = new Materials(ffwyMaterials);
//        Phases phases = new Phases(ffwyPhases);
//        iFfwyOrderCombomealService.insertFfwyOrder(ffwyOrderCombomeal,materials,phases);

    }

    @Test
    public void test1(){
        FfwyOrderCombomeal ffwyOrderCombomeal = iFfwyOrderCombomealService.selectFfwyOrderCombomealById((long) 1);
        System.err.println(ffwyOrderCombomeal);
        iFfwyOrderCombomealService.insertFfwyOrderCombomeal(ffwyOrderCombomeal);
        System.err.println(ffwyOrderCombomeal.getOrderId());
    }

    @Test
    public void test2(){
        ffwyOrderCombomeal.setWorkerId((long) 5);
        ffwyOrderCombomeal.setOrderType("0");
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);
        ffwyOrderCombomeals.forEach(ffwyOrderCombomeal1 -> {
            System.err.println(ffwyOrderCombomeal1);
        });

    }
    @Test
    public void test3(){
        ffwyOrderCombomeal.setSearchStr("张三丰");
        List<FfwyOrderCombomeal> ffwyOrderCombomeals = iFfwyOrderCombomealService.selectFfwyOrderCombomealList(ffwyOrderCombomeal);
        ffwyOrderCombomeals.forEach(ffwyOrderCombomeal1 -> {
            System.err.println(ffwyOrderCombomeal1);
        });
    }
}
