package com.ruoyi;

import com.ruoyi.system.domain.aftersale.FfwyAfterPhoto;
import com.ruoyi.system.domain.aftersale.FfwyAfterSale;
import com.ruoyi.system.domain.product.FfwyProductCategory;
import com.ruoyi.system.service.IFfwyAfterPhotoService;
import com.ruoyi.system.service.IFfwyAfterSaleService;
import com.ruoyi.system.service.IFfwyProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestAFterSale {

    @Autowired
    private IFfwyAfterSaleService iFfwyAfterSaleService;

    @Autowired
    private IFfwyProductCategoryService ffwyProductCategoryService;
    @Autowired
    private IFfwyAfterPhotoService iFfwyAfterPhotoService;
    @Test
    public void text1(){
        FfwyAfterSale ffwyAfterSale = iFfwyAfterSaleService.selectByOrderId((long) 1);
        System.err.println(ffwyAfterSale);
        List<FfwyAfterPhoto> photos = ffwyAfterSale.getPhotos();
        for (FfwyAfterPhoto photo : photos) {
            System.out.println(photo);
        }

    }

    @Test
    public void test2(){

        List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryService.selectFfwyProductCategoryList(null);
        System.err.println(ffwyProductCategories);
    }

}
