package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.product.FfwyProductComment;
import org.springframework.web.multipart.MultipartFile;


/**
 * 商品评论Service接口
 *
 * @author ruoyi
 * @date 2021-04-23
 */
public interface IFfwyProductCommentService
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
     * @return
     */
    public List<FfwyProductComment> selectUserId(Long userId,Long shopId,Long productId,
                                                 Long combomealId);

    /**
     * 查询商品评论列表
     *
     * @param ffwyProductComment 商品评论
     * @return 商品评论集合
     */
    public List<FfwyProductComment> selectFfwyProductCommentList(FfwyProductComment ffwyProductComment);


    /**
     * 查询店铺评论
     *
     * @param ffwyProductComment 商品评论
     * @return 商品评论集合
     */
    public List<FfwyProductComment> selectShopComment(FfwyProductComment ffwyProductComment);

    /**
     * 新增商品评论
     *
     * @param ffwyProductComment 商品评论
     * @return 结果
     */

    /**
     * 修改商品评论
     *
     * @param ffwyProductComment 商品评论
     * @return 结果
     */
    public int updateFfwyProductComment(FfwyProductComment ffwyProductComment);
    /**
     * 修改商品评论
     *
     * @param ffwyProductComment 商品评论
     * @return 结果
     */
    public int revert(FfwyProductComment ffwyProductComment);

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

    AjaxResult insertFfwyProductComment(FfwyProductComment comment);



}
