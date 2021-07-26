package com.ruoyi;

import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.combomealorders.FfwyPhase;
import com.ruoyi.system.mapper.combomealorders.FfwyPhaseMapper;
import com.ruoyi.system.service.IFfwyMaterialService;
import com.ruoyi.system.service.IFfwyPhaseService;
import com.ruoyi.system.service.IFfwyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestPhase {
    @Autowired
    private IFfwyPhaseService iFfwyPhaseService;
    @Autowired
    private IFfwyUserService iFfwyUserService;

    @Autowired
    private IFfwyMaterialService iFfwyMaterialService;

    @Autowired
    private FfwyPhaseMapper ffwyPhaseMapper;

    @Autowired
    private FfwyMaterial ffwyMaterial;

    @Autowired
    private FfwyPhase ffwyPhase;


    @Test
    public void test2(){
        ffwyMaterial.setOrderCombomealId((long) 6);
        ffwyMaterial.setTagId(1);
        List<FfwyMaterial> ffwyMaterials = iFfwyMaterialService.selectFfwyMaterialList(ffwyMaterial);
        for (FfwyMaterial material : ffwyMaterials) {
            System.err.println(material);
        }

    }

    @Test
    public void test1(){
        FfwyPhase ffwyPhase = new FfwyPhase();
        ffwyPhase.setOrderId((long) 1);
        List<FfwyPhase> ffwyPhases = iFfwyPhaseService.selectFfwParent(ffwyPhase);
//        ffwyPhases.forEach(ffwyPhase1 -> {
//
//            FfwyPhase Phase2 = new FfwyPhase();
//            Phase2.setOrderId((long) 1);
//            Phase2.setParentId(ffwyPhase1.getPhaseId());
//
//            List<FfwyPhase> ffwyPhases1 = iFfwyPhaseService.selectFfwyPhaseList(Phase2);
//            ffwyPhase1.setFfwyPhaseList(ffwyPhases1);
//
//
//        });
        ffwyPhases.forEach(ffwyPhase1 -> {
            System.out.println("父节点");
            System.out.println(ffwyPhase1);
            List<FfwyPhase> list = ffwyPhase1.getFfwyPhaseList();
            list.forEach(ffwyPhase2 -> {
                System.out.println("子节点");
                System.out.println(ffwyPhase2);
            });
        });

    }
}
