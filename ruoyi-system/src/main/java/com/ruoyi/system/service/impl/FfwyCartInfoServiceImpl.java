package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import com.ruoyi.common.config.AuthContextHolder;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.cart.FfwyCartInfo;
import com.ruoyi.system.domain.order.FfwyConsigneeAddr;
import com.ruoyi.system.domain.order.FfwyOrder;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.mapper.cart.FfwyCartInfoMapper;
import com.ruoyi.system.mapper.order.FfwyConsigneeAddrMapper;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.mapper.product.FfwyProductSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyCartInfoService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 购物车 Service业务层处理
 *
 * @author 郭晓康
 * @date 2021-05-14
 */
@Service
public class FfwyCartInfoServiceImpl implements IFfwyCartInfoService {
    @Autowired
    private FfwyCartInfoMapper ffwyCartInfoMapper;
    @Autowired
    private FfwyProductSkuMapper ffwyProductSkuMapper;
    @Autowired
    private FfwyProductMapper ffwyProductMapper;
    @Autowired
    private FfwyConsigneeAddrMapper ffwyConsigneeAddrMapper;
    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;
    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    /**
     * 查询购物车
     *
     * @param id 购物车 ID
     * @return 购物车
     */
    @Override
    public FfwyCartInfo selectFfwyCartInfoById(Long id) {
        return ffwyCartInfoMapper.selectFfwyCartInfoById(id);
    }

    /**
     * 查询购物车 列表
     *
     * @param ffwyCartInfo 购物车
     * @return 购物车
     */
    @Override
    public AjaxResult selectFfwyCartInfoList(FfwyCartInfo ffwyCartInfo) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取当前用户传入进来的数量
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        //用户id
        List<FfwyCartInfo> ffwyCartInfos = ffwyCartInfoMapper.selectFfwyCartInfoList(ffwyCartInfo);
        for (int i = 0; i < ffwyCartInfos.size(); i++) {
            if (ffwyCartInfos.get(i).getUserId().equals(userId)) {
                ffwyCartInfos.get(i).setImgUrl(prefixUrl + ffwyCartInfos.get(i).getImgUrl());
                //查询购物是查询库存
                FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuById(ffwyCartInfos.get(i).getSkuId());
                FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
                ffwyOrderDetails.setSkuId(ffwyCartInfos.get(i).getSkuId());
                //剩余库存
                FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails);
                if (orderDetails == null) {
                    ffwyCartInfos.get(i).setNum(ffwyProductSku.getStock());
                } else if (orderDetails != null && !orderDetails.equals("")) {
                    if (ffwyProductSku.getStock() != null) {
                        long num = ffwyProductSku.getStock() - orderDetails.getNum();
                        //剩余多少库存 如果为
                        //空是不让添加
                        if (num > 0) {
                            ffwyCartInfos.get(i).setNum(num);
                            ffwyCartInfos.get(i).getSkuNum();
                        } else if (num < 0) {
                            //更新数量为空
                            ffwyCartInfos.get(i).setSkuNum(0);

                        }
                    }

                }

            }
        }

        return AjaxResult.success("查询成功", ffwyCartInfos);
    }


    /**
     * 新增购物车
     *
     * @param ffwyCartInfo 购物车
     * @return 结果
     */
    @Override
    public AjaxResult insertFfwyCartInfo(FfwyCartInfo ffwyCartInfo) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
            //获取当前用户传入进来的数量
            Long skuNum = Long.valueOf(ffwyCartInfo.getSkuNum());
            //真实用户
            Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
            if (userId != null) {
                //查询购物车有没有此数据
                //根据用户的skuid跟用户的id去查询购物车
                ffwyCartInfo.setUserId(userId);
                List<FfwyCartInfo> ffwyCartInfos = ffwyCartInfoMapper.selectFfwyCartInfoList(ffwyCartInfo);
                if (ffwyCartInfos.size() > 0) {
                    for (int i = 0; i < ffwyCartInfos.size(); i++) {
                        //查询购物是查询库存
                        FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuById(ffwyCartInfos.get(i).getSkuId());
                        FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
                        ffwyOrderDetails.setSkuId(ffwyCartInfos.get(i).getSkuId());
                        //剩余库存
                        FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails);
                        //不存在
                        if (orderDetails == null && ffwyProductSku.getStock() > 0) {
                            if (ffwyCartInfos.size() > 0) {
                                //有此数据追加数量
                                ffwyCartInfo.setId(ffwyCartInfos.get(i).getId());
                                ffwyCartInfo.setSkuNum(ffwyCartInfos.get(i).getSkuNum() + ffwyCartInfo.getSkuNum());
                                ffwyCartInfo.setIsChecked(1);
                                int j = ffwyCartInfoMapper.updateFfwyCartInfo(ffwyCartInfo);
                                return AjaxResult.success("追加数量:", j);
                            } else {
                                //不存在 新增一个商品
                                FfwyCartInfo cartInfo = new FfwyCartInfo();
                                //设置用户ID
                                cartInfo.setUserId(userId);
                                //设置SkuId
                                cartInfo.setSkuId(ffwyCartInfo.getSkuId());
                                //设置数量
                                cartInfo.setSkuNum(ffwyCartInfo.getSkuNum());
                                FfwyProductSku ffwyProduct = ffwyProductSkuMapper.selectFfwyProductCartById(ffwyCartInfo.getSkuId());
                                FfwyProduct product = ffwyProductMapper.selectFfwyProductById(ffwyProduct.getProductId());
                                //获取价格
                                cartInfo.setCartPrice(ffwyProduct.getCurrentPrice());
                                //名称
                                cartInfo.setSkuName(ffwyProduct.getProductName());
                                //图片
                                cartInfo.setImgUrl(ffwyProduct.getSkuDefaultImg());
                                //规格
                                cartInfo.setSkuSpec(ffwyProduct.getSkuSpec());
                                //运费
                                cartInfo.setFreight(product.getFreight());
                                //保存
                                int j = ffwyCartInfoMapper.insertFfwyCartInfo(cartInfo);
                                return AjaxResult.success("添加商品成功", j);
                            }

                        } else if (ffwyProductSku.getStock() == 0) {
                            return AjaxResult.success("该商品库存为零!");
                        } else {
                            long num = ffwyProductSku.getStock() - orderDetails.getNum();
                            //剩余多少库存 如果为空是不让添加
                            if (num > 0) {
                                ffwyCartInfos.get(i).setRepertory(Long.valueOf(num).intValue());
                            } else {
                                ffwyCartInfos.get(i).setRepertory(Long.valueOf(num).intValue());
                                return AjaxResult.error("库存不足!");
                            }
                            //拿到购物车的数量
                            Integer skuNum1 = ffwyCartInfos.get(i).getSkuNum();
                            skuNum += skuNum1;
                            //剩余库存 传入进来的数量 购物车的数量
                            if (num > skuNum) {
                                if (ffwyCartInfos.size() > 0) {
                                    //有此数据追加数量
                                    ffwyCartInfo.setId(ffwyCartInfos.get(i).getId());
                                    ffwyCartInfo.setSkuNum(ffwyCartInfos.get(i).getSkuNum() + ffwyCartInfo.getSkuNum());
                                    ffwyCartInfo.setIsChecked(1);
                                    int j = ffwyCartInfoMapper.updateFfwyCartInfo(ffwyCartInfo);
                                    return AjaxResult.success("追加数量:", j);
                                } else {
                                    //不存在 新增一个商品
                                    FfwyCartInfo cartInfo = new FfwyCartInfo();
                                    //设置用户ID
                                    cartInfo.setUserId(userId);
                                    //设置SkuId
                                    cartInfo.setSkuId(ffwyCartInfo.getSkuId());
                                    //设置数量
                                    cartInfo.setSkuNum(ffwyCartInfo.getSkuNum());
                                    FfwyProductSku ffwyProduct = ffwyProductSkuMapper.selectFfwyProductCartById(ffwyCartInfo.getSkuId());
                                    FfwyProduct product = ffwyProductMapper.selectFfwyProductById(ffwyProduct.getProductId());
                                    //获取价格
                                    cartInfo.setCartPrice(ffwyProduct.getCurrentPrice());
                                    //名称
                                    cartInfo.setSkuName(ffwyProduct.getProductName());
                                    //图片
                                    cartInfo.setImgUrl(ffwyProduct.getSkuDefaultImg());
                                    //规格
                                    cartInfo.setSkuSpec(ffwyProduct.getSkuSpec());
                                    //运费
                                    cartInfo.setFreight(product.getFreight());
                                    //保存
                                    int j = ffwyCartInfoMapper.insertFfwyCartInfo(cartInfo);
                                    return AjaxResult.success("添加商品成功", j);
                                }
                            }
                            return AjaxResult.error("该商品库存不足,剩余:", num);
                        }
                    }
                } else {
                    //查询购物是查询库存
                    FfwyProductSku sku =
                            ffwyProductSkuMapper.selectFfwyProductSkuById(ffwyCartInfo.getSkuId());
                    FfwyOrderDetails ffwyOrderDetails1 = new FfwyOrderDetails();
                    ffwyOrderDetails1.setSkuId(ffwyCartInfo.getSkuId());
                    //剩余库存
                    FfwyOrderDetails details =
                            ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails1);
                    //不存在
                    if (sku == null || details == null) {
                        if (sku.getStock() > 0) {
                            //不存在 新增一个商品
                            FfwyCartInfo cartInfo = new FfwyCartInfo();
                            //设置用户ID
                            cartInfo.setUserId(userId);
                            //设置SkuId
                            cartInfo.setSkuId(ffwyCartInfo.getSkuId());
                            //设置数量
                            cartInfo.setSkuNum(ffwyCartInfo.getSkuNum());
                            FfwyProductSku ffwyProduct = ffwyProductSkuMapper.selectFfwyProductCartById(ffwyCartInfo.getSkuId());
                            FfwyProduct product = ffwyProductMapper.selectFfwyProductById(ffwyProduct.getProductId());
                            //获取价格
                            cartInfo.setCartPrice(ffwyProduct.getCurrentPrice());
                            //名称
                            cartInfo.setSkuName(ffwyProduct.getProductName());
                            //图片
                            cartInfo.setImgUrl(ffwyProduct.getSkuDefaultImg());
                            //规格
                            cartInfo.setSkuSpec(ffwyProduct.getSkuSpec());
                            //运费
                            cartInfo.setFreight(product.getFreight());
                            //保存
                            int j = ffwyCartInfoMapper.insertFfwyCartInfo(cartInfo);
                            return AjaxResult.success("添加商品成功", j);
                        }
                    }
                    long num1 = sku.getStock() - details.getNum();
                    if (num1 != 0) {
                        if (details == null || sku.getStock() > 0) {
                            //拿到购物车的数量
                            Integer skuNum1 = ffwyCartInfo.getSkuNum();
                            skuNum += skuNum1;
                            //剩余库存 传入进来的数量 购物车的数量
                            if (num1 > skuNum) {
                                //不存在 新增一个商品
                                FfwyCartInfo cartInfo = new FfwyCartInfo();
                                //设置用户ID
                                cartInfo.setUserId(userId);
                                //设置SkuId
                                cartInfo.setSkuId(ffwyCartInfo.getSkuId());
                                //设置数量
                                cartInfo.setSkuNum(ffwyCartInfo.getSkuNum());
                                FfwyProductSku ffwyProduct = ffwyProductSkuMapper.selectFfwyProductCartById(ffwyCartInfo.getSkuId());
                                FfwyProduct product = ffwyProductMapper.selectFfwyProductById(ffwyProduct.getProductId());
                                //获取价格
                                cartInfo.setCartPrice(ffwyProduct.getCurrentPrice());
                                //名称
                                cartInfo.setSkuName(ffwyProduct.getProductName());
                                //图片
                                cartInfo.setImgUrl(ffwyProduct.getSkuDefaultImg());
                                //规格
                                cartInfo.setSkuSpec(ffwyProduct.getSkuSpec());
                                //运费
                                cartInfo.setFreight(product.getFreight());
                                //保存
                                int j = ffwyCartInfoMapper.insertFfwyCartInfo(cartInfo);
                                return AjaxResult.success("添加商品成功", j);
                            }
                        }
                    }

                }

            }
        }
        return AjaxResult.error("用户TOKRN失效");
    }

    /**
     * 修改购物车
     *
     * @param ffwyCartInfo 购物车
     * @return 结果
     */
    @Override
    public AjaxResult updateFfwyCartInfo(FfwyCartInfo ffwyCartInfo) {
        if (ffwyCartInfo.getIds() != null && ffwyCartInfo.getIsChecked() == 0) {
            //选中取消
            return AjaxResult.success("取消",
                    ffwyCartInfoMapper.updateFfwyCartInfoId(Convert.toStrArray(ffwyCartInfo.getIds())));
        } else if (ffwyCartInfo.getIds() != null && ffwyCartInfo.getIsChecked() == 1) {
            //选中取消
            return AjaxResult.success("选中",
                    ffwyCartInfoMapper.updateFfwyCartInfoisCheckedId(Convert.toStrArray(ffwyCartInfo.getIds())));

        } else {
            Long skuNum = Long.valueOf(ffwyCartInfo.getSkuNum());
            if (ffwyCartInfo.getSkuNum() != null) {
                //获取他的总数
                //查询购物车 用户勾选的商品
                FfwyCartInfo cartInfo = ffwyCartInfoMapper.selectFfwyCartInfoById(ffwyCartInfo.getId());
                //查询购物是查询库存
                FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuById(cartInfo.getSkuId());
                if (ffwyProductSku == null) {
                    return AjaxResult.error("没有库存！");
                }
                FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
                ffwyOrderDetails.setSkuId(cartInfo.getSkuId());
                //剩余库存
                FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails);
                if (orderDetails == null && ffwyProductSku.getStock() > 0) {
                    return AjaxResult.success("修改", ffwyCartInfoMapper.updateFfwyCartInfo(ffwyCartInfo));
                } else {
                    long num = ffwyProductSku.getStock() - orderDetails.getNum();
                    ffwyCartInfo.setNum(num);
                    //判断库存是否比传入大小
                    if (num < skuNum) {
                        return AjaxResult.success("库存不足,剩余:", num);
                    }
                    ;
                    ffwyCartInfo.setSkuNum(Integer.valueOf(Math.toIntExact(skuNum)));
                    return AjaxResult.success("修改", ffwyCartInfoMapper.updateFfwyCartInfo(ffwyCartInfo));
                }
            }
        }
        return AjaxResult.error();
    }

    /**
     * 批量删除购物车
     *
     * @param ids 需要删除的购物车 ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCartInfoByIds(String ids) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        if (userId != null) {
            return ffwyCartInfoMapper.deleteFfwyCartInfoByIds(Convert.toStrArray(ids));
        }
        return 1;
    }

    /**
     * 删除购物车 信息
     *
     * @param id 购物车 ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCartInfoById(Long id) {
        return ffwyCartInfoMapper.deleteFfwyCartInfoById(id);
    }

    @Override
    public Map<String, Object> getCartCheckedList(String id) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        Map<String, Object> result = new HashMap<>();
        //查询用户的默认地址
        FfwyConsigneeAddr ffwyConsigneeAddr = new FfwyConsigneeAddr();
        ffwyConsigneeAddr.setUserId(userId);
        List<FfwyConsigneeAddr> ffwyConsigneeAddrs = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrDefaultList(ffwyConsigneeAddr);
        List<FfwyCartInfo> list = new ArrayList<>();
        //查询购物车 用户勾选的商品
        String[] split = id.split(";");
        for (int i = 0; i < split.length; i++) {
            String ids = split[i];
            FfwyCartInfo ffwyCartInfos = ffwyCartInfoMapper.selectFfwyCartInfoisCheckedById(ids, userId);
            list.add(ffwyCartInfos);
        }
        for (int i = 0; i < list.size(); i++) {
            //查询购物是查询库存
            FfwyProductSku ffwyProductSku = ffwyProductSkuMapper.selectFfwyProductSkuById(list.get(i).getSkuId());
            FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
            ffwyOrderDetails.setSkuId(list.get(i).getSkuId());
            //剩余库存
            FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails);
            if (orderDetails == null) {
                List<FfwyOrderDetails> orderDetailList = list.stream().map(cartInfo -> {
                    FfwyOrderDetails details = new FfwyOrderDetails();
                    //skuid
                    details.setProductSkuId(cartInfo.getSkuId());
                    //数量
                    details.setNumber(cartInfo.getSkuNum());
                    //名字
                    details.setProductSkuName(cartInfo.getSkuName());
                    //价格
                    details.setPrice(cartInfo.getCartPrice());
                    //规格
                    details.setProductSkuSpec(cartInfo.getSkuSpec());
                    //商品的主视图
                    details.setImgUrl(prefixUrl + cartInfo.getImgUrl());
                    //运费
                    details.setFreight(cartInfo.getFreight());
                    //商品id
                    FfwyProductSku sku = ffwyProductSkuMapper.selectFfwyProductSkuById(details.getProductSkuId());
                    Long productId = sku.getProductId();
                    details.setProductId(productId);
                    //获取店铺id
                    FfwyProduct ffwyProduct = ffwyProductMapper.selectFfwyProductById(productId);
                    details.setShopId(ffwyProduct.getShopId());
                    //获取sku规格
                    details.setSkuSpec(sku.getSkuSpec());
                    //查询收货地址
                    return details;
                }).collect(Collectors.toList());
                //设置订单详情集合
                result.put("detailArrayList", orderDetailList);
                //2:商品总件数
                long totalNum = orderDetailList.stream().
                        collect(Collectors.summarizingInt(FfwyOrderDetails::getNumber)).getSum();
                result.put("totalNum", totalNum);
                //3:商品总金额
                double totalAmount = orderDetailList.stream().collect(Collectors.summarizingDouble(orderDetail -> {
                    return orderDetail.getNumber() * orderDetail.getPrice().doubleValue();
                })).getSum();
                result.put("totalAmount", totalAmount);
                //4:运费
                double totafreight = orderDetailList.stream().collect(Collectors.summarizingDouble(orderFreight -> {
                    return orderFreight.getFreight().doubleValue();
                })).getSum();
                result.put("totafreight", totafreight);
                //5:默认地址
                result.put("ffwyConsigneeAddrs", ffwyConsigneeAddrs);
                //6:应付款
                result.put("payPrice", totalAmount + totafreight);
            } else {
                long num = ffwyProductSku.getStock() - orderDetails.getNum();
                //剩余多少库存 如果为空是不让添加
                if (num > list.get(i).getSkuNum() || num == list.get(i).getSkuNum()) {
                    List<FfwyOrderDetails> orderDetailList = list.stream().map(cartInfo -> {
                        FfwyOrderDetails details = new FfwyOrderDetails();
                        //skuid
                        details.setProductSkuId(cartInfo.getSkuId());
                        //数量
                        details.setNumber(cartInfo.getSkuNum());
                        //名字
                        details.setProductSkuName(cartInfo.getSkuName());
                        //价格
                        details.setPrice(cartInfo.getCartPrice());
                        //规格
                        details.setSkuSpec(cartInfo.getSkuSpec());
                        //商品的主视图
                        details.setImgUrl(prefixUrl + cartInfo.getImgUrl());
                        //运费
                        details.setFreight(cartInfo.getFreight());
                        //商品id
                        FfwyProductSku sku = ffwyProductSkuMapper.selectFfwyProductSkuById(details.getProductSkuId());
                        Long productId = sku.getProductId();
                        details.setProductId(productId);
                        //获取店铺id
                        FfwyProduct ffwyProduct = ffwyProductMapper.selectFfwyProductById(productId);
                        details.setShopId(ffwyProduct.getShopId());
                        //获取sku规格
                        details.setSkuSpec(sku.getSkuSpec());
                        //skuid
                        details.setSkuId(sku.getSkuId());
                        //查询收货地址
                        return details;
                    }).collect(Collectors.toList());
                    //设置订单详情集合
                    result.put("detailArrayList", orderDetailList);
                    //2:商品总件数
                    long totalNum = orderDetailList.stream().
                            collect(Collectors.summarizingInt(FfwyOrderDetails::getNumber)).getSum();
                    result.put("totalNum", totalNum);
                    //3:商品总金额
                    double totalAmount = orderDetailList.stream().collect(Collectors.summarizingDouble(orderDetail -> {
                        return orderDetail.getNumber() * orderDetail.getPrice().doubleValue();
                    })).getSum();
                    result.put("totalAmount", totalAmount);
                    //4:运费
                    double totafreight = orderDetailList.stream().collect(Collectors.summarizingDouble(orderFreight -> {
                        return orderFreight.getFreight().doubleValue();
                    })).getSum();
                    result.put("totafreight", totafreight);
                    //5:默认地址
                    result.put("ffwyConsigneeAddrs", ffwyConsigneeAddrs);
                    //6:应付款
                    result.put("payPrice", totalAmount + totafreight);
                }
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> getCartCheckedSkuList(String skuId, int skuNum) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        Map<String, Object> result = new HashMap<>();
        //查询用户的默认地址
        FfwyConsigneeAddr ffwyConsigneeAddr = new FfwyConsigneeAddr();
        ffwyConsigneeAddr.setUserId(userId);
        List<FfwyConsigneeAddr> ffwyConsigneeAddrs = ffwyConsigneeAddrMapper.selectFfwyConsigneeAddrDefaultList(ffwyConsigneeAddr);
        List<FfwyProductSku> list = new ArrayList<>();
        //查询购物车 用户勾选的商品
        //查询购物是查询库存
        FfwyProductSku ffwyProductSku =
                ffwyProductSkuMapper.selectFfwyProductSkuById(Long.valueOf(skuId));
        list.add(ffwyProductSku);
        FfwyProduct ffwyProduct =
                ffwyProductMapper.selectFfwyProductById(ffwyProductSku.getProductId());
        FfwyOrderDetails ffwyOrderDetails = new FfwyOrderDetails();
        ffwyOrderDetails.setSkuId(Long.valueOf(skuId));
        if (ffwyProductSku == null || ffwyProduct == null) {
            return null;
        }
        //剩余库存
        FfwyOrderDetails orderDetails = ffwyOrderDetailsMapper.selectFfwyOrderDetailsRepertorys(ffwyOrderDetails);
        if (orderDetails == null) {
            List<FfwyOrderDetails> orderDetailList = list.stream().map(cartInfo -> {
                FfwyOrderDetails details = new FfwyOrderDetails();
                //skuid
                details.setProductSkuId(Long.valueOf(skuId));
                //数量
                details.setNumber(skuNum);
                //名字
                details.setProductSkuName(ffwyProductSku.getProductName());
                //价格
                details.setPrice(ffwyProductSku.getPrice());
                //规格
                details.setProductSkuSpec(ffwyProductSku.getSkuSpec());
                //商品的主视图
                details.setImgUrl(prefixUrl + ffwyProductSku.getSkuDefaultImg());
                //运费
                details.setFreight(ffwyProduct.getFreight());
                //商品id
                FfwyProductSku sku = ffwyProductSkuMapper.selectFfwyProductSkuById(details.getProductSkuId());
                Long productId = sku.getProductId();
                details.setProductId(productId);
                //获取店铺id
                details.setShopId(ffwyProduct.getShopId());
                //获取sku规格
                details.setSkuSpec(sku.getSkuSpec());
                //查询收货地址
                return details;
            }).collect(Collectors.toList());
            //设置订单详情集合
            result.put("detailArrayList", orderDetailList);
            //2:商品总件数
            long totalNum = orderDetailList.stream().
                    collect(Collectors.summarizingInt(FfwyOrderDetails::getNumber)).getSum();
            result.put("totalNum", totalNum);
            //3:商品总金额
            double totalAmount = orderDetailList.stream().collect(Collectors.summarizingDouble(orderDetail -> {
                return orderDetail.getNumber() * orderDetail.getPrice().doubleValue();
            })).getSum();
            result.put("totalAmount", totalAmount);
            //4:运费
            double totafreight = orderDetailList.stream().collect(Collectors.summarizingDouble(orderFreight -> {
                return orderFreight.getFreight().doubleValue();
            })).getSum();
            result.put("totafreight", totafreight);
            //5:默认地址
            result.put("ffwyConsigneeAddrs", ffwyConsigneeAddrs);
            //6:应付款
            result.put("payPrice", totalAmount + totafreight);
        } else {
            long num = ffwyProductSku.getStock() - orderDetails.getNum();
            //剩余多少库存 如果为空是不让添加
            if (num > skuNum || num == skuNum) {
                List<FfwyOrderDetails> orderDetailList = list.stream().map(cartInfo -> {
                    FfwyOrderDetails details = new FfwyOrderDetails();
                    //skuid
                    details.setProductSkuId(Long.valueOf(skuId));
                    //数量
                    details.setNumber(skuNum);
                    //名字
                    details.setProductSkuName(ffwyProductSku.getProductName());
                    //价格
                    details.setPrice(ffwyProductSku.getPrice());
                    //规格
                    details.setProductSkuSpec(ffwyProductSku.getSkuSpec());
                    //商品的主视图
                    details.setImgUrl(prefixUrl + ffwyProductSku.getSkuDefaultImg());
                    //运费
                    details.setFreight(ffwyProduct.getFreight());
                    //商品id
                    FfwyProductSku sku = ffwyProductSkuMapper.selectFfwyProductSkuById(details.getProductSkuId());
                    Long productId = sku.getProductId();
                    details.setProductId(productId);
                    //获取店铺id
                    details.setShopId(ffwyProduct.getShopId());
                    //获取sku规格
                    details.setSkuSpec(sku.getSkuSpec());
                    //skuid
                    details.setSkuId(sku.getSkuId());
                    //查询收货地址
                    return details;
                }).collect(Collectors.toList());
                //设置订单详情集合
                result.put("detailArrayList", orderDetailList);
                //2:商品总件数
                long totalNum = orderDetailList.stream().
                        collect(Collectors.summarizingInt(FfwyOrderDetails::getNumber)).getSum();
                result.put("totalNum", totalNum);
                //3:商品总金额
                double totalAmount = orderDetailList.stream().collect(Collectors.summarizingDouble(orderDetail -> {
                    return orderDetail.getNumber() * orderDetail.getPrice().doubleValue();
                })).getSum();
                result.put("totalAmount", totalAmount);
                //4:运费
                double totafreight = orderDetailList.stream().collect(Collectors.summarizingDouble(orderFreight -> {
                    return orderFreight.getFreight().doubleValue();
                })).getSum();
                result.put("totafreight", totafreight);
                //5:默认地址
                result.put("ffwyConsigneeAddrs", ffwyConsigneeAddrs);
                //6:应付款
                result.put("payPrice", totalAmount + totafreight);
            }
        }
        return result;


    }

}
