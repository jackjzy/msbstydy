package com.ruoyi;

import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.product.FfwyProductCommentPhoto;
import com.ruoyi.system.service.IFfwyProductCommentPhotoService;
import com.ruoyi.system.service.IFfwyProductCommentService;
import com.ruoyi.system.service.IFfwyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestProductComment {

    @Autowired
    private IFfwyProductCommentService iFfwyProductCommentService;

    @Autowired
    private IFfwyProductCommentPhotoService iFfwyProductCommentPhotoService;
    @Autowired
    private IFfwyUserService iFfwyUserService;



    @Test
    public void test4(){
        FfwyProductComment comment = iFfwyProductCommentService.selectFfwyProductCommentById((long) 1);
        System.err.println(comment.getComment());
        FfwyUser ffwyUser = iFfwyUserService.selectFfwyUserById((long) 4);
        ffwyUser.setUserName(comment.getComment());
        iFfwyUserService.updateFfwyUser(ffwyUser);
    }

    @Test
    public void test2(){

        FfwyProductComment ffwyProductComment1 = new FfwyProductComment();
        FfwyProduct ffwyProduct = new FfwyProduct();
        ffwyProduct.setShopId((long) 4);
        ffwyProductComment1.setFfwyProduct(ffwyProduct);
        List<FfwyProductComment> ffwyProductComments = iFfwyProductCommentService.selectShopComment(ffwyProductComment1);
        ffwyProductComments.forEach(ffwyProductComment -> {
            System.err.println(ffwyProductComment);
        });
    }

    @Test
    public void test3(){
        FfwyProductComment comment = new FfwyProductComment();
        FfwyProduct ffwyProduct = new FfwyProduct();
        ffwyProduct.setShopId((long) 4);
        comment.setFfwyProduct(ffwyProduct);
        List<FfwyProductComment> ffwyProductComments = iFfwyProductCommentService.selectShopComment(comment);
        ffwyProductComments.forEach(ffwyProductComment -> {
            System.err.println(ffwyProductComment);
        });

    }
}
