package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.combomeal.FfwyCombomeal;
import com.ruoyi.system.domain.combomeal.FfwyCombomealCommentPhoto;
import com.ruoyi.system.domain.product.*;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.to.ProductTo;
import com.ruoyi.system.mapper.FfwySpecificationMapper;
import com.ruoyi.system.mapper.combomeal.*;
import com.ruoyi.system.mapper.product.FfwyProductCategoryMapper;
import com.ruoyi.system.mapper.product.FfwyProductDescMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.mapper.product.FfwyProductPhotoMapper;
import com.ruoyi.system.service.FfwySearchService;
import com.ruoyi.system.service.IFfwyProductDescService;
import com.ruoyi.system.service.IFfwyProductPhotoService;
import com.ruoyi.system.mapper.product.*;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import com.ruoyi.system.service.IFfwyProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


/**
 * 商品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyProductServiceImpl implements IFfwyProductService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FfwyProductServiceImpl.class);
    @Autowired
    private FfwyProductMapper ffwyProductMapper;
    @Autowired
    private FfwyProductCategoryMapper ffwyProductCategoryMapper;
    @Autowired
    private FfwyProductPhotoMapper ffwyProductPhotoMapper;
    @Autowired
    private FfwyProductDescMapper ffwyProductDescMapper;
    @Autowired
    private FfwySpecificationMapper ffwySpecificationMapper;
    @Autowired
    private FfwyShopMapper ffwyShopMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private FfwyProductCommentMapper ffwyProductCommentMapper;
    @Autowired
    private FfwyProductCommentPhotoMapper ffwyProductCommentPhotoMapper;
    @Autowired
    private IFfwyProductPhotoService ffwyProductPhotoService;
    @Autowired
    private IFfwyProductDescService iFfwyProductDescService;
    @Autowired
    private FfwySearchService ffwySearchService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Value("${cos.prefixUrl}")
    private String prefixUrl;
    @Autowired
    private FfwyCombomealMapper ffwyCombomealMapper;
    @Autowired(required = false)
    private FfwyProductSkuMapper ffwyProductSkuMapper;
    @Autowired(required = false)
    private FfwyCombomealSmartMapper ffwyCombomealSmartMapper;
    @Autowired(required = false)
    private FfwyCombomealHardMapper ffwyCombomealHardMapper;
    @Autowired(required = false)
    private FfwyCombomealSoftMapper ffwyCombomealSoftMapper;
    @Autowired(required = false)
    private FfwyCombomealCommodityMapper ffwyCombomealCommodityMapper;
    @Autowired(required = false)
    private FfwyCombomealWiringMapper ffwyCombomealWiringMapper;

    /**
     * 查询商品信息
     *
     * @param productId 商品信息ID
     * @return 商品信息
     */
    @Override
    public FfwyProduct selectFfwyProductById(Long productId) {
        FfwyProduct ffwyProduct = ffwyProductMapper.selectFfwyProductById(productId);
        //获取商品图片
        List<FfwyProductPhoto> productPhotos = ffwyProductPhotoMapper.selectFfwyProductPhotoListByProductId(productId);



        ArrayList<String> photos = new ArrayList<>();
        productPhotos.forEach(productPhoto -> {
            String photoType = productPhoto.getPhotoType();
            String productPhotopath = prefixUrl + productPhoto.getProductPhotopath();
            if (("1").equals(photoType)) {
                photos.add(productPhotopath);
            } else if (("2").equals(photoType)) {
                ffwyProduct.setVideoPath(productPhotopath);
            }
        });
        ffwyProduct.setProductPhoto(photos);
        FfwyProductDesc ffwyProductDesc = new FfwyProductDesc();
        ffwyProductDesc.setProductId(productId);
        List<FfwyProductDesc> ffwyProductDescList = ffwyProductDescMapper.selectFfwyProductDescList(ffwyProductDesc);
        if (null != ffwyProductDescList & ffwyProductDescList.size() > 0)
            ffwyProduct.setDesc(ffwyProductDescList.get(0).getDecript());
        ffwyProduct.setAttr(ffwySpecificationMapper.selectFfwySpecificationByproductId(productId));
        return ffwyProduct;
    }

    @Override
    public FfwyProduct selectStroeProductById(Long productId, Long userId) {
//        return ffwyProductMapper.selectStroeProductById(ffwyProduct);
        return null;
    }

    /**
     * 查询商品信息列表
     *
     * @param ffwyProduct 商品信息
     * @return 商品信息
     */
    @Override
    public List<FfwyProduct> selectFfwyProductList(ProductTo ffwyProduct) {
        Map<Integer, String> map = new HashMap<>();
        List<FfwyProductCategory> ffwyProductCategoryList = ffwyProductCategoryMapper.selectFfwyProductCategoryList(null);
        ffwyProductCategoryList.forEach(ffwyProductCategory -> map.put(ffwyProductCategory.getCategoryId().intValue(),ffwyProductCategory.getCategoryName()));

        //根据分类id进行查询
        List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectProductToList(ffwyProduct);
        LOGGER.info("FfwyProductServiceImpl  selectFfwyProductList  ffwyProducts: ", ffwyProducts);

        ffwyProducts.forEach(product -> {
            //图片集合
            List<FfwyProductPhoto> ffwyProductPhotos = ffwyProductPhotoMapper.selectFfwyProductPhotoListByProductId(product.getProductId());

            ArrayList<String> photos = new ArrayList<>();

            ffwyProductPhotos.forEach(productPhoto -> {
                String photoType = productPhoto.getPhotoType();
                String productPhotopath = prefixUrl + productPhoto.getProductPhotopath();
                if (("1").equals(photoType)) {
                    photos.add(productPhotopath);
                } else if (("2").equals(photoType)) {
                    product.setVideoPath(productPhotopath);
                }

            });
            product.setPhoto(prefixUrl + product.getPhoto());
            product.setProductPhoto(photos);

            //查询销售数量
            FfwyProduct ffwyProduct1 = ffwyProductMapper.selectProductCouont(product.getProductId());
            product.setSales(ffwyProduct1.getSales());

            //查询总库存
            FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductStock(product.getProductId());
            if (null == ffwyProductSku){
                product.setStock(0L);
            } else {
                product.setStock(ffwyProductSku.getStock()-ffwyProduct1.getSales());
            }


            //商品介绍
            FfwyProductDesc ffwyProductDesc = new FfwyProductDesc();
            ffwyProductDesc.setProductId(product.getProductId());
            List<FfwyProductDesc> ffwyProductDescList = ffwyProductDescMapper.selectFfwyProductDescList(ffwyProductDesc);
            if (null != ffwyProductDescList & ffwyProductDescList.size() > 0)
                product.setDesc(ffwyProductDescList.get(0).getDecript());

            //商品规格
            product.setAttr(ffwySpecificationMapper.selectFfwySpecificationByproductId(product.getProductId()));

            product.setProductCategoryName(map.get(product.getProductCategoryId().intValue()));
        });
        //判断类型是否为空
        if (ffwyProduct.getCategoryLevel() != null) {
            List<Long> products = new ArrayList<>();
            List<FfwyProductCategory> ffwyProductCategories = ffwyProductCategoryMapper.selectFfwyProductCategoryList(new FfwyProductCategory(ffwyProduct.getCategoryLevel()));
            ffwyProductCategories.forEach(ffwyProductCategory -> {
                List<FfwyProduct> products1 = ffwyProductMapper.selectFfwyProductListLong(ffwyProductCategory.getCategoryId());
                for (FfwyProduct product : products1) {
                    products.add(product.getProductId());
                }
            });
            for (int i = 0; i < ffwyProducts.size(); i++) {
                if (!products.contains(ffwyProducts.get(i).getProductId())) {
                    ffwyProducts.remove(i);
                    i--;
                }
            }
        }
        return ffwyProducts;
    }

    @Override
    public List<FfwyProduct> selectStoreProductList(Long userId) {

        return setPhoto(ffwyProductMapper.selectStoreProductList(userId));
    }

    /**
     * 新增商品信息
     *
     * @param productTo 商品信息
     * @return 结果
     */
    @Override
    public Long insertFfwyProduct(ProductTo productTo) {
        FfwyProduct ffwyProduct = new FfwyProduct();

        ffwyProduct.setProductName(productTo.getProductName());  //商品名
        ffwyProduct.setProductCategoryId(productTo.getProductCategoryId()); // 分类id
        ffwyProduct.setCreateTime(DateUtils.getNowDate()); // 添加时间
        ffwyProduct.setPhoto(productTo.getPhoto());   //  封面图
        ffwyProduct.setProductStatus(1);            // 上架状态
        ffwyProduct.setShopId(productTo.getShopId()); // 店铺id
        ffwyProduct.setCurrentPrice(new BigDecimal(productTo.getCurrentPrice())); // 价格
        ffwyProduct.setOriginalPrice(new BigDecimal(productTo.getOriginalPrice()));  //原价
        ffwyProduct.setFreight(new BigDecimal(productTo.getFreight()));    // 运费
        ffwyProduct.setDetails(productTo.getDetails());//商品详情  富文本
        ffwyProductMapper.insertFfwyProduct(ffwyProduct);

        List<FfwyProduct> products = ffwyProductMapper.selectFfwyProductList(ffwyProduct);
        try {
            // 上传es
            ffwySearchService.importAll(products.get(0).getProductId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 添加商品轮播图
        String[] productPhoto = productTo.getProductPhoto();
        if (productPhoto.length > 0) {

            for (String photoPath : productPhoto) {
                //7.7 视频和轮播图分开上传  这里只传轮播图 定义为1
                ffwyProductPhotoService.insertFfwyProductPhoto(new FfwyProductPhoto(photoPath.replaceAll(prefixUrl, ""), "1"), products.get(0).getProductId());
            }
        }
        String videoPath = productTo.getVideoPath();

        //添加视频
        if (StringUtils.isNotEmpty(videoPath))
            ffwyProductPhotoService.insertFfwyProductPhoto(new FfwyProductPhoto(videoPath.replaceAll(prefixUrl, ""), "2"), products.get(0).getProductId());

        // 添加商品详情
        //用富文本 去除文字和图片区别
        String productDesc = productTo.getProductDesc();
        if (StringUtils.isNotEmpty(productDesc))
            iFfwyProductDescService.insertFfwyProductDesc(new FfwyProductDesc(productDesc, "0"), products.get(0).getProductId());

        return products.get(0).getProductId();
    }

    @Override
    public AjaxResult insertStroeProduct(FfwyProduct ffwyProduct) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        FfwyProduct product = ffwyProductMapper.selectStroeProduct(userId, ffwyProduct.getProductId());
        if (product.getCountNum() > 0) {
            return AjaxResult.error("该用户已有收藏");
        }
        return AjaxResult.success(ffwyProductMapper.insertStroeProduct(ffwyProduct.getProductId(), userId));
    }

    /**
     * 修改商品信息
     *
     * @param
     * @return 结果
     */
    @Override
    public int updateFfwyProduct(Long productId, String productStatus) {
        FfwyProduct ffwyProduct = new FfwyProduct().setProductId(productId).setProductStatus(Integer.parseInt(productStatus));
        ffwyProduct.setUpdateTime(DateUtils.getNowDate());
        if (1 == ffwyProduct.getProductStatus()) {
            ffwySearchService.importAll(ffwyProduct.getProductId());
        } else if (0 == ffwyProduct.getProductStatus()) {
            //查询分类id
            FfwyProduct ffwyProduct1 = ffwyProductMapper.selectFfwyProductById(productId);
            ffwySearchService.lowerProductSearch(ffwyProduct.getProductId());
            //redis删除
            productCategory(ffwyProduct1.getProductCategoryId());
            //修改套餐商品规格
            combomealDelete(ffwyProduct.getProductId());
        }
        return ffwyProductMapper.updateFfwyProduct(ffwyProduct);
    }

    private void productCategory(Long productCategoryId) {
        redisCache.deleteObject("product :" + productCategoryId);
    }

    public void combomealDelete(Long productId) {
        //下架移除
        List<FfwyCombomeal> ffwyCombomeals = ffwyCombomealMapper.selectFfwyCombomeal(new FfwyCombomeal());
        List<FfwyProductSku> ffwyProductSkus =
                ffwyProductSkuMapper.selectFfwyfindByproductcatsj(productId);
        ffwyCombomeals.forEach(ffwyCombomeal -> {
            //智能家具
            String smartName = ffwyCombomeal.getSmartName();
            Long categoryId = ffwyCombomeal.getCategoryId();
            if (smartName != null && categoryId == 1) {
                if (smartName.indexOf(",") != 0) {
                    String[] split = smartName.split(",");
                    List<String> list1 = Arrays.asList(split);
                    List<String> arrList = new ArrayList<String>(list1);
                    ffwyProductSkus.forEach(ffwyProductSku -> {
                        Long skuId = ffwyProductSku.getSkuId();
                        for (int i = 0; i < arrList.size(); i++) {
                            if (String.valueOf(skuId).equals(arrList.get(i))) {
                                arrList.remove(arrList.get(i));
                                String[] strArrStrings = new String[arrList.size()];
                                String resultString = "";
                                for (int j = 0; j <= arrList.size() - 1; j++) {
                                    strArrStrings[j] = (String) arrList.get(j);
                                }
                                for (int j = 0; j <= strArrStrings.length - 1; j++) {
                                    if (j < strArrStrings.length - 1) {
                                        resultString += strArrStrings[j] + ",";
                                    } else {
                                        resultString += strArrStrings[j];
                                    }
                                }
                                System.out.println("最后拼接的字符串：" + resultString);
                                FfwyCombomeal ffwyCombomeal1 = new FfwyCombomeal();
                                ffwyCombomeal1.setCombomealId(ffwyCombomeal.getCombomealId());
                                ffwyCombomeal1.setSmartName(resultString);
                                ffwyCombomealMapper.updateFfwyCombomeal(ffwyCombomeal1);
                                ffwyCombomealSmartMapper
                                        .deleteFfwyCombomealSmartcId(ffwyCombomeal.getCombomealId());
                            }
                        }
                    });
                }
            }
            //家电
            String applianceName = ffwyCombomeal.getApplianceName();
            if (applianceName != null) {
                if (applianceName.indexOf(",") != 0) {
                    String[] split = applianceName.split(",");
                    List<String> list1 = Arrays.asList(split);
                    List<String> arrList = new ArrayList<String>(list1);
                    ffwyProductSkus.forEach(ffwyProductSku -> {
                        Long skuId = ffwyProductSku.getSkuId();
                        for (int i = 0; i < arrList.size(); i++) {
                            if (String.valueOf(skuId).equals(arrList.get(i))) {
                                arrList.remove(arrList.get(i));
                                String[] strArrStrings = new String[arrList.size()];
                                String resultString = "";
                                for (int j = 0; j <= arrList.size() - 1; j++) {
                                    strArrStrings[j] = (String) arrList.get(j);
                                }
                                for (int j = 0; j <= strArrStrings.length - 1; j++) {
                                    if (j < strArrStrings.length - 1) {
                                        resultString += strArrStrings[j] + ",";
                                    } else {
                                        resultString += strArrStrings[j];
                                    }
                                }
                                System.out.println("最后拼接的字符串：" + resultString);
                                FfwyCombomeal ffwyCombomeal1 = new FfwyCombomeal();
                                ffwyCombomeal1.setCombomealId(ffwyCombomeal.getCombomealId());
                                ffwyCombomeal1.setApplianceName(resultString);
                                ffwyCombomealMapper.updateFfwyCombomeal(ffwyCombomeal1);
                                ffwyCombomealWiringMapper
                                        .deleteFfwyCombomealWiringcId(ffwyCombomeal.getCombomealId());
                            }
                        }
                    });
                }
            }
            //生活日用品
            String articlesName = ffwyCombomeal.getArticlesName();
            if (articlesName != null)
                if (articlesName.indexOf(",") != 0) {
                    String[] split = articlesName.split(",");
                    List<String> list1 = Arrays.asList(split);
                    List<String> arrList = new ArrayList<String>(list1);
                    ffwyProductSkus.forEach(ffwyProductSku -> {
                        Long skuId = ffwyProductSku.getSkuId();
                        for (int i = 0; i < arrList.size(); i++) {
                            if (String.valueOf(skuId).equals(arrList.get(i))) {
                                arrList.remove(arrList.get(i));
                                String[] strArrStrings = new String[arrList.size()];
                                String resultString = "";
                                for (int j = 0; j <= arrList.size() - 1; j++) {
                                    strArrStrings[j] = (String) arrList.get(j);
                                }
                                for (int j = 0; j <= strArrStrings.length - 1; j++) {
                                    if (j < strArrStrings.length - 1) {
                                        resultString += strArrStrings[j] + ",";
                                    } else {
                                        resultString += strArrStrings[j];
                                    }
                                }
                                System.out.println("最后拼接的字符串：" + resultString);
                                FfwyCombomeal ffwyCombomeal1 = new FfwyCombomeal();
                                ffwyCombomeal1.setCombomealId(ffwyCombomeal.getCombomealId());
                                ffwyCombomeal1.setArticlesName(resultString);
                                ffwyCombomealMapper.updateFfwyCombomeal(ffwyCombomeal1);
                                ffwyCombomealCommodityMapper.deleteFfwyCombomealCommoditycId(ffwyCombomeal.getCombomealId());
                            }
                        }
                    });
                }
            //软装
            String softOutfit = ffwyCombomeal.getSoftOutfit();
            if (softOutfit != null) {
                if (softOutfit.indexOf(",") != 0) {
                    String[] split = softOutfit.split(",");
                    List<String> list1 = Arrays.asList(split);
                    List<String> arrList = new ArrayList<String>(list1);
                    ffwyProductSkus.forEach(ffwyProductSku -> {
                        Long skuId = ffwyProductSku.getSkuId();
                        for (int i = 0; i < arrList.size(); i++) {
                            if (String.valueOf(skuId).equals(arrList.get(i))) {
                                arrList.remove(arrList.get(i));
                                String[] strArrStrings = new String[arrList.size()];
                                String resultString = "";
                                for (int j = 0; j <= arrList.size() - 1; j++) {
                                    strArrStrings[j] = (String) arrList.get(j);
                                }
                                for (int j = 0; j <= strArrStrings.length - 1; j++) {
                                    if (j < strArrStrings.length - 1) {
                                        resultString += strArrStrings[j] + ",";
                                    } else {
                                        resultString += strArrStrings[j];
                                    }
                                }
                                System.out.println("最后拼接的字符串：" + resultString);
                                FfwyCombomeal ffwyCombomeal1 = new FfwyCombomeal();
                                ffwyCombomeal1.setCombomealId(ffwyCombomeal.getCombomealId());
                                ffwyCombomeal1.setSoftOutfit(resultString);
                                ffwyCombomealMapper.updateFfwyCombomeal(ffwyCombomeal1);
                                ffwyCombomealSoftMapper.deleteFfwyCombomealSoftcId(ffwyCombomeal.getCombomealId());
                            }
                        }
                    });
                }
            }


        });
    }

    /**
     * 批量删除商品信息
     *
     * @param productIds 需要删除的商品信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductByIds(Long[] productIds) {
        return ffwyProductMapper.deleteFfwyProductByIds(productIds);
    }

    /**
     * 删除商品信息信息
     *
     * @param productId 商品信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductById(Long productId) {
        return ffwyProductMapper.deleteFfwyProductById(productId);
    }


    @Override
    public AjaxResult deleteStroeProduct(FfwyProduct ffwyProduct) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        return AjaxResult.success(ffwyProductMapper.deleteStroeProduct(ffwyProduct.getProductId(), userId));
    }

    //获取单个商品
    @Override
    public List<FfwyProduct> selectFfwyProductListId(FfwyProduct ffwyProduct) {
        //根据商品id
        List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyProductList(ffwyProduct);
        LOGGER.info("FfwyProductServiceImpl  selectFfwyProductList  ffwyProducts:{} ", ffwyProducts);
        ffwyProducts.forEach(product -> {
            product.setPhoto(prefixUrl + product.getPhoto());
            //获取轮播图
            List<FfwyProductPhoto> ffwyProductPhotos = ffwyProductPhotoMapper.selectFfwyProductPhotoListByProductId(product.getProductId());
            ArrayList<String> photos = new ArrayList<>();

            ffwyProductPhotos.forEach(productPhoto -> {
                productPhoto.setProductPhotopath(prefixUrl + productPhoto.getProductPhotopath());
                String photoType = productPhoto.getPhotoType();
                String productPhotopath = prefixUrl + productPhoto.getProductPhotopath();
                if (("1").equals(photoType)) {
                    photos.add(productPhotopath);
                } else if (("2").equals(photoType)) {
                    ffwyProduct.setVideoPath(productPhotopath);
                }
            });

            product.setProductPhoto(photos);
            product.setProductPhotos(ffwyProductPhotos);
            LOGGER.info("FfwyProductServiceImpl  selectFfwyProductList  product:{} ", product);
            //获取规格
            List<FfwySpecification> ffwySpecifications = ffwySpecificationMapper.selectFfwySpecificationByproductId(product.getProductId());
            ffwySpecifications.forEach(ffwySpecification -> {
                String valueSelect = ffwySpecification.getValueSelect();
                // 判断有没有多个规格
                if (valueSelect.indexOf(";") == 0) {
                    String value = valueSelect.substring(0, valueSelect.indexOf(";"));
                    ffwySpecification.setValueSelect(value);
                }
                String value = valueSelect;
                ffwySpecification.setValueSelect(value);

            });

            product.setAttr(ffwySpecifications);
            //获取评价总数量
            FfwyProductComment ffwyProductComments =
                    ffwyProductCommentMapper.selectFfwyProductCommentByproductId(product.getProductId());
            product.setSumComment(ffwyProductComments.getSumComment());
            LOGGER.info("FfwyProductServiceImpl  selectFfwyProductList  product.setProductComment():{} ", product);
            //获取评价最新的一条
            product.setProductCommentData(ffwyProductCommentMapper.selectFfwyProductCommentArticle(product.getProductId()));
            product.getProductCommentData().forEach(pr -> {
                pr.setProductCommentPhoto(prefixUrl + pr.getProductCommentPhoto());
                pr.setPhoto(prefixUrl + pr.getPhoto());
                Long productCommentId = pr.getProductCommentId();
                FfwyProductCommentPhoto ffwyCombomealCommentPhoto = new FfwyProductCommentPhoto(productCommentId);
                List<FfwyProductCommentPhoto> ffwyProductCommentPhotos = ffwyProductCommentPhotoMapper.selectFfwyProductCommentPhotoList(ffwyCombomealCommentPhoto);
                ffwyProductCommentPhotos.forEach(ffwyProductCommentPhoto -> {
                    ffwyProductCommentPhoto.setProductCommentPhoto(prefixUrl + ffwyProductCommentPhoto.getProductCommentPhoto());
                });
                pr.setPhotot(ffwyProductCommentPhotos);
                pr.getFfwyUser().setPhoto(prefixUrl + pr.getFfwyUser().getPhoto());
            });
            //获取店铺  获取店铺的名字
            List<FfwyShop> ffwyShops = ffwyShopMapper.selectFfwyShopIdList(product.getShopId());
            //店铺收藏数量
            FfwyShop ffwyShops1 = ffwyShopMapper.selectFfwyStoreShopCount(product.getShopId());
            ffwyShops.forEach(ffwyShop -> {
                ffwyShop.setCollect(ffwyShops1.getCollect());
                ffwyShop.setShopLogo(prefixUrl + ffwyShop.getShopLogo());
            });
            product.setShop(ffwyShops);
            LOGGER.info("FfwyProductServiceImpl  selectFfwyProductList  product.getShopId():{} ", product.getShopId());
            //获取详情内容
            //           List<FfwyProductDesc> ffwyProductDescs =
            ///               ffwyProductDescMapper.selectSpecificationById(product
            //               .getProductId());
//            ffwyProductDescs.forEach(ffwyProductDesc -> {
//                if (ffwyProductDesc.getDescType().equals("1")) {
//                    ffwyProductDesc.setDecript(prefixUrl + ffwyProductDesc.getDecript());
//                }
//            });
            FfwyProductDesc ffwyProductDesc = new FfwyProductDesc();
            ffwyProductDesc.setProductId(product.getProductId());
            List<FfwyProductDesc> ffwyProductDescList = ffwyProductDescMapper.selectFfwyProductDescList(ffwyProductDesc);
            if (null != ffwyProductDescList & ffwyProductDescList.size() > 0)
                product.setDesc(ffwyProductDescList.get(0).getDecript());

            //获取真实用户的id
            //随意在任何地方获取请求响应对象
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            //真实用户
            if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
                Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
                FfwyProduct ffwyProduct1 = new FfwyProduct();
                ffwyProduct1.setProductId(product.getProductId());
                ffwyProduct1.setUserId(userId);
                FfwyProduct productList = ffwyProductMapper.selectStroeProductById(ffwyProduct1);
                if (productList != null) {
                    product.setProducts(productList.getUserCount());
                } else if (productList == null) {
                    product.setProducts(0);
                }

            }
        });
        return ffwyProducts;
    }


    @Override
    public List<FfwyProduct> selectClassifyList(FfwyProduct ffwyProduct) {
        //如果redis里面有数据的时候
        List<FfwyProduct> list = ffwyProductMapper.selectFfwyProductCategoryList(ffwyProduct);
        FfwyProduct countList =
                ffwyProductMapper.selectFfwyProductCategoryCountList(ffwyProduct.getProductCategoryId());
        List<FfwyProduct> cacheList =
                redisCache.getCacheList("product :" + ffwyProduct.getProductCategoryId());
        if (cacheList.size() == countList.getCountNum()) {
            List page = page(cacheList, ffwyProduct.getPageNum(), ffwyProduct.getPageSize());
            return page;
        } else {
            redisCache.deleteObject("product :" + ffwyProduct.getProductCategoryId());
        }
        if (list.size() != 0) {
            LOGGER.info("FfwyProductServiceImpl  selectClassifyList  ffwyProducts:{} ", list);
            //获取商品的名字  价格  商品的销量   店铺的名字
            list.forEach(product -> {
                //获取店铺的名字
                product.setPhoto(prefixUrl + product.getPhoto());
                List<FfwyShop> ffwyShops = ffwyShopMapper.selectFfwyShopIdList(product.getShopId());
                ffwyShops.forEach(f -> {
                    f.setShopLogo(prefixUrl + f.getShopLogo());
                });
                product.setShop(ffwyShops);
                LOGGER.info("FfwyProductServiceImpl  selectClassifyList  product:{} ", product);
            });
            //规格ID 往redis里面添加数据
            redisCache.setCacheList("product :" + list.get(0).getProductCategoryId(), list);
        }
        return list;
    }


    /**
     * 循环截取某页列表进行分页
     * @param dataList 分页数据
     * @param pageSize  页面大小
     * @param pageNum   当前页面
     */
    public static List<FfwyProduct> page(List<FfwyProduct> dataList, int pageNum,
                                 int pageSize) {
        List<FfwyProduct> currentPageList = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            int currIdx = (pageNum > 1 ? (pageNum - 1) * pageSize : 0);
            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
                FfwyProduct data = dataList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }

    /**
     * 修改商品信息
     *
     * @param productTo
     * @return
     */
    @Override
    public Long updataProductSku(ProductTo productTo) {

        FfwyProduct ffwyProduct = new FfwyProduct();
        ffwyProduct.setProductId(productTo.getProductId());
        ffwyProduct.setProductName(productTo.getProductName());  //商品名
        ffwyProduct.setProductCategoryId(productTo.getProductCategoryId()); // 分类id
        ffwyProduct.setCreateTime(DateUtils.getNowDate()); // 添加时间
        ffwyProduct.setPhoto(productTo.getPhoto().replaceAll(prefixUrl, ""));   //  封面图
        ffwyProduct.setProductStatus(1);            // 上架状态
        ffwyProduct.setShopId(productTo.getShopId()); // 店铺id
        ffwyProduct.setCurrentPrice(new BigDecimal(productTo.getCurrentPrice())); // 价格
        ffwyProduct.setOriginalPrice(new BigDecimal(productTo.getOriginalPrice()));  //原价
        ffwyProduct.setFreight(new BigDecimal(productTo.getFreight()));    // 运费
        ffwyProduct.setDetails(productTo.getDetails());
        ffwyProductMapper.updateFfwyProduct(ffwyProduct);

        List<FfwyProduct> products = ffwyProductMapper.selectFfwyProductList(ffwyProduct);
        try {
            // 上传es
            ffwySearchService.importAll(products.get(0).getProductId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //删除商品轮播图
        ffwyProductPhotoService.deleteFfwyProductPhotoByProductId(productTo.getProductId());

        // 添加商品轮播图
        String[] productPhoto = productTo.getProductPhoto();
        if (productPhoto.length > 0) {
            for (String photoPath : productPhoto) {
                //7.7 视频和轮播图分开上传  这里只传轮播图 定义为1
                ffwyProductPhotoService.insertFfwyProductPhoto(new FfwyProductPhoto(photoPath.replaceAll(prefixUrl, ""), "1"), products.get(0).getProductId());
            }
        }
        String videoPath = productTo.getVideoPath();

        //添加视频
        if (StringUtils.isNotEmpty(videoPath))
            ffwyProductPhotoService.insertFfwyProductPhoto(new FfwyProductPhoto(videoPath.replaceAll(prefixUrl, ""), "2"), products.get(0).getProductId());


        //删除商品详情
        iFfwyProductDescService.deleteFfwyProductDescByProductId(productTo.getProductId());
        // 添加商品详情
        //用富文本 去除文字和图片区别
        String productDesc = productTo.getProductDesc();
        if (StringUtils.isNotEmpty(productDesc))
            iFfwyProductDescService.insertFfwyProductDesc(new FfwyProductDesc(productDesc, "0"), products.get(0).getProductId());

        return products.get(0).getProductId();
    }

    private List<FfwyProduct> setPhoto(List<FfwyProduct> products) {
        products.forEach(ffwyProduct -> {
            ffwyProduct.setPhoto(CosCofig.getPrefixUrl() + ffwyProduct.getPhoto());
        });
        return products;
    }
}
