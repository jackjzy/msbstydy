package com.ruoyi.system.mapper.withdrawdeposit;

import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.withdrawdeposit.FfwyApplicaWithdrawal;

import java.util.List;

/**
 * 商品评论Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-23
 */
public interface FfwyApplicaWithdrawalMapper
{
    /**
     * 查询商品评论
     *
     * @param productCommentId 商品评论ID
     * @return 商品评论
     */
    public FfwyProductComment selectFfwyProductCommentById(Long productCommentId);

    /**
     * 查询商品评论列表
     *
     * @param ffwyProductComment 商品评论
     * @return 商品评论集合
     */
    public List<FfwyProductComment> selectFfwyProductCommentList(FfwyProductComment ffwyProductComment);

    /**
     * 新增商品评论
     *
     * @param ffwyApplicaWithdrawal 商品评论
     * @return 结果
     */
    public int insertFfwyApplicaWithdrawal(FfwyApplicaWithdrawal ffwyApplicaWithdrawal);

    /**
     * 修改商品评论
     *
     * @param ffwyProductComment 商品评论
     * @return 结果
     */
    public int updateFfwyProductComment(FfwyProductComment ffwyProductComment);

    /**
     * 删除商品评论
     *
     * @param productCommentId 商品评论ID
     * @return 结果
     */
    public int deleteFfwyProductCommentById(Long productCommentId);

    /**
     * 批量删除商品评论
     *
     * @param productCommentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyProductCommentByIds(Long[] productCommentIds);

    List<FfwyProductComment> selectFfwyProductCommentByproductId(String productName);


    List<FfwyProductComment> selectFfwyProductCommentArticle(String productName);

    FfwyProductComment selectFfwyProductCommentCount(Long productId);

}
