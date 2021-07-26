package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.AuthServerConstant;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.querys.QueryShop;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.shop.FfwyShopPhoto;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import com.ruoyi.system.mapper.shop.FfwyShopPhotoMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyShopService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 店铺信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-20
 */
@Service
public class FfwyShopServiceImpl implements IFfwyShopService {
    @Autowired
    private FfwyShopMapper ffwyShopMapper;

    @Autowired
    private FfwyProductMapper ffwyProductMapper;

    @Autowired
    private FfwyShopPhotoMapper ffwyShopPhotoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    /**
     * 查询店铺信息
     *
     * @param shopId 店铺信息ID
     * @return 店铺信息
     */
    @Override
    public FfwyShop selectFfwyShopById(Long shopId) {
        return ffwyShopMapper.selectFfwyShopById(shopId);
    }

    @Override
    public FfwyShop selectFfwyByUserId(Long userId) {
        return ffwyShopMapper.selectFfwyByUserId(userId);
    }

    @Override
    public FfwyShop selectStroeShopByuserIdAndShopId(Long shopId, Long userId) {
        return ffwyShopMapper.selectStroeShopByuserIdAndShopId(shopId, userId);
    }

    /**
     * 查询店铺信息列表
     *
     * @param ffwyShop 店铺信息
     * @return 店铺信息
     */
    @Override
    public List<FfwyShop> selectFfwyShopList(FfwyShop ffwyShop) {
        List<FfwyShop> shops = ffwyShopMapper.selectFfwyShopList(ffwyShop);
        if(null==shops)return  new ArrayList<FfwyShop>();
        for (FfwyShop shop : shops) {
            if(null==shop.getShopStatus())continue;
            if ( shop.getShopStatus() == 0) {  // 判断状态是否需要解禁   判断当下时间是否大于设置的禁用时间
                Date disableTime = shop.getDisableTime();
                if (System.currentTimeMillis() >= DateUtils.getTime(disableTime)) {
                    ffwyShopMapper.updateShopDisable(shop.getShopId(), null, 1);// 1 为正常状态
                    shop.setShopStatus(1);
                }
            }
        }

        return shops;
    }

    @Override
    public List<FfwyShop> selectByShopAdudtiStatus(QueryShop queryShop) {
        return ffwyShopMapper.selectByShopAdudtiStatus(queryShop);
    }

    @Override
    public List<FfwyShop> selectStroeShop(Long userId) {


        return setCover(ffwyShopMapper.selectStroeShop(userId));
    }

    /**
     * 新增店铺信息
     *
     * @param ffwyShop 店铺信息
     * @return 结果
     */
    @Override
    public int insertFfwyShop(FfwyShop ffwyShop) {
        ffwyShop.setCreateTime(DateUtils.getNowDate());
        return ffwyShopMapper.insertFfwyShop(ffwyShop);
    }

    @Override
    public AjaxResult insertStroeShop(FfwyShop ffwyShop) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        int i = ffwyShopMapper.insertStroeShop(ffwyShop.getShopId(), userId);
        if (i > 0) {
            FfwyShop shop = ffwyShopMapper.selectFfwyShopById(ffwyShop.getShopId());
            Long collect = shop.getCollect();
            FfwyShop shop1 = new FfwyShop();
            shop1.setCollect(collect + 1);
            shop1.setShopId(ffwyShop.getShopId());
            return AjaxResult.success(ffwyShopMapper.updateFfwyShop(shop1));
        }
        return AjaxResult.error();
    }

    /**
     * 修改店铺信息
     *
     * @param ffwyShop 店铺信息
     * @return 结果
     */
    @Override
    public int updateFfwyShop(FfwyShop ffwyShop) {
        ffwyShop.setUpdateTime(DateUtils.getNowDate());
        return ffwyShopMapper.updateFfwyShop(ffwyShop);
    }

    @Override
    public int updateFfwyShopLogo(MultipartFile file, Long shopId) {
        FfwyShop ffwyShop = ffwyShopMapper.selectFfwyShopById(shopId);
        CosUtil.deletePhoto(ffwyShop.getShopLogo());
        String s = CosUtil.CosUpload(file);
        ffwyShop.setShopLogo(s);

        return ffwyShopMapper.updateFfwyShop(ffwyShop);
    }

    /**
     * 批量删除店铺信息
     *
     * @param shopIds 需要删除的店铺信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopByIds(Long[] shopIds) {
        return ffwyShopMapper.deleteFfwyShopByIds(shopIds);
    }

    /**
     * 删除店铺信息信息
     *
     * @param shopId 店铺信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyShopById(Long shopId) {
        return ffwyShopMapper.deleteFfwyShopById(shopId);
    }

    @Override
    public List<FfwyShop> selectFfwyShopAppList(FfwyShop ffwyShop) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取店铺的所有商品
        List<FfwyShop> ffwyShops = ffwyShopMapper.selectFfwyShopList(ffwyShop);
        ffwyShops.forEach(ffwyShop1 -> {
            ffwyShop1.setShopLogo(prefixUrl + ffwyShop1.getShopLogo());
        });
        //真实用户
        if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
            Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
            ffwyShops.forEach(shop -> {
                FfwyShop ffwyShop1 = new FfwyShop();
                ffwyShop1.setShopId(ffwyShop.getShopId());
                ffwyShop1.setUserId(userId);
                FfwyShop ffwyShop2 = ffwyShopMapper.selectFfwyStoreShopList(ffwyShop1);
                if (ffwyShop2!=null){
                    shop.setShopCollect(ffwyShop2.getUserId());
                }else {
                    shop.setShopCollect(null);
                }

            });
        }
        return ffwyShops;
    }

    @Override
    public List<FfwyShop> selectFfwyShopIdList(FfwyShop ffwyShop) {
        // TODO 店铺访问量统计  每调用一次接口增加一次
        // 获取存入Redis里的访问量
        String s = stringRedisTemplate.opsForValue().get(Constants.VIEWS + ffwyShop.getShopId());
        if (!StringUtils.isEmpty(s)) {
            Integer integer = Integer.valueOf(s);
            stringRedisTemplate.opsForValue().set(Constants.VIEWS + ffwyShop.getShopId(),
                    integer + 1 + "", DateUtils.getSecondsNextEarlyMorning(), TimeUnit.SECONDS);
        } else {
            //存入redis，设置过期时间为午夜0点
            stringRedisTemplate.opsForValue().set(Constants.VIEWS + ffwyShop.getShopId(),
                    "1", DateUtils.getSecondsNextEarlyMorning(), TimeUnit.SECONDS);
        }


        List<FfwyShop> ffwyShops = ffwyShopMapper.selectFfwyShopList(ffwyShop);
        //获取
        ffwyShops.forEach(ffwyShop1 -> {
            ffwyShop1.setShopLogo(prefixUrl+ffwyShop1.getShopLogo());
            List<FfwyShopPhoto> ffwyShopPhotos = ffwyShopPhotoMapper.selectFfwyShopPhotoListByShopId(ffwyShop1.getShopId());
            ffwyShopPhotos.forEach(ffwyShopPhoto -> {
                ffwyShopPhoto.setPhototPath(prefixUrl+ffwyShopPhoto.getPhototPath());
            });
            ffwyShop1.setShopPhotos(ffwyShopPhotos);
        });
        return ffwyShops;
    }

    @Override
    public List<FfwyProduct> selectFfwyproductList(FfwyProduct ffwyProduct) {
        List<FfwyProduct> ffwyProducts = ffwyProductMapper.selectFfwyProductList(ffwyProduct);
        ffwyProducts.forEach(ffwyProduct1 -> {
            ffwyProduct1.setPhoto(prefixUrl + ffwyProduct1.getPhoto());
        });
        return ffwyProducts;
    }

    @Override
    public AjaxResult deleteStroeShop(FfwyShop shop) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        int i = ffwyShopMapper.deleteStroeShop(shop.getShopId(), userId);
        if (i > 0) {
            FfwyShop fs = ffwyShopMapper.selectFfwyShopById(shop.getShopId());
            Long collect = fs.getCollect();
            FfwyShop ffwyShop = new FfwyShop();
            ffwyShop.setCollect(collect - 1);
            ffwyShop.setShopId(shop.getShopId());
            return AjaxResult.success(ffwyShopMapper.updateFfwyShop(ffwyShop));
        }
        return AjaxResult.error();
    }

    @Override
    public String viewShare() {
        List<FfwyShop> shops = ffwyShopMapper.selectFfwyShopList(new FfwyShop());
        for (FfwyShop shop : shops) {
            if (shop.getTerraceRatio() != null && shop.getShopRetio() != null) {
                JSONObject json = new JSONObject();
                json.put("terraceRatio", shop.getTerraceRatio());
                json.put("shopRetio", shop.getShopRetio());
                return json.toJSONString();
            }
        }

        return null;
    }

    @Override
    @Transactional
    public boolean modifyShare(BigDecimal terraceRatio, BigDecimal shopRetio) {
        int pageSize = 5;
        int pageNum = 0;
        int startSize = 0;
        int size = ffwyShopMapper.sCount();

        pageNum = size % pageSize == 0 ? size / pageSize : (size / pageSize) + 1;

        for (int i = 0; i < pageNum; i++) {
            startSize = i * pageSize;
            List<FfwyShop> shops = ffwyShopMapper.selectShopListLimit(startSize, pageSize);
            for (FfwyShop shop : shops) {
                ffwyShopMapper.updateShop(shop.getShopId(), terraceRatio, shopRetio);
            }
        }

        return true;
    }

    @Override
    public int disableShop(Long shopId, Date disableTime) {
        return ffwyShopMapper.updateShopDisable(shopId, disableTime, 0);  //  0为禁用状态
    }

    private  List<FfwyShop> setCover(List<FfwyShop> ffwyShops){
        ffwyShops.forEach(ffwyShop -> {
            ffwyShop.setShopLogo(CosCofig.getPrefixUrl()+ffwyShop.getShopLogo());
        });

        return ffwyShops;
    }
}
