package com.ruoyi.system.service;

import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.withdrawdeposit.FfwyApplicaWithdrawal;

import java.util.List;

/**
 * 商品评论Service接口
 * 
 * @author ruoyi
 * @date 2021-04-23
 */
public interface IFfwyApplicaWithdrawalService
{
    /**
     * 查询商品评论
     *
     * @param productCommentId 商品评论ID
     * @return 商品评论
     */
    public FfwyProductComment selectFfwyProductCommentById(Long productCommentId);

    /**
     * 根据店主查询商品评论
     * @param userId  店主 id
     * @param shopId  店铺id
     * @param productId 商品id
     * @param combomealId 套餐id
     * @param appraiseFile 评价图片
     * @return
     */
    public List<FfwyProductComment> selectUserId(Long userId, Long shopId, Long productId,
                                                 Long combomealId);

    /**
     * 查询商品评论列表
     * 
     * @param ffwyProductComment 商品评论
     * @return 商品评论集合
     */
    public List<FfwyProductComment> selectFfwyProductCommentList(FfwyProductComment ffwyProductComment);

    /**
     * 新增提现
     * 
     * @param ffwyApplicaWithdrawal 提现
     * @return 结果
     */
    public int insertFfwyApplicaWithdrawalt(FfwyApplicaWithdrawal ffwyApplicaWithdrawal);

    /**
     * 修改商品评论
     * 
     * @param ffwyProductComment 商品评论
     * @return 结果
     */
    public int updateFfwyProductComment(FfwyProductComment ffwyProductComment);

    /**
     * 批量删除商品评论
     * 
     * @param productCommentIds 需要删除的商品评论ID
     * @return 结果
     */
    public int deleteFfwyProductCommentByIds(Long[] productCommentIds);

    /**
     * 删除商品评论信息
     * 
     * @param productCommentId 商品评论ID
     * @return 结果
     */
    public int deleteFfwyProductCommentById(Long productCommentId);

    List<FfwyProductComment> selectFfwyProductCommentAllList(FfwyProductComment ffwyProductComment);
}
