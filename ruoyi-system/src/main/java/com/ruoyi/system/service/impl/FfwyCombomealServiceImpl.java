package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.CombomealGoodsCategory;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.OrderComStatusEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisHashUtil;
import com.ruoyi.system.domain.FfwyPayment;
import com.ruoyi.system.domain.Search;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomeal.*;
import com.ruoyi.system.domain.combomealorders.FfwyMaterial;
import com.ruoyi.system.domain.combomealorders.FfwyOrderCombomeal;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderStatus;
import com.ruoyi.system.domain.product.*;
import com.ruoyi.system.domain.to.*;
import com.ruoyi.system.domain.video.FfwyVideoHotVo;
import com.ruoyi.system.domain.video.FfwyVideoHotsVo;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.mapper.FfwyCombomealCommentMapper;
import com.ruoyi.system.mapper.FfwyPaymentMapper;
import com.ruoyi.system.mapper.FfwySpecificationMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.combomeal.*;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealCategoryMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyMaterialMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyOrderCombomealMapper;
import com.ruoyi.system.mapper.order.FfwyOrderMapper;
import com.ruoyi.system.mapper.order.FfwyOrderStatusMapper;
import com.ruoyi.system.mapper.product.FfwyProductCategoryMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.mapper.product.FfwyProductPhotoMapper;
import com.ruoyi.system.mapper.product.FfwyProductSkuMapper;
import com.ruoyi.system.service.IFfwyProductSkuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyCombomealService;
import org.springframework.transaction.annotation.Transactional;

import static cn.hutool.core.date.DateTime.now;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
@Slf4j
public class FfwyCombomealServiceImpl implements IFfwyCombomealService {
    @Autowired
    private FfwyCombomealMapper ffwyCombomealMapper;

    @Autowired
    private CombomealCategoryMapper combomealCategoryMapper;

    @Autowired
    private FfwyCombomealHardMapper ffwyCombomealHardMapper;

    @Autowired
    private FfwyCombomealSoftMapper ffwyCombomealSoftMapper;

    @Autowired
    private FfwyCombomealWiringMapper ffwyCombomealWiringMapper;

    @Autowired
    private FfwyProductSkuMapper ffwyProductSkuMapper;

    @Autowired
    private FfwyCombomealCommodityMapper ffwyCombomealCommodityMapper;

    @Autowired
    private FfwyCombomealSmartMapper ffwyCombomealSmartMapper;

    @Autowired
    private FfwyOrderMapper ffwyOrderMapper;

    @Autowired
    private FfwyProductMapper ffwyProductMapper;

    @Autowired
    private FfwyOrderStatusMapper ffwyOrderStatusMapper;

    @Autowired
    private FfwyProductCategoryMapper ffwyProductCategoryMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private FfwyProductPhotoMapper ffwyProductPhotoMapper;

    @Autowired
    private FfwyPaymentMapper ffwyPaymentMapper;

    @Autowired
    private FfwyCombomealCategoryMapper ffwyCombomealCategoryMapper;

    @Autowired
    private FfwyCombomealPhotoMapper ffwyCombomealPhotoMapper;

    @Autowired
    private FfwyCombomealCommentMapper ffwyCombomealCommentMapper;

    @Autowired
    private IFfwyProductSkuService ffwyProductSkuService;

    @Autowired
    private FfwyOrderCombomealMapper ffwyOrderCombomealMapper;

    @Autowired
    private IFfwyProductSkuService ifwyProductSkuService;
    @Autowired
    private FfwyMaterialMapper ffwyMaterialMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private FfwySpecificationMapper ffwySpecificationMapper;

    @Autowired
    private FfwyCombomealCommentPhotoMapper ffwyCombomealCommentPhotoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //    @Qualifier(value = "redisTemplateMy")
    @Autowired
    private RedisTemplate redisTemplate;
    // @Autowired
    //private RedisClient redisClient;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;


    /**
     * 查询【请填写功能名称】
     *
     * @param combomealId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomeal selectFfwyCombomealById(Long combomealId) {
        return ffwyCombomealMapper.selectFfwyCombomealById(combomealId);
    }

    @Override
    public List<ProductVo> selectFfwyCombomealList(FfwyOrderVo ffwyOrderVo) {
        List<ProductVo> orderLists = new ArrayList();
        ProductVo productVo = new ProductVo();
        FfwyOrder ffwyOrder = new FfwyOrder();
        ffwyOrder.setOrderId(ffwyOrderVo.getOrderId().longValue());
        ffwyOrder.setOrderNumber(ffwyOrderVo.getOrderNumber());
        List<FfwyOrder> ffwyOrders = ffwyOrderMapper.selectFfwyOrderList(ffwyOrder);
        FfwyPayment ffwyPayment = new FfwyPayment();
        FfwyUser ffwyUser = new FfwyUser();
        for (FfwyOrder order : ffwyOrders) {
            productVo.setOrderId(order.getOrderId());
            productVo.setOrderNumber(order.getOrderNumber());
            ffwyUser.setUserId(order.getUserId());
            ffwyPayment.setPaymentId(order.getPaymentId());
        }
        List<FfwyPayment> ffwyPayments = ffwyPaymentMapper.selectFfwyPaymentList(ffwyPayment);
        for (FfwyPayment payment : ffwyPayments) {
            productVo.setPatmenttype(payment.getPatmentType());
        }
        List<FfwyUser> ffwyUsers = ffwyUserMapper.selectFfwyUserList(ffwyUser);
        for (FfwyUser user : ffwyUsers) {
            productVo.setUserName(user.getUserName());
            productVo.setPhoto(user.getPhoto());
        }
        FfwyProduct ffwyProduct = new FfwyProduct();
        ffwyProduct.setProductName(ffwyOrderVo.getProductName());
        List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyProductList(ffwyProduct);
        FfwyProductPhoto ffwyProductPhoto = new FfwyProductPhoto();
        for (FfwyProduct product : ffwyProducts) {
            productVo.setProductName(product.getProductName());
            productVo.setSales(product.getSales());
            productVo.setStock(product.getStock());
            productVo.setStockUnit(product.getStockUnit());
            ffwyProductPhoto.setProductId(product.getProductId());
        }
        List<FfwyProductPhoto> ffwyProductPhotos = ffwyProductPhotoMapper.selectFfwyProductPhotoList(ffwyProductPhoto);
        for (FfwyProductPhoto productPhoto : ffwyProductPhotos) {
            productVo.setProductId(productPhoto.getProductId());
            productVo.setProductPhotopath(productPhoto.getProductPhotopath());
        }
        FfwyOrderStatus ffwyOrderStatus = new FfwyOrderStatus();
        ffwyOrderStatus.setStatusName(ffwyOrderVo.getOrderStatus());
        List<FfwyOrderStatus> ffwyOrderStatuses = ffwyOrderStatusMapper.selectFfwyOrderStatusList(ffwyOrderStatus);
        for (FfwyOrderStatus orderStatus : ffwyOrderStatuses) {
            productVo.setStatusName(orderStatus.getStatusName());
        }
        FfwyProductCategory ffwyProductCategory = new FfwyProductCategory();
        ffwyProductCategory.setCreateTime(ffwyOrderVo.getCreateTime());
        ffwyProductCategory.setCategoryId(ffwyOrderVo.getCategoryId().longValue());
        List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryMapper.selectFfwyProductCategoryList(ffwyProductCategory);
        for (FfwyProductCategory productCategory : ffwyProductCategories) {
            productVo.setCategoryName(productCategory.getCategoryName());
        }
        orderLists.add(productVo);
        return orderLists;
    }

    @Override
    @Transactional
    public int insertFfwyCombomeal(FfwyCombomeal ffwyCombomeal) {
        //套餐
        ffwyCombomeal.setCreateTime(new Date());
        String fileCover1 = ffwyCombomeal.getFileCover();
        ffwyCombomeal.setFileCover(StringUtils.remove(fileCover1, prefixUrl));
        int i = ffwyCombomealMapper.insertFfwyCombomeal(ffwyCombomeal);
        Long categoryId = ffwyCombomeal.getCategoryId();
        if (categoryId == 1) {
            //智能家居的信息
            String smartName = ffwyCombomeal.getSmartName();
            if (StringUtils.isNotEmpty(smartName)) {

                String[] split1 = smartName.split(",");

                FfwyCombomealSmart combomealSmart = new FfwyCombomealSmart();

                for (int i1 = 0; i1 < split1.length; i1++) {
                    String s1 = split1[i1];
                    long s2 = Long.valueOf(s1).intValue();
                    List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatName(s2);
                    for (FfwyProductSku productSkus : ffwyProductSkus) {
                        Long productId = productSkus.getProductId();
                        List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyfindByproductcatName(productId);
                        for (FfwyProduct ffwyProduct : ffwyProducts) {
                            String productName1 = ffwyProduct.getProductName();
                            combomealSmart.setProductName(productName1);
                        }

                    }
                    combomealSmart.setProductId(s1);
                    combomealSmart.setCombomealId(Long.valueOf(ffwyCombomeal.getCombomealId()));
                    ffwyCombomealSmartMapper.insertFfwyCombomealSmart(combomealSmart);
                }

                log.info("combomealSmart:{}", combomealSmart);
            }
        }


        //拿到生活日用品的信息
        String articlesName = ffwyCombomeal.getArticlesName();
        if (articlesName != null) {
            String[] split1 = articlesName.split(",");

            FfwyCombomealCommodity combomealCommodity = new FfwyCombomealCommodity();

            for (int i1 = 0; i1 < split1.length; i1++) {
                String s1 = split1[i1];
                long s2 = Long.valueOf(s1).intValue();
                List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatName(s2);
                for (FfwyProductSku productSkus : ffwyProductSkus) {
                    Long productId = productSkus.getProductId();
                    List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyfindByproductcatName(productId);
                    for (FfwyProduct ffwyProduct : ffwyProducts) {
                        String productName1 = ffwyProduct.getProductName();
                        combomealCommodity.setProductName(productName1);
                    }

                }
                combomealCommodity.setProductId(s1);
                combomealCommodity.setCombomealId(Long.valueOf(ffwyCombomeal.getCombomealId()));
                int i2 = ffwyCombomealCommodityMapper.insertFfwyCombomealCommodity(combomealCommodity);

            }

            log.info("combomealCommodity:{}", combomealCommodity);

        }

        //添加家电产品
//        FfwyProductCategory homeName = ffwyProductCategoryMapper.selectFfwyProductCategoryByName(ffwyCombomeal.getApplianceName());
        String applianceName = ffwyCombomeal.getApplianceName();

        if (applianceName != null) {

            String[] split1 = applianceName.split(",");
            FfwyCombomealWiring ffwyCombomealWiring = new FfwyCombomealWiring();

            for (int i1 = 0; i1 < split1.length; i1++) {
                String s1 = split1[i1];
                long s2 = Long.valueOf(s1).intValue();
                List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatName(s2);
                for (FfwyProductSku productSkus : ffwyProductSkus) {
                    Long productId = productSkus.getProductId();
                    List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyfindByproductcatName(productId);
                    for (FfwyProduct ffwyProduct : ffwyProducts) {
                        String productName1 = ffwyProduct.getProductName();
                        ffwyCombomealWiring.setProductName(productName1);
                    }

                }
                ffwyCombomealWiring.setProductId(s1);
                ffwyCombomealWiring.setCombomealId(Long.valueOf(ffwyCombomeal.getCombomealId()));

                ffwyCombomealWiringMapper.insertFfwyCombomealWiring(ffwyCombomealWiring);
            }
            log.info("ffwyCombomealWiring:{}", ffwyCombomealWiring);

        }


        //软装
//        FfwyProductCategory softName = ffwyProductCategoryMapper.selectFfwyProductCategoryByName(ffwyCombomeal.getSoftOutfit());
        String softOutfit = ffwyCombomeal.getSoftOutfit();
        if (softOutfit != null) {

            String[] split1 = softOutfit.split(",");

            FfwyCombomealSoft ffwyCombomealSoft = new FfwyCombomealSoft();
            for (int i1 = 0; i1 < split1.length; i1++) {
                String s1 = split1[i1];
                long s2 = Long.valueOf(s1).intValue();
                List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatName(s2);
                for (FfwyProductSku productSkus : ffwyProductSkus) {
                    Long productId = productSkus.getProductId();
                    List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyfindByproductcatName(productId);
                    for (FfwyProduct ffwyProduct : ffwyProducts) {
                        String productName1 = ffwyProduct.getProductName();
                        ffwyCombomealSoft.setProductName(productName1);
                    }

                }
                ffwyCombomealSoft.setProductId(s1);
                ffwyCombomealSoft.setCombomealId(Long.valueOf(ffwyCombomeal.getCombomealId()));
                ffwyCombomealSoftMapper.insertFfwyCombomealSoft(ffwyCombomealSoft);

            }
            log.info("ffwyCombomealSoft:{}", ffwyCombomealSoft);
        }

//
//        String fileCover = ffwyCombomeal.getFileCover();
//        //添加封面图片
//        if (StringUtils.isNotEmpty(fileCover)) {
//            // 上传文件
//            FfwyCombomealPhoto ffwyCombomealPhoto = new FfwyCombomealPhoto();
//            ffwyCombomealPhoto.setCombomealId(ffwyCombomeal.getCombomealId());
//            ffwyCombomealPhoto.setPhotoPath(StringUtils.remove(fileCover,prefixUrl));
//            ffwyCombomealPhoto.setCreateTime(now());
//            ffwyCombomealPhoto.setPhotoType("2");
//            ffwyCombomealPhotoMapper.insertFfwyCombomealPhoto(ffwyCombomealPhoto);
//
//        }


        List<FfwyCombomealPhoto> combomealPhotos = ffwyCombomeal.getCombomealPhotos();
        if (combomealPhotos.size() > 0 && combomealPhotos != null) {
            combomealPhotos.forEach(cp -> {
                String photoPath = cp.getPhotoPath();
                cp.setCombomealId(ffwyCombomeal.getCombomealId());
                cp.setPhotoPath(StringUtils.remove(photoPath, prefixUrl));
                cp.setCreateTime(now());
                cp.setPhotoType("0");
            });
            ffwyCombomealPhotoMapper.insertFfwyCombomealPhotos(combomealPhotos);

        }

        return i;
    }

    @Override
    public int updateFfwyCombomeal(FfwyCombomealCategory ffwyCombomealCategory) {
        return ffwyCombomealCategoryMapper.updateFfwyCombomealCategory(ffwyCombomealCategory);
    }


    @Override
    @Transactional
    public int editFfwyCombomeal(FfwyCombomeal ffwyCombomeal) {

        ffwyCombomeal.setUpdateTime(new Date());
        String fileCover1 = ffwyCombomeal.getFileCover();
        ffwyCombomeal.setFileCover(StringUtils.remove(fileCover1, prefixUrl));
        int i = ffwyCombomealMapper.updateFfwyCombomeal(ffwyCombomeal);
        Long combomealId = ffwyCombomeal.getCombomealId();
        Long categoryId = ffwyCombomeal.getCategoryId();
        if (categoryId == 1) {
            //智能家居的信息
            String smartName = ffwyCombomeal.getSmartName();
            ffwyCombomealSmartMapper.deleteFfwyCombomealSmartcId(combomealId);

            if (StringUtils.isNotEmpty(smartName)) {
                String[] split1 = smartName.split(",");

                FfwyCombomealSmart combomealSmart = new FfwyCombomealSmart();

                for (int i1 = 0; i1 < split1.length; i1++) {
                    String s1 = split1[i1];
                    long s2 = Long.valueOf(s1).intValue();
                    List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatName(s2);
                    for (FfwyProductSku productSkus : ffwyProductSkus) {
                        Long productId = productSkus.getProductId();
                        List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyfindByproductcatName(productId);
                        for (FfwyProduct ffwyProduct : ffwyProducts) {
                            String productName1 = ffwyProduct.getProductName();
                            combomealSmart.setProductName(productName1);
                        }

                    }
                    combomealSmart.setProductId(s1);
                    combomealSmart.setCombomealId(Long.valueOf(ffwyCombomeal.getCombomealId()));
                    ffwyCombomealSmartMapper.insertFfwyCombomealSmart(combomealSmart);
                }

                log.info("combomealSmart:{}", combomealSmart);
            }
        }


        //拿到生活日用品的信息
        String articlesName = ffwyCombomeal.getArticlesName();
        ffwyCombomealCommodityMapper.deleteFfwyCombomealCommoditycId(combomealId);

        if (articlesName != null && articlesName != "") {
            String[] split1 = articlesName.split(",");

            FfwyCombomealCommodity combomealCommodity = new FfwyCombomealCommodity();

            for (int i1 = 0; i1 < split1.length; i1++) {
                String s1 = split1[i1];
                long s2 = Long.valueOf(s1).intValue();
                List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatName(s2);
                for (FfwyProductSku productSkus : ffwyProductSkus) {
                    Long productId = productSkus.getProductId();
                    List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyfindByproductcatName(productId);
                    for (FfwyProduct ffwyProduct : ffwyProducts) {
                        String productName1 = ffwyProduct.getProductName();
                        combomealCommodity.setProductName(productName1);
                    }

                }
                combomealCommodity.setProductId(s1);
                combomealCommodity.setCombomealId(Long.valueOf(ffwyCombomeal.getCombomealId()));
                int i2 = ffwyCombomealCommodityMapper.insertFfwyCombomealCommodity(combomealCommodity);

            }

            log.info("combomealCommodity:{}", combomealCommodity);

        }

        //添加家电产品
//        FfwyProductCategory homeName = ffwyProductCategoryMapper.selectFfwyProductCategoryByName(ffwyCombomeal.getApplianceName());
        String applianceName = ffwyCombomeal.getApplianceName();
        ffwyCombomealWiringMapper.deleteFfwyCombomealWiringcId(combomealId);
        if (applianceName != null && articlesName != "") {

            String[] split1 = applianceName.split(",");
            FfwyCombomealWiring ffwyCombomealWiring = new FfwyCombomealWiring();

            for (int i1 = 0; i1 < split1.length; i1++) {
                String s1 = split1[i1];
                long s2 = Long.valueOf(s1).intValue();
                List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatName(s2);
                for (FfwyProductSku productSkus : ffwyProductSkus) {
                    Long productId = productSkus.getProductId();
                    List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyfindByproductcatName(productId);
                    for (FfwyProduct ffwyProduct : ffwyProducts) {
                        String productName1 = ffwyProduct.getProductName();
                        ffwyCombomealWiring.setProductName(productName1);
                    }

                }
                ffwyCombomealWiring.setProductId(s1);
                ffwyCombomealWiring.setCombomealId(Long.valueOf(ffwyCombomeal.getCombomealId()));

                ffwyCombomealWiringMapper.insertFfwyCombomealWiring(ffwyCombomealWiring);
            }
            log.info("ffwyCombomealWiring:{}", ffwyCombomealWiring);

        }


        //软装
//        FfwyProductCategory softName = ffwyProductCategoryMapper.selectFfwyProductCategoryByName(ffwyCombomeal.getSoftOutfit());
        String softOutfit = ffwyCombomeal.getSoftOutfit();
        ffwyCombomealSoftMapper.deleteFfwyCombomealSoftcId(combomealId);
        if (softOutfit != null && articlesName != "") {
            String[] split1 = softOutfit.split(",");

            FfwyCombomealSoft ffwyCombomealSoft = new FfwyCombomealSoft();
            for (int i1 = 0; i1 < split1.length; i1++) {
                String s1 = split1[i1];
                long s2 = Long.valueOf(s1).intValue();
                List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatName(s2);
                for (FfwyProductSku productSkus : ffwyProductSkus) {
                    Long productId = productSkus.getProductId();
                    List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyfindByproductcatName(productId);
                    for (FfwyProduct ffwyProduct : ffwyProducts) {
                        String productName1 = ffwyProduct.getProductName();
                        ffwyCombomealSoft.setProductName(productName1);
                    }

                }
                ffwyCombomealSoft.setProductId(s1);
                ffwyCombomealSoft.setCombomealId(Long.valueOf(ffwyCombomeal.getCombomealId()));
                ffwyCombomealSoftMapper.insertFfwyCombomealSoft(ffwyCombomealSoft);

            }
            log.info("ffwyCombomealSoft:{}", ffwyCombomealSoft);
        }

        List<FfwyCombomealPhoto> combomealPhotos = ffwyCombomeal.getCombomealPhotos();

        String photoType = "0";
        ffwyCombomealPhotoMapper.deleteFfwyCombomealPhotocIds(combomealId, photoType);
        if (combomealPhotos.size() > 0) {
            combomealPhotos.forEach(cp -> {
                cp.setCombomealId(ffwyCombomeal.getCombomealId());
                cp.setUpdateTime(now());
                cp.setPhotoPath(StringUtils.remove(cp.getPhotoPath(), prefixUrl));
                cp.setPhotoType("0");
            });
            ffwyCombomealPhotoMapper.insertFfwyCombomealPhotos(combomealPhotos);
            log.info("combomealPhotos:{}", combomealPhotos);
        }

        return i;
    }

    @Override
    public int deletePhopos(String path) {
        int i = ffwyCombomealPhotoMapper.deleteFfwyPhotos(path);
        return i;
    }

    @Override
    public List<FfwyVideoHotsVo> selectFfwyCombomealfindBylists(Long hotId) {
        return ffwyCombomealMapper.selectFfwyCombomealfindBylists(hotId);
    }

    @Override
    public int deleteFfwyCombomealByIds(Long[] combomealIds) {
        return ffwyCombomealMapper.deleteFfwyCombomealByIds(combomealIds);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param ffwyOrderVo 【请填写功能名称】
     * @return 【请填写功能名称】
     */


    /**
     * 修改【请填写功能名称】
     *
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 结果
     */


    /**
     * 批量删除【请填写功能名称】
     *
     * @param combomealIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */


    /**
     * 删除【请填写功能名称】信息
     *
     * @param combomealId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealById(Long combomealId) {
        return ffwyCombomealMapper.deleteFfwyCombomealById(combomealId);
    }

    @Override
    public List<FfwyCombomeal> selectCombomeal(FfwyCombomeal ffwyCombomeal) {
        List<FfwyCombomeal> combomealCatVos = ffwyCombomealMapper.selectCombomealAll(ffwyCombomeal);
        List<FfwyProductSku> ffwyProductSkusList = ffwyProductSkuMapper.selectFfwyProductSkuList(new FfwyProductSku());
        List<FfwyProduct> ffwyProductList = ffwyProductMapper.selectProductToList(new ProductTo());

        for (FfwyCombomeal combomealCatVo : combomealCatVos) {
            Long combomealId = combomealCatVo.getCombomealId();
            String applianceName1 = combomealCatVo.getApplianceName();
            String softOutfit1 = combomealCatVo.getSoftOutfit();
            String articlesName1 = combomealCatVo.getArticlesName();
            String combomealNames = combomealCatVo.getCombomealNames();
            String smartName1 = combomealCatVo.getSmartName();
            String fileCover1 = combomealCatVo.getFileCover();
            combomealCatVo.setFileCover(prefixUrl + fileCover1);
            if (combomealNames.equals("毛坯房套餐")) {
                if (StringUtils.isNotEmpty(smartName1)) {
                    String smartName = "";
                    String[] split1 = smartName1.split(",");
                    for (int i1 = 0; i1 < split1.length; i1++) {
                        String s = split1[i1];
                        long skuId = Long.valueOf(s).intValue();
                        FfwyProductSku ffwyProductSku = ffwyProductSkusList.stream().filter(pk -> pk.getSkuId().equals(skuId)).collect(Collectors.toList()).get(0);
                        Long productId = ffwyProductSku.getProductId();
                        FfwyProduct ffwyProduct = ffwyProductList.stream().filter(p -> p.getProductId().equals(productId)).collect(Collectors.toList()).get(0);
                        smartName += ffwyProduct.getProductName() + ffwyProductSku.getSkuSpec() + ",";
                    }
                    combomealCatVo.setSmartName(smartName);
                }
            }
            if (StringUtils.isNotEmpty(applianceName1)) {
                String applianceName = "";
                String[] split1 = applianceName1.split(",");
                for (int i1 = 0; i1 < split1.length; i1++) {
                    String s = split1[i1];
                    long skuId = Long.valueOf(s).intValue();

                    FfwyProductSku ffwyProductSku = ffwyProductSkusList.stream().filter(pk -> pk.getSkuId().equals(skuId)).collect(Collectors.toList()).get(0);
                    Long productId = ffwyProductSku.getProductId();
                    FfwyProduct ffwyProduct = ffwyProductList.stream().filter(p -> p.getProductId().equals(productId)).collect(Collectors.toList()).get(0);
                    applianceName += ffwyProduct.getProductName() + ffwyProductSku.getSkuSpec() + ",";

                }
                combomealCatVo.setApplianceName(applianceName);
            }

            if (StringUtils.isNotEmpty(softOutfit1)) {
                String softOutfit = "";
                String[] split1 = softOutfit1.split(",");
                for (int i1 = 0; i1 < split1.length; i1++) {
                    String s = split1[i1];
                    long skuId = Long.valueOf(s).intValue();

                    List<FfwyProductSku> ffwyProductSkus = ffwyProductSkusList.stream().filter(pk -> pk.getSkuId().equals(skuId)).collect(Collectors.toList());

                    FfwyProductSku ffwyProductSku = ffwyProductSkus.get(0);

                    Long productId = ffwyProductSku.getProductId();
                    FfwyProduct ffwyProduct = ffwyProductList.stream().filter(p -> p.getProductId().equals(productId)).collect(Collectors.toList()).get(0);
                    softOutfit += ffwyProduct.getProductName() + ffwyProductSku.getSkuSpec() + ",";

                }
                combomealCatVo.setSoftOutfit(softOutfit);
            }

            if (StringUtils.isNotEmpty(articlesName1)) {
                String articlesName = "";
                String[] split1 = articlesName1.split(",");
                for (int i1 = 0; i1 < split1.length; i1++) {
                    String s = split1[i1];
                    long skuId = Long.valueOf(s).intValue();

                    FfwyProductSku ffwyProductSku = ffwyProductSkusList.stream().filter(pk -> pk.getSkuId().equals(skuId)).collect(Collectors.toList()).get(0);
                    Long productId = ffwyProductSku.getProductId();
                    FfwyProduct ffwyProduct = ffwyProductList.stream().filter(p -> p.getProductId().equals(productId)).collect(Collectors.toList()).get(0);
                    articlesName += ffwyProduct.getProductName() + ffwyProductSku.getSkuSpec() + ",";

                }
                combomealCatVo.setArticlesName(articlesName);
            }

            FfwyCombomealPhoto ffwyCombomealPhoto = new FfwyCombomealPhoto();
            ffwyCombomealPhoto.setCombomealId(combomealId);
            List<FfwyCombomealPhoto> ffwyCombomealPhotos = ffwyCombomealPhotoMapper.selectFfwyCombomealPhotoList(ffwyCombomealPhoto);
            if (ffwyCombomealPhotos.size() > 0) {
                List<String> list = new ArrayList<>();
                List<FfwyCombomealPhoto> ffwyCombomealPhotos1 = new ArrayList<>();
                for (FfwyCombomealPhoto combomealPhoto : ffwyCombomealPhotos) {
                    String photoType = combomealPhoto.getPhotoType();
                    String photoPath1 = combomealPhoto.getPhotoPath();
                    combomealPhoto.setPhotoPath(prefixUrl + photoPath1);
                    if (photoType.equals("0")) {
                        ffwyCombomealPhotos1.add(combomealPhoto);
                    }
//                        else if (photoType.equals("1")) {
//                            String img = combomealPhoto.getPhotoPath();
//                            list.add(img);
//                        }
                }
                combomealCatVo.setCombomealPhotos(ffwyCombomealPhotos1);
//                    combomealCatVo.setImg(list);

            }
        }

        return combomealCatVos;
    }


    @Override
    public List<Search> selectFfwyCombomealBycat(String token) {

//        List<Search> search = (List<Search>) redisClient.get(token);
//        if(search != null){
//           return search;
//       }



        /*List<Search> search = (List<Search>) redisClient.get(token);
        if(search != null){
           return search;
       }*/

        return null;
    }

    @Override
    public List<FfwyVideoHotVo> selectFfwyCombomealBylists() {
        return ffwyCombomealMapper.selectFfwyCombomealBylists();
    }

    @Override
    public Boolean deleteFfwyCombomealBytoken(String token) {


        //return redisClient.del(token);


        return false;
        /* return redisClient.del(token);*/

    }

    @Override
    public SignInCategoryCombomeal getCombomealStyleList(Long categoryId, Integer currPage) {
        log.info("FfwyCombomealServiceImpl  SignInCategoryCombomeal  categoryId: {}", categoryId);
        log.info("FfwyCombomealServiceImpl  SignInCategoryCombomeal  currPage: {}", currPage);
        // TODO 1. categoryId为空  查出 类型为1的商品     返回类型集合、套餐集合
        //      2. categoryId不为空  查出 类型为categoryId的商品     返回类型集合、套餐集合

        PageEntity pageGoods = new PageEntity();
        SignInCategoryCombomeal signInCategoryCombomeal = new SignInCategoryCombomeal();

        // 类型集合
        signInCategoryCombomeal.setCombomealCategories(combomealCategoryMapper.selectCombomealCategoryList(null));


        // 套餐集合
        if (null == categoryId) {
            categoryId = 1L;
        }
        pageGoods.setCurrPage(currPage);
        pageGoods.setTotal(ffwyCombomealMapper.getCountByCategoryId(categoryId));
        signInCategoryCombomeal.setCombomeals(ffwyCombomealMapper.selectCombomealByCategoryId(categoryId, pageGoods.getLimitFront(), pageGoods.getLimitAfter()));
        log.info("FfwyCombomealServiceImpl  SignInCategoryCombomeal  signInCategoryCombomeal: {}", signInCategoryCombomeal);
        return signInCategoryCombomeal;
    }

    @Override
    public AjaxResult getCombomealDetails(Long combomealId, Long userId) {
        log.info("FfwyCombomealServiceImpl  getCombomealDetails  combomealId: {}", combomealId);
        FfwyCombomeal ffwyCombomeal = ffwyCombomealMapper.selectCombomealByCombomealId(combomealId);

        if (null == ffwyCombomeal) {
            log.info("套餐id: " + combomealId + "的套餐为null");
            return AjaxResult.error("无此套餐或已下架");
        }

        CombomealDetailsVo detailsVo = new CombomealDetailsVo();
        // 组装套餐详情
        detailsVo.setCombomealId(ffwyCombomeal.getCombomealId());
        detailsVo.setCombomealName(ffwyCombomeal.getCombomealName());
        detailsVo.setPrice(ffwyCombomeal.getPrice());
        detailsVo.setPrices(ffwyCombomeal.getPrices());
        detailsVo.setCategoryId(ffwyCombomeal.getCategoryId());
        detailsVo.setSales(ffwyCombomeal.getSales());
        detailsVo.setExplainl(ffwyCombomeal.getExplainl());
        detailsVo.setInformation(ffwyCombomeal.getInformation());
        detailsVo.setHardOutfit(ffwyCombomeal.getHardOutfit());

        // 轮播图
        List<FfwyCombomealPhoto> ffwyCombomealPhotos = ffwyCombomealPhotoMapper.selectFfwyCombomealPhotoList(new FfwyCombomealPhoto(ffwyCombomeal.getCombomealId(), "0"));
        ffwyCombomealPhotos.forEach(ffwyCombomealPhoto -> {
            detailsVo.setCarouselPhoto(ffwyCombomealPhoto.getPhotoPath());
        });
//
//        // 详情图
//        ffwyCombomealPhotos = ffwyCombomealPhotoMapper.selectFfwyCombomealPhotoList(new FfwyCombomealPhoto(ffwyCombomeal.getCombomealId(), "0", "1"));
//        ffwyCombomealPhotos.forEach(ffwyCombomealPhoto -> {
//            detailsVo.setInformationPhoto(ffwyCombomealPhoto.getPhotoPath());
//        });


        // 软装
        List<FfwyCombomealSoft> ffwyCombomealSofts = ffwyCombomealSoftMapper.selectFfwyCombomealSoftList(new FfwyCombomealSoft(ffwyCombomeal.getCombomealId()));
        ffwyCombomealSofts.forEach(fSoft -> {
            detailsVo.setCombomealSoft(fSoft.getProductId(), fSoft.getProductName(), fSoft.getCombomealId(), 1L);
        });

        // 智能家居
        List<FfwyCombomealSmart> ffwyCombomealSmarts = ffwyCombomealSmartMapper.selectFfwyCombomealSmartList(new FfwyCombomealSmart(ffwyCombomeal.getCombomealId()));
        ffwyCombomealSmarts.forEach(fSmart -> {
            detailsVo.setCombomealSmart(fSmart.getProductId(), fSmart.getProductName(), fSmart.getCombomealId(), 1L);
        });

        //  家电
        List<FfwyCombomealWiring> ffwyCombomealWirings = ffwyCombomealWiringMapper.selectFfwyCombomealWiringList(new FfwyCombomealWiring(ffwyCombomeal.getCombomealId()));
        ffwyCombomealWirings.forEach(fWiring -> {
            detailsVo.setCombomealWiring(fWiring.getProductId(), fWiring.getProductName(), fWiring.getCombomealId(), 1L);
        });

        // 日用品
        List<FfwyCombomealCommodity> ffwyCombomealCommodities = ffwyCombomealCommodityMapper.selectFfwyCombomealCommodityList(new FfwyCombomealCommodity(ffwyCombomeal.getCombomealId()));
        ffwyCombomealCommodities.forEach(fCdity -> {
            detailsVo.setCombomealDity(fCdity.getProductId(), fCdity.getProductName(), fCdity.getCombomealId(), 1L);
        });

        // 评论
        List<FfwyCombomealComment> ffwyCombomealComments = ffwyCombomealCommentMapper.selectCombomealCommentByCombomealId(ffwyCombomeal.getCombomealId());
        for (FfwyCombomealComment fComment : ffwyCombomealComments) {
            // 评价用户
            FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserById(fComment.getUserId());
            // 评论图片
            List<String> ffwyCombomealCommentPhotos = ffwyCombomealCommentPhotoMapper.selectCommentPhotoList(fComment.getCommentId());
            detailsVo.setCommentVO(ffwyUser.getUserId() + "", ffwyUser.getUserName(), ffwyUser.getPhoto(), fComment.getComment(), fComment.getCreateTime(), fComment.getStar(), ffwyCombomealCommentPhotos);
        }

        detailsVo.setCommentSize((long) ffwyCombomealComments.size());
        // 定金
        detailsVo.setDeposit(new BigDecimal("200"));

        log.info("FfwyCombomealServiceImpl  getCombomealDetails  detailsVo: {}", detailsVo);
        return AjaxResult.success(detailsVo);
    }

    @Override
    public String getEstimatedPrice(CombomealTo combomealTo) {
        return getCalculatePrice(combomealTo).toString();
    }

    @Transactional
    @Override
    public String generateCombomaealOrder(CombomealTo combomealTo) {
        FfwyCombomeal ffwyCombomeal = ffwyCombomealMapper.selectCombomealByCombomealId(combomealTo.getCombomealId());

        if (null == ffwyCombomeal) {
            log.info("套餐id: " + combomealTo.getCombomealId() + "的套餐为null");
            return "无此套餐或已下架";
        }

        // 1.创建装修订单
        FfwyOrderCombomeal orderCombomeal = new FfwyOrderCombomeal();
        orderCombomeal.setUserId(Long.valueOf(combomealTo.getUserId()));         //用户id
//        orderCombomeal.setHouseArea(new BigDecimal(combomealTo.getSquareMeter())); //房屋面积
        orderCombomeal.setCombomealId(combomealTo.getCombomealId());          // 套餐id
        orderCombomeal.setCombomealCover(ffwyCombomeal.getPhoto());           // 套餐封面
        orderCombomeal.setCombomealName(ffwyCombomeal.getCombomealName());       // 套餐名称
        orderCombomeal.setOrderUserName(combomealTo.getName());             // 联系人
        orderCombomeal.setOrderPhone(combomealTo.getPhone());               // 联系手机号
        orderCombomeal.setOrderAddr(combomealTo.getAddress());              // 地址
        orderCombomeal.setServiceMoney(new BigDecimal("200"));             // 服务金额
        orderCombomeal.setOrderType(ffwyCombomeal.getCategoryId() + "");    // 套餐类型
        orderCombomeal.setOrderNumber(orderNumberGenerator());            // 订单号
        orderCombomeal.setOrderStatus(OrderComStatusEnum.CREATE_NEW.getCode());                                 // 订单状态

        int i = ffwyOrderCombomealMapper.insertFfwyOrderCombomeal(orderCombomeal);
        if (i > 0) {
            // 2.库存锁定，只要有异常，回滚订单数据
            WareSkuLockVo lockVo = new WareSkuLockVo();
            lockVo.setOrderSn(orderCombomeal.getOrderNumber());
            List<FfwyMaterial> materialList = new ArrayList<>();
            // 2.1 添加装修材料，锁库存
            if (combomealTo.getCombomealHards() != null) {
                assembleMaterial(materialList, combomealTo.getCombomealHards(), orderCombomeal.getOrderId()); // 硬装

            }
            if (combomealTo.getCombomealSofts() != null) {
                assembleMaterial(materialList, combomealTo.getCombomealSofts(), orderCombomeal.getOrderId()); // 软装

            }
            if (combomealTo.getCombomealSmarts() != null) {
                assembleMaterial(materialList, combomealTo.getCombomealSmarts(), orderCombomeal.getOrderId()); // 智能家居
            }
            if (combomealTo.getCombomealWirings() != null) {
                assembleMaterial(materialList, combomealTo.getCombomealWirings(), orderCombomeal.getOrderId()); // 家电

            }
            if (combomealTo.getCommodityVos() != null) {
                assembleMaterial(materialList, combomealTo.getCommodityVos(), orderCombomeal.getOrderId()); // 日用品
            }
            if (lockVo.getComLocks() != null) {
                lockVo.setComLocks(materialList);
                //给MQ发送消息
                log.info("----------生成订单--------generateCombomaealOrder  lockVo： {}", JSONObject.toJSON(lockVo));
                rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", JSONObject.toJSON(lockVo));
            }
            return orderCombomeal.getOrderNumber();
        }

        return "套餐订单生成失败";
    }

    /**
     * 装修材料添加，锁库存
     *
     * @param materialList
     * @param objectList
     */
    private void assembleMaterial(List<FfwyMaterial> materialList, List<JSONObject> objectList, Long orderId) {
        objectList.forEach(json -> {
            if (json.getLong("number") == null) {
                return;
            }
            FfwyProductSku proSku = ffwyProductSkuService.selectFfwyProductSkuById(json.getLong("productId"));
            FfwyMaterial material = new FfwyMaterial();
            material.setMaterialName(proSku.getSkuName() + "+" + proSku.getSkuSpec()); // 材料名（商品名+规格）
            material.setMaterialNumber(json.getLong("number"));                // 数量
            material.setMaterialPrice(proSku.getPrice());                            // 价格
            material.setOrderCombomealId(orderId);                                  // 订单id
            material.setSkuId(json.getLong("productId"));                    //  skuId
            material.setTagId(json.getInteger("category"));   //分类  1:硬装  2:软装  3:智能家居  4:家电  5:日用品 6:空间布置
            ffwyMaterialMapper.insertFfwyMaterial(material);
            proSku.setStock(proSku.getStock() - json.getLong("number"));  // 减去库存
            ffwyProductSkuService.updateFfwyProductSku(proSku);
            materialList.add(material);

        });
    }

    @Override
    public AjaxResult combomaealGoodsAdd(CombomealGoodsTo combomealGoodsTo) {
        // TODO 使用redis实现用户自选套餐商品保存功能，下单后清空
        //  key:  user;combomaeal;category:用户id;套餐id;套餐装修类型id
        //  hashKey:   商品sku:商品名称(规格)
        //  delta：       数量

        String key = RedisHashUtil.getKey(combomealGoodsTo.getUserId(), combomealGoodsTo.getCombomealId(), combomealGoodsTo.getCategory());
        FfwyProductSku productSku = ffwyProductSkuMapper.selectFfwyProductSkuById(Long.valueOf(combomealGoodsTo.getProductSkuId()));

        if (productSku == null || productSku.getStock() < combomealGoodsTo.getNumber()) {
            AjaxResult.error("库存不足，添加失败！");
        }
        String hashKey = RedisHashUtil.getHashKey(combomealGoodsTo.getProductSkuId(), productSku.getSkuName(), productSku.getSkuSpec());

        Long increment = 0L;

        try {
            increment = redisTemplate.opsForHash().increment(key, hashKey, combomealGoodsTo.getNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return increment > 0 ? AjaxResult.success("添加成功") : AjaxResult.error("添加失败！");
    }

    @Override
    public void combomaealUnlockStock(WareSkuLockVo lockVo) {
        //TODO 1.未支付量房款 解锁库存  成功？删除订单:重新放入套餐订单队列
        //     2.支付量房款  解锁库存  成功？修改订单状态:重新放入套餐订单队列
        log.info("-----------------combomaealUnlockStock lockVo: {}", lockVo);
        FfwyOrderCombomeal orderCombomeal = ffwyOrderCombomealMapper.selectFfwyOrderCombomealByOrderNumber(lockVo.getOrderSn());

        //TODO  只有待付款 和 已付款 两个状态可进入解锁库存，防止mq重复消费
        if (orderCombomeal.getOrderStatus().equals(OrderComStatusEnum.CREATE_NEW.getCode())
                || orderCombomeal.getOrderStatus().equals(OrderComStatusEnum.PAID.getCode())) {

            if (orderCombomeal != null) {
                List<FfwyMaterial> materials = lockVo.getComLocks();
                Boolean aBoolean = ifwyProductSkuService.UnlockStock(materials);

                //1.1如果商品库存解锁成功，修改订单状态
                //1.2失败则重新放入套餐订单队列
                if (aBoolean) {
                    Long orderCombomealId = lockVo.getComLocks().get(0).getOrderCombomealId();
                    // 2.如果未支付量房款,关闭订单
                    if (orderCombomeal.getOrderStatus().equals(OrderComStatusEnum.CREATE_NEW.getCode())) {
//                        ffwyOrderCombomealMapper.deleteFfwyOrderCombomealById(orderCombomealId);
                        ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(new FfwyOrderCombomeal(orderCombomealId, OrderComStatusEnum.CLOSED.getCode()));
                    } else {
                        ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(new FfwyOrderCombomeal(orderCombomealId, OrderComStatusEnum.WFTD.getCode()));
                    }
                } else {
                    rabbitTemplate.convertAndSend("order.release.order.queue.com", JSONObject.toJSON(lockVo));
                }
            }
        }

    }

    @Override
    public int combomaealStatusUpdata(Long orderCombomealId, String status) {
        return ffwyOrderCombomealMapper.updateFfwyOrderCombomeal(new FfwyOrderCombomeal(orderCombomealId, status));
    }

    @Override
    public Map selectfindByproductcat() {
        Map<List<FfwyProductCategory>, List<FfwyProductCategory>> ffwyProductHomeHashMap = new HashMap<>();
        List arrayList = new ArrayList();
        FfwyProductCategory ffwyProductCategory1 = new FfwyProductCategory();
        long parentId = 0;
        ffwyProductCategory1.setParentId(parentId);
        List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryMapper.selectFfwyProductCategoryAll(ffwyProductCategory1);
        FfwyProductCategory ffwyProductCategory = new FfwyProductCategory();
        ffwyProductCategory.setCategoryName(ffwyProductCategories.get(0).getCategoryName());
        List<FfwyProductCategory> ffwyProductCategories1 =
                ffwyProductCategoryMapper.selectFfwyProductCategoryname(ffwyProductCategory);
        if (!StringUtils.isEmpty(ffwyProductCategories1)) {
            arrayList.add(ffwyProductCategories1);
            Long[] ids = new Long[ffwyProductCategories1.size()];
            int i = 0;
            for (FfwyProductCategory productCategory : ffwyProductCategories1) {
                Long categoryId = productCategory.getCategoryId();
                ids[i] = categoryId;
                i++;
            }
            List<FfwyProductCategory> ffwyProductCategories2 = ffwyProductCategoryMapper.selectFfwyCombomealByList(ids);
            arrayList.add(ffwyProductCategories2);
            ffwyProductHomeHashMap.put(ffwyProductCategories1, ffwyProductCategories2);
        }
        return ffwyProductHomeHashMap;
    }

    public List<String> selectFfwyfindByproductcat() {
        return ffwyProductCategoryMapper.selectFfwyfindByproductcat();
    }

    @Override
    public List<FfwyProduct> selectFfwyfindByproductcatName(Long productId) {
        return ffwyProductMapper.selectFfwyfindByproductcatName(productId);
    }

    @Override
    public List<FfwyProductCategory> selectFfwyfindByproductcatdesc(Long categoryId) {
        FfwyProductCategory ffwyProductCategory = new FfwyProductCategory();
        ffwyProductCategory.setParentId(categoryId);
        return ffwyProductCategoryMapper.selectByperentid(ffwyProductCategory);
    }

    @Override
    public List<FfwySpecification> selectFfwyfindByproductcatspe(Long parentId) {
        return ffwySpecificationMapper.selectByspecificationid(parentId);
    }

    @Override
    public List<FfwyProductCategory> selectFfwyfindBysoft() {
        List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryMapper.selectFfwyfindBysoft();
        Long categoryId = ffwyProductCategories.get(0).getCategoryId();
        FfwyProductCategory ffwyProductCategory = new FfwyProductCategory();
        ffwyProductCategory.setParentId(categoryId);
        return ffwyProductCategoryMapper.selectByperentid(ffwyProductCategory);
    }

    @Override
    public List<FfwyProductCategory> selectFfwyfindByarticles() {
        List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryMapper.selectFfwyfindByarticles();
        Long categoryId = ffwyProductCategories.get(0).getCategoryId();
        FfwyProductCategory ffwyProductCategory = new FfwyProductCategory();
        ffwyProductCategory.setParentId(categoryId);
        return ffwyProductCategoryMapper.selectByperentid(ffwyProductCategory);
    }


    @Override
    public List<FfwyProductSku> selectFfwyfindByproductcatsj(Long productId) {
        return ffwyProductSkuMapper.selectFfwyfindByproductcatsj(productId);
    }


    @Override
    public List<String> selectFfwyfindByproductcatej(Long productCategoryId) {
        return ffwyProductMapper.selectFfwyfindByproductcatej(productCategoryId);
    }


    //v生成订单号
    private String orderNumberGenerator() {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String seconds = new SimpleDateFormat("HHmmssSSS").format(new Date());
        return date + "010" + new Random().nextInt(1000) + CombomealGoodsCategory.ORDER_COMBOMEAL + seconds + new Random().nextInt(1000);
    }

    /**
     * 把商品sku id集合放入map, 数量叠加计算
     *
     * @param map
     * @param list
     * @return
     */
    private Map<String, Integer> getproSkuMap(Map<String, Integer> map, List<String> list) {
        for (String s : list) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        return map;
    }

    /**
     * 计算装修价格
     *
     * @param combomealTo
     * @return
     */
    private BigDecimal getCalculatePrice(CombomealTo combomealTo) {
        FfwyCombomeal ffwyCombomeal = ffwyCombomealMapper.selectCombomealByCombomealId(combomealTo.getCombomealId());
        //  价格/每平米  *  房屋平米数
        BigDecimal divide = ffwyCombomeal.getPrices().multiply(new BigDecimal(combomealTo.getSquareMeter()));
        log.info(" 价格/每平米  *  房屋平米数  divide: {}", divide);

        // 硬装商品价格计算
        BigDecimal divideProduct = new BigDecimal("0");
        divideProduct = calculatePrice(combomealTo.getCombomealHards(), divideProduct);
        log.info(" + 硬装商品价格计算  divideProduct: {}", divideProduct);

        // 软装商品价格计算
        divideProduct = calculatePrice(combomealTo.getCombomealSofts(), divideProduct);
        log.info(" + 软装商品价格计算  divideProduct: {}", divideProduct);

        // 智能家居商品价格计算
        divideProduct = calculatePrice(combomealTo.getCombomealSmarts(), divideProduct);
        log.info(" + 智能家居商品价格计算  divideProduct: {}", divideProduct);

        // 家电商品价格计算
        divideProduct = calculatePrice(combomealTo.getCombomealWirings(), divideProduct);
        log.info(" + 家电商品价格计算  divideProduct: {}", divideProduct);

        //  日用品价格计算
        divideProduct = calculatePrice(combomealTo.getCommodityVos(), divideProduct);
        log.info(" + 日用品价格计算  divideProduct: {}", divideProduct);

        BigDecimal decimal = divide.add(divideProduct);
        log.info("FfwyCombomealServiceImpl  getEstimatedPrice  decimal: {}", decimal);
        return decimal;
    }

    private BigDecimal calculatePrice(List<JSONObject> strS, BigDecimal divide) {
        if (null != strS && strS.size() > 0) {
            for (JSONObject str : strS) {
                FfwyProductSku ffwyProductSku = ffwyProductSkuService.selectFfwyProductSkuById(str.getLong("productId"));
                divide = divide.add(ffwyProductSku.getPrice());
            }
        }
        return divide;
    }


}
