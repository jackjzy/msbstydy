package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductCommentPhoto;

import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.mapper.FfwySpecificationMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;
import com.ruoyi.system.mapper.product.FfwyProductCommentPhotoMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.mapper.product.FfwyProductSkuMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.product.FfwyProductCommentMapper;
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.service.IFfwyProductCommentService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品评论Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-23
 */
@Service
public class FfwyProductCommentServiceImpl implements IFfwyProductCommentService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FfwyProductCommentServiceImpl.class);

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
    private FfwyOrderMapper ffwyOrderMapper;
    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;
    @Value("${cos.prefixUrl}")
    private String prefixUrl;


    /**
     * 查询商品评论
     *
     * @param productCommentId 商品评论ID
     * @return 商品评论
     */
    @Override
    public FfwyProductComment selectFfwyProductCommentById(Long productCommentId) {
        FfwyProductComment ffwyProductComment = ffwyProductCommentMapper.selectFfwyProductCommentById(productCommentId);
        List<FfwyProductCommentPhoto> photot = ffwyProductComment.getPhotot();
        if (null != photot && photot.size() == 1) {
            FfwyProductCommentPhoto ffwyProductCommentPhoto = photot.get(0);
            if (ffwyProductCommentPhoto.getProductCommentPhotoId() == null && ffwyProductCommentPhoto.getProductCommentPhoto() == null) {
                photot.remove(0);
            }
        }
        ffwyProductComment.setPhotot(photot);
        return ffwyProductComment;
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

    @Override
    public List<FfwyProductComment> selectShopComment(FfwyProductComment ffwyProductComment) {
        Long userId = null;
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取当前用户传入进来的数量
        //真实用户
        if (!StringUtils.isEmpty(request.getHeader(JWTUtils.TOKRN))) {
            userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        }

        if (userId != null) {
            ffwyProductComment.setUserId(userId);
            List<FfwyProductComment> ffwyProductComments = ffwyProductCommentMapper.selectFfwyProductCommentList(ffwyProductComment);
            Long finalUserId = userId;
            ffwyProductComments.forEach(ffwyProductComment1 -> {
                FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserById(finalUserId);
                ffwyUser.setPhoto(prefixUrl + ffwyUser.getPhoto());
                ffwyProductComment1.setFfwyUser(ffwyUser);
                List<FfwyProductCommentPhoto> ffwyProductCommentPhotos = ffwyProductCommentPhotoMapper.selectFfwyProductCommentPhoto(ffwyProductComment.getProductCommentId());
                ffwyProductCommentPhotos.forEach(f -> {
                    f.setProductCommentPhoto(prefixUrl + f.getProductCommentPhoto());
                });
                ffwyProductComment1.setPhotot(ffwyProductCommentPhotos);
            });
            return ffwyProductComments;
        } else {
            List<FfwyProductComment> ffwyProductComments = ffwyProductCommentMapper.selectFfwyProductCommentListByShop(ffwyProductComment);
//            ffwyProductComments.forEach(ffwyProductComment1 -> {
//                FfwyUser ffwyUser = ffwyProductComment1.getFfwyUser();
//                String userName = ffwyUser.getUserName();
//                String s1 = EmojiParser.parseToUnicode(userName);
//                String s = EmojiParser.parseToHtmlDecimal(s1);
//                ffwyUser.setUserName(s);
//                ffwyProductComment1.setFfwyUser(ffwyUser);
//            });
            return ffwyProductComments;
        }

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
            LOGGER.info("comments:", comments.getProductCommentId());
            //获取评论图片
            comments.setPhotot(ffwyProductCommentPhotoMapper.selectFfwyProductCommentPhoto(comments.getProductCommentId()));
            //获取用户信息
            comments.setUser(ffwyUserMapper.selectFfwyUserId(comments.getUserId()));
            //获取商品规格
            FfwyOrderDetails ffwyOrderDetails
                    =
                    ffwyOrderDetailsMapper.selectFfwyOrderDetailsSku(comments.getOrderId());
            if (ffwyOrderDetails == null) {
                comments.setSku(null);
            } else  if (ffwyOrderDetails != null){
                comments.setSku(ffwyOrderDetails.getProductSkuSpec());
            }

        });
        return ffwyProductComments;
    }

    @Override
    public AjaxResult insertFfwyProductComment(FfwyProductComment comment) {

        comment.getComments().forEach(ffwyProductComment -> {
            ffwyProductComment.setProductId(ffwyProductComment.getProductId());
            ffwyProductComment.setOrderId(ffwyProductComment.getOrderId());
            ffwyProductComment.setComment(ffwyProductComment.getComment());
            ffwyProductComment.setRating(ffwyProductComment.getRating());
            ffwyProductComment.setUserId(ffwyProductComment.getUserId());
            ffwyProductComment.setProductName(ffwyProductComment.getProductName());
            ffwyProductComment.setCommrnytTime(new Date());
            ffwyProductCommentMapper.insertFfwyProductComment(ffwyProductComment);
            List<String> img = ffwyProductComment.getImg();
            if (null == img || img.size() == 0) {

            } else if (img.size() > 0 && img != null) {
                for (int i = 0; i < img.size(); i++) {
                    FfwyProductCommentPhoto ffwyProductCommentPhoto1 = new FfwyProductCommentPhoto(ffwyProductComment.getProductCommentId());
                    ffwyProductCommentPhoto1.setProductCommentId(ffwyProductComment.getProductCommentId());
                    ffwyProductCommentPhoto1.setProductCommentPhoto((StringUtils.remove(ffwyProductComment.getImg().get(i), prefixUrl)));
                    ffwyProductCommentPhotoMapper.insertFfwyProductCommentPhoto(ffwyProductCommentPhoto1);
                }
            }
            if (ffwyProductComment.getOrderId() != null) {
                FfwyOrder ffwyOrder = new FfwyOrder();
                ffwyOrder.setOrderId(ffwyProductComment.getOrderId());
                ffwyOrder.setStatusId(6);
                ffwyOrderMapper.updateFfwyOrder(ffwyOrder);
                FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
                ffwyOrderDetails.setOrderId(ffwyProductComment.getOrderId());
                ffwyOrderDetails.setOrderStatus(6);
                ffwyOrderDetailsMapper.updateFfwyOrderDetailsId(ffwyOrderDetails);
            }
        });
        return AjaxResult.success("添加评价", comment);

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

    @Override
    public int revert(FfwyProductComment ffwyProductComment) {
        ffwyProductComment.setReplyStatus("1");
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
