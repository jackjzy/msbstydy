package com.ruoyi.system.mapper.product;

import java.util.List;
import com.ruoyi.system.domain.product.FfwyProductComment;
import org.springframework.stereotype.Repository;

/**
 * 商品评论Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@Repository
public interface FfwyProductCommentMapper
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
     * 后台管理查询商屏评论（根据店铺id）
     *
     * @param ffwyProductComment 商品评论
     * @return 商品评论集合
     */
    public List<FfwyProductComment> selectFfwyProductCommentListByShop(FfwyProductComment ffwyProductComment);

    /**
     * 新增商品评论
     *
     * @param ffwyProductComment 商品评论
     * @return 结果
     */
    public int insertFfwyProductComment(FfwyProductComment ffwyProductComment);


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
    FfwyProductComment selectFfwyProductCommentByproductId(Long productId);


    List<FfwyProductComment> selectFfwyProductCommentArticle(Long productId);

    FfwyProductComment selectFfwyProductCommentCount(Long productId);

}
