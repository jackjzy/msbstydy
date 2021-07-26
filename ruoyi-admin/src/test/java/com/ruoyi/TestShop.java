package com.ruoyi;

import com.ruoyi.system.domain.product.FfwyProductPhoto;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.shop.FfwyShopPhoto;
import com.ruoyi.system.mapper.product.FfwyProductPhotoMapper;
import com.ruoyi.system.service.IFfwyShopPhotoService;
import com.ruoyi.system.service.IFfwyShopService;
import com.ruoyi.web.controller.product.FfwyProductPhotoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestShop {

    @Autowired
    FfwyShop ffwyShop;
    @Autowired
    IFfwyShopService iFfwyShopService;
    @Autowired
    IFfwyShopPhotoService iFfwyShopPhotoService;
//    @Autowired
//    private FfwyProductPhotoMapper ffwyProductPhotoMapper;

    @Test
    public void test2()
    {
        List<FfwyShopPhoto> list = iFfwyShopPhotoService.selectFfwyShopPhotoList(null);
        for (FfwyShopPhoto ffwyShopPhoto : list) {
            System.out.println(ffwyShopPhoto);
        }
        iFfwyShopPhotoService.insertFfwyShopPhotos(list);
    }

    @Test
    public void test1()
    {
        FfwyProductPhoto productPhoto = new FfwyProductPhoto(2L,"dsqcasq",1L,"2");
        //ffwyProductPhotoMapper.insertFfwyProductPhoto(productPhoto);
    }
    @Test
    public void test3(){


    }
}
