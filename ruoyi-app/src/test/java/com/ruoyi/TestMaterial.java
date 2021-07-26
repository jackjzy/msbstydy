package com.ruoyi;

import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.service.IFfwyMaterialService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiApplication.class})
public class TestMaterial {

    @Autowired
    private IFfwyMaterialService iFfwyMaterialService;

    @Test
    public void test1(){
        FfwyMaterial ffwyMaterial1 = new FfwyMaterial();
        ffwyMaterial1.setOrderCombomealId((long) 7);
        FfwyProductSku ffwyProductSku = new FfwyProductSku();
        ffwyProductSku.setCatalogId((long) 6);
        ffwyMaterial1.setSku(ffwyProductSku);
        List<FfwyMaterial> ffwyMaterials = iFfwyMaterialService.selectFfwyMaterialList(ffwyMaterial1);
        ffwyMaterials.forEach(ffwyMaterial -> {
            System.err.println(ffwyMaterial);
        });
    }
}
