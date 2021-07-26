package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.product.FfwyProductCommentPhoto;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.withdrawdeposit.FfwyApplicaWithdrawal;
import com.ruoyi.system.mapper.FfwySpecificationMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.product.FfwyProductCommentMapper;
import com.ruoyi.system.mapper.product.FfwyProductCommentPhotoMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import com.ruoyi.system.mapper.withdrawdeposit.FfwyApplicaWithdrawalMapper;
import com.ruoyi.system.service.IFfwyApplicaWithdrawalService;
import com.ruoyi.system.service.IFfwyProductCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品评论Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@Service
public class FfwyApplicaWithdrawalServiceImpl implements IFfwyApplicaWithdrawalService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FfwyApplicaWithdrawalServiceImpl.class);


    @Autowired
    private FfwyApplicaWithdrawalMapper ffwyApplicaWithdrawalMapper;


    @Autowired
    private FfwyProductCommentMapper ffwyProductCommentMapper;

    @Autowired
    private FfwyProductCommentPhotoMapper ffwyProductCommentPhotoMapper;

    @Autowired
    private FfwyShopMapper ffwyShopMapper;

    @Autowired
    private FfwyProductMapper ffwyProductMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;
    @Autowired
    private FfwySpecificationMapper ffwySpecificationMapper;

    /**
     * 查询商品评论
     *
     * @param productCommentId 商品评论ID
     * @return 商品评论
     */
    @Override
    public FfwyProductComment selectFfwyProductCommentById(Long productCommentId) {
        return ffwyProductCommentMapper.selectFfwyProductCommentById(productCommentId);
    }

    /**
     * 根据店主查询商品评论
     *
     * @param userId 商品评论ID
     * @return 商品评论
     */
    @Override
    public List<FfwyProductComment> selectUserId(Long userId, Long shopId, Long productId,
                                                 Long combomealId) {
        if (null != productId) {
            return this.selectFfwyProductCommentList(new FfwyProductComment(productId));
        }

        List<FfwyProductComment> ffwyProductCommentList = new ArrayList<>();
        List<FfwyShop> ffwyShops = ffwyShopMapper.selectFfwyShopList(new FfwyShop(userId, shopId));
        for (FfwyShop ffwyShop : ffwyShops) {
            List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyProductList(new FfwyProduct(ffwyShop.getShopId()));
            for (FfwyProduct ffwyProduct : ffwyProducts) {
                ffwyProductCommentList.addAll(this.selectFfwyProductCommentList(new FfwyProductComment(ffwyProduct.getProductId())));
            }
        }

        return ffwyProductCommentList;
    }

    /**
     * 组装评论图片
     *
     * @param ffwyProductComment
     * @return
     */
    private void assemblePhotot(FfwyProductComment ffwyProductComment) {
        ffwyProductComment.setPhotot(
                ffwyProductCommentPhotoMapper.selectFfwyProductCommentPhotoList(
                        new FfwyProductCommentPhoto(
                                ffwyProductComment.getProductCommentId()))
        );
        ffwyProductComment.setUserPhoto(
                ffwyUserMapper.selectFfwyUserById(ffwyProductComment.getUserId()).getPhoto()
        );

    }

    /**
     * 查询商品评论列表
     *
     * @param ffwyProductComment 商品评论
     * @return 商品评论
     */
    @Override
    public List<FfwyProductComment> selectFfwyProductCommentList(FfwyProductComment ffwyProductComment) {
        return ffwyProductCommentMapper.selectFfwyProductCommentList(ffwyProductComment);
    }

    /**
     * 所有查询商品评论列表
     *
     * @param ffwyProductComment 商品评论
     * @return 0是好评 1是差评
     */
    @Override
    public List<FfwyProductComment> selectFfwyProductCommentAllList(FfwyProductComment ffwyProductComment) {
        List<FfwyProductComment> ffwyProductComments = ffwyProductCommentMapper.selectFfwyProductCommentList(ffwyProductComment);
        ffwyProductComments.forEach(comments -> {
            LOGGER.info("comments:" , comments.getProductCommentId());
            //获取评论图片
            comments.setPhotot(ffwyProductCommentPhotoMapper.selectFfwyProductCommentPhoto(comments.getProductCommentId()));
            //获取用户信息
            comments.setUser(ffwyUserMapper.selectFfwyUserId(comments.getUserId()));
            //获取商品规格
            comments.setAttr(ffwySpecificationMapper.selectFfwySpecificationByproductId(comments.getProductId()));
        });
        return ffwyProductComments;
    }

    /**
     * 新增提现
     *
     * @param ffwyApplicaWithdrawal 提现
     * @return 结果
     */
    @Override
    public int insertFfwyApplicaWithdrawalt(FfwyApplicaWithdrawal ffwyApplicaWithdrawal) {
        return ffwyApplicaWithdrawalMapper.insertFfwyApplicaWithdrawal(ffwyApplicaWithdrawal);
    }

    /**
     * 修改商品评论
     *
     * @param ffwyProductComment 商品评论
     * @return 结果
     */
    @Override
    public int updateFfwyProductComment(FfwyProductComment ffwyProductComment) {
        return ffwyProductCommentMapper.updateFfwyProductComment(ffwyProductComment);
    }

    /**
     * 批量删除商品评论
     *
     * @param productCommentIds 需要删除的商品评论ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductCommentByIds(Long[] productCommentIds) {
        return ffwyProductCommentMapper.deleteFfwyProductCommentByIds(productCommentIds);
    }

    /**
     * 删除商品评论信息
     *
     * @param productCommentId 商品评论ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductCommentById(Long productCommentId) {
        return ffwyProductCommentMapper.deleteFfwyProductCommentById(productCommentId);
    }
}
