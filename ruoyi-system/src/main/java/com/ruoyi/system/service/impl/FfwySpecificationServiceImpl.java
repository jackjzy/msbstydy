package com.ruoyi.system.service.impl;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.Model;

import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.to.ProductSkuTo;
import com.ruoyi.system.domain.vo.SpecificationSort;
import com.ruoyi.system.domain.product.FfwySpecification;
import com.ruoyi.system.domain.vo.SpeVO;
import com.ruoyi.system.mapper.FfwySpecificationMapper;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.service.IFfwyProductSkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import com.ruoyi.system.mapper.product.FfwyProductSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwySpecificationService;

/**
 * 订单规格Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-16
 */
@Service
@Slf4j
public class FfwySpecificationServiceImpl implements IFfwySpecificationService {

    @Autowired
    private FfwySpecificationMapper ffwySpecificationMapper;
    @Autowired
    private FfwyProductSkuMapper ffwyProductSkuMapper;
    @Autowired
    private IFfwyProductSkuService iFfwyProductSkuService;
    @Autowired
    private FfwyProductMapper ffwyProductMapper;
    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;

    @Autowired
    private FfwyProductServiceImpl ffwyProductService;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    /**
     * 查询订单规格
     *
     * @param specificationId 订单规格ID
     * @return 订单规格
     */
    @Override
    public FfwySpecification selectFfwySpecificationById(Integer specificationId) {

        return ffwySpecificationMapper.selectFfwySpecificationById(specificationId);
    }

    /**
     * 查询订单规格列表
     *
     * @param ffwySpecification 订单规格
     * @return 订单规格
     */
    @Override
    public AjaxResult selectFfwySpecificationList(FfwySpecification ffwySpecification) {
        FfwyProductSku sku = new FfwyProductSku();
        sku.setProductId(ffwySpecification.getProductId());
        sku.setSkuSpec(ffwySpecification.getValue());
        List<FfwyProductSku> ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuList(sku);
        //商品图片名字
        //商品库存
        //查询购物是查询库存
        ffwyProductSku.forEach(productSku -> {
            ArrayList<FfwyProductSku> list = new ArrayList<>();
            //拿到规格的库存
            for (int i = 0; i < ffwyProductSku.size(); i++) {
                FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
                ffwyOrderDetails.setSkuId(ffwyProductSku.get(i).getSkuId());
                //剩余库存
                FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails);
                if (orderDetails == null) {
                    Long stock = ffwyProductSku.get(i).getStock();
                    ffwyProductSku.get(i).setNum(stock);
                    list.add(ffwyProductSku.get(i));
                } else {
                    long num = ffwyProductSku.get(i).getStock() - orderDetails.getNum();
                    ffwyProductSku.get(i).setNum(num);
                    list.add(ffwyProductSku.get(i));
                    if (num == 0) {
                        continue;
                    } else if (ffwyProductSku.get(i).getNum() > 0) {
                        list.add(ffwyProductSku.get(i));
                    }
                }
                ffwySpecification.setSku(list);
            }
        });
        if (ffwySpecification.getSku() == null) {
            List<FfwySpecification> ffwySpecifications = ffwySpecificationMapper.selectFfwySpecificationList(ffwySpecification);
            ffwySpecifications.forEach(pr -> {
                pr.setValues(pr.getValueSelect().split(";"));
            });
            boolean empty = ffwySpecifications.isEmpty();
            if (empty) {
                return AjaxResult.success("请添加规格!");
            }
            return AjaxResult.success(ffwySpecification);
        }
        ffwySpecification.getSku().forEach(sku1 -> {
            sku1.setValues(sku1.getSkuSpec().split(";"));
            sku1.setSkuDefaultImg(prefixUrl + sku1.getSkuDefaultImg());
        });
        List<FfwySpecification> ffwySpecifications = ffwySpecificationMapper.selectFfwySpecificationList(ffwySpecification);
        ffwySpecifications.forEach(pr -> {
            pr.setValues(pr.getValueSelect().split(";"));
        });
        ffwySpecification.setSpecifications(ffwySpecifications);
        return AjaxResult.success(ffwySpecification);
    }

    @Override
    public List<JSONObject> selectSpeVoList(Long productId) {
        List<FfwySpecification> ffwySpecifications = ffwySpecificationMapper.selectFfwySpecificationByproductId(productId);
        Map<String, List<String>> map = new HashMap<>();

        // 把productId商品下每一个规格对象中的规格名和规格值放入map
        ffwySpecifications.forEach(ffwySpecification -> {
            String[] split = ffwySpecification.getValueSelect().split(SpecificationSort.SEPARATOR);
            map.put(ffwySpecification.getSpecificationName(), Arrays.asList(split));
        });
        //  map排序组合
//        List<SpeVO> speVOS = SpecificationSort.specificationMapAscendTree(map, productId);
        List<JSONObject> jsonObjects = SpecificationSort.specificationMapAscendJSON(map);

        List<FfwyProductSku> ffwyProductSkus = iFfwyProductSkuService.selectFfwyProductSkuList(new FfwyProductSku(productId));

        ffwyProductSkus.stream().forEach(productSku -> productSku.setSkuDefaultImg(prefixUrl + productSku.getSkuDefaultImg()));

        // 规格 和 sku商品组合
        for (JSONObject jsonObject : jsonObjects) {
            String string = jsonObject.getString(SpecificationSort.SELECTVALUE);
            jsonObject.put(SpecificationSort.SKU, SpecificationSort.contrastValue(string, ffwyProductSkus, productId));
            jsonObject.remove(SpecificationSort.SELECTVALUE);
        }
        log.info("FfwySpecificationServiceImpl  selectSpeVoList  speVOS: {}", jsonObjects);

        return jsonObjects;
    }

    @Override
    public ProductSkuTo selectAdminSpeVoList(Long productId) {
        ProductSkuTo productSkuTo = new ProductSkuTo();

        productSkuTo.setProductId(productId);

        List<FfwySpecification> ffwySpecifications = ffwySpecificationMapper.selectFfwySpecificationByproductId(productId);
        List<SpeVO> speVOList = new ArrayList<SpeVO>();


        ffwySpecifications.forEach(ffwySpecification -> speVOList.add(new SpeVO(ffwySpecification.getSpecificationId(), ffwySpecification.getProductId(),
                ffwySpecification.getSpecificationName(), JSONObject.toJSONString(ffwySpecification.getValueSelect().split(";")))));


        productSkuTo.setSpeVOS(speVOList);

        List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyfindByproductcatsj(productId);

        ffwyProductSkus.stream().forEach(productSku -> productSku.setSkuDefaultImg(prefixUrl + productSku.getSkuDefaultImg()));

        productSkuTo.setProductSkus(ffwyProductSkus);

        return productSkuTo;
    }


    /**
     * 1.  规格的新增以及修改
     * 1.1  （speId == null?新增:修改）
     * 1.2 删除： 查出库里此商品下所有规格，与传进来的规格id进行对比，多出来的删除
     * 2.  sku的新增以及修改
     * 2.1  删除ProductId商品下所有原有sku
     * 2.2  新增  SkuId == null
     * 增加新收到的sku
     * 修改  SkuId ！= null
     * 直接修改
     *
     * @param productSku
     * @return
     */
    @Override
    public AjaxResult insertOrpdataProductSku(ProductSkuTo productSku) {
        log.info("FfwySpecificationServiceImpl  insertOrpdataProductSku  productSku: {}", productSku);

        FfwyProduct product = ffwyProductMapper.selectFfwyProductById(productSku.getProductId());
        if (product == null) {
            return AjaxResult.error("请先添加商品基础信息！");
        }
        // 1. 更新商品规格
        List<SpeVO> speVOS = productSku.getSpeVOS();
        //2:获取原有的商品规格并进行逻辑删除
        List<FfwySpecification> ffwySpecificationList = ffwySpecificationMapper.selectFfwySpecificationByproductId(productSku.getProductId());
        if (ffwySpecificationList.size() > 0 & speVOS.size() > 0) {
            //逻辑删除
            if (speVOS.get(0).getSpeId() == null)
                ffwySpecificationMapper.updateFfwySpecification(new FfwySpecification().setProductId(productSku.getProductId()).setEnable(0));
        }
        //  1.2 新增、修改商品规格
        speVOS.forEach(speVO -> speVO.setProductId(productSku.getProductId()));
        speVOS.forEach(this::insertOrUpdataSpeVO);


        // 2.更新商品sku
        List<FfwyProductSku> productSkus = productSku.getProductSkus();

        //   2.1 先遍历一遍看集合中是否有空的SkuId   有则说明是新增，就把SkuId商品下所有原有sku清空
        List<FfwyProductSku> ffwyProductSkuList = ffwyProductSkuMapper.selectFfwyfindByproductcatsj(productSku.getProductId());
        if (productSkus.size() > 0 & ffwyProductSkuList.size() > 0) {
            if (null == productSkus.get(0).getSkuId()) {
                //  删除商品下原有sku
                ffwyProductService.combomealDelete(product.getProductId());
                ffwyProductSkuMapper.updateFfwyProductSku(new FfwyProductSku().setProductId(productSku.getProductId()).setIsOpen(2));

            }
        }

        //    2.2 更新
        productSkus.forEach(sku -> {
            sku.setProductId(product.getProductId());    //  商品id
            sku.setSkuSpec(assembleValues(sku.getValues()));  // 规格
            sku.setSkuName(product.getProductName());   //  商品名
            sku.setCatalogId(product.getProductCategoryId());  // 所属分类
            if (sku.getSkuId() == null) {
                ffwyProductSkuMapper.insertFfwyProductSku(sku);
            } else {
                ffwyProductSkuMapper.updateFfwyProductSku(sku);
            }
        });

        return AjaxResult.success("添加成功！");
    }

    private String assembleValues(String[] str) {
        String value = null;
        for (String speValue : str) {
            value = (value == null) ? speValue : value + ";" + speValue;
        }
        return value;
    }

    private int insertOrUpdataSpeVO(SpeVO speVO) {
        String value = assembleValues(speVO.getSpeValues());

        FfwySpecification ffwySpecification = new FfwySpecification(speVO.getSpeId(), speVO.getSpeName(), speVO.getProductId(), value);
        log.info("FfwySpecificationServiceImpl  insertOrUpdataSpecification  ffwySpecification:" + ffwySpecification);
        return ffwySpecificationMapper.selectSpecificationNameAndProductId(speVO.getProductId(), speVO.getSpeName())
                == null ? ffwySpecificationMapper.insertFfwySpecification(ffwySpecification) :
                ffwySpecificationMapper.updateFfwySpecification(ffwySpecification);
    }


    @Override
    public int insertOrUpdataSpecification(SpeVO speVO) {
        return insertOrUpdataSpeVO(speVO);
    }

    @Override
    public int insertOrUpdata(FfwySpecification ffwySpecification) {
        for (String value : ffwySpecification.getValues()) {
            if (StringUtils.isNotEmpty(ffwySpecification.getValueSelect())) {
                ffwySpecification.setValueSelect(ffwySpecification.getValueSelect() + ";" + value);
            } else {
                ffwySpecification.setValueSelect(value);
            }
        }
        FfwySpecification ffwySpecificationNew = ffwySpecificationMapper.selectSpecificationNameAndProductId(ffwySpecification.getProductId(), ffwySpecification.getSpecificationName());

        return ffwySpecificationNew != null ? ffwySpecificationMapper.updateFfwySpecification(ffwySpecification) :
                ffwySpecificationMapper.insertFfwySpecification(ffwySpecification);
    }

    /**
     * 修改订单规格
     *
     * @param ffwySpecification 订单规格
     * @return 结果
     */
    @Override
    public int updateFfwySpecification(FfwySpecification ffwySpecification) {
        return ffwySpecificationMapper.updateFfwySpecification(ffwySpecification);
    }

    /**
     * 批量删除订单规格
     *
     * @param specificationIds 需要删除的订单规格ID
     * @return 结果
     */
    @Override
    public int deleteFfwySpecificationByIds(Integer[] specificationIds) {
        return ffwySpecificationMapper.deleteFfwySpecificationByIds(specificationIds);
    }

    /**
     * 删除订单规格信息
     *
     * @param specificationId 订单规格ID
     * @return 结果
     */
    @Override
    public int deleteFfwySpecificationById(Integer specificationId) {
        return ffwySpecificationMapper.deleteFfwySpecificationById(specificationId);
    }

    @Override
    public AjaxResult selectFfwySpecificationListById(Long productId, String value) {
        FfwySpecification ffwySpecification = new FfwySpecification();
        //拿出每一个规格
        List<FfwySpecification> ffwySpecifications = ffwySpecificationMapper.selectFfwySpecificationByproductId(productId);
        ffwySpecifications.forEach(ff -> {
            ff.setValues(ff.getValueSelect().split(";"));
        });
        //根据分类查询分类下面的库存
        //根据关键字模糊查询id
        FfwyProductSku sku = new FfwyProductSku();
        sku.setSkuSpec(value);
        sku.setProductId(productId);
        List<FfwyProductSku> ffwyProductSkus = ffwyProductSkuMapper.selectFfwyProductSkunUmber(sku);
        if (ffwyProductSkus == null) {
            return AjaxResult.success(ffwySpecification);
        } else {
            List<FfwyProductSku> list = new ArrayList<FfwyProductSku>();
            for (int i = 0; i < ffwyProductSkus.size(); i++) {
                FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
                ffwyOrderDetails.setSkuId(ffwyProductSkus.get(i).getSkuId());
                FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails);
                //没有下单
                if (orderDetails == null) {
                    Long stock = ffwyProductSkus.get(i).getStock();
                    ffwyProductSkus.get(i).setNum(stock);
                    list.add(ffwyProductSkus.get(i));
                    ffwySpecification.setSku(list);
                } else {
                    //下单
                    long num = ffwyProductSkus.get(i).getStock() - orderDetails.getNum();
                    ffwyProductSkus.get(i).setNum(num);
                    list.add(ffwyProductSkus.get(i));
                    ffwySpecification.setSku(list);
                }
                String skuSpec = list.get(i).getSkuSpec();
                String[] split = skuSpec.split(";");
                ffwyProductSkus.forEach(f -> {
                    ArrayList<String> list1 = new ArrayList<>();
                    for (int i1 = 0; i1 < split.length; i1++) {
                        if (value.equals(split[i1])) {
                            f.setSpecification(value);
                        }
                        if (!value.equals(split[i1])) {
                            String s = split[i1];
                            list1.add(s);
                            f.setValue(list1);
                        }
                    }
                });
                ffwySpecification.setFfwySpecifications(ffwySpecifications);
                ffwySpecification.setSku(ffwyProductSkus);

            }
            return AjaxResult.success(ffwySpecification);
        }

    }


}
