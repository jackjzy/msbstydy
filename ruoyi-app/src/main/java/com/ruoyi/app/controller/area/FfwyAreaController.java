package com.ruoyi.app.controller.area;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;

import com.ruoyi.common.utils.StringUtils;

import com.ruoyi.common.utils.redis.RedisCacheHelper;
import com.ruoyi.common.utils.redis.RedisKeyEnum;
import com.ruoyi.system.domain.FfwyArea;
import com.ruoyi.system.service.IFfwyAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 省市县Controller
 * 
 * @author ruoyi
 * @date 2021-06-30
 */
@Slf4j
@Api(tags = "省市县数据")
@RestController
@RequestMapping("/app/area/")
public class FfwyAreaController extends BaseController
{
    @Autowired
    private IFfwyAreaService ffwyAreaService;

    @Autowired
    private RedisCacheHelper redisCacheHelper;

    @ApiOperation("查询所有省市县数据")
    @PostMapping("/")
    public AjaxResult findAll() {

        Object object = redisCacheHelper.hget(RedisKeyEnum.AREAALL.getKey(), RedisKeyEnum.AREAALL.getKey());
        if (null != object) return AjaxResult.success(JSONObject.parseArray(object.toString()));

        List<FfwyArea> ffwyAreas = ffwyAreaService.selectFfwyAreaList(null);
        List<FfwyArea> FfwyAreaOne = ffwyAreas.stream().filter(ffwyArea -> ffwyArea.getPerentCode().equals("0")).collect(Collectors.toList());
        FfwyAreaOne.forEach(ffwyAreaOne -> ffwyAreaOne.setFfwyAreaList(getChildren(ffwyAreas, ffwyAreaOne.getCode())));

        redisCacheHelper.hset(RedisKeyEnum.AREAALL.getKey(), RedisKeyEnum.AREAALL.getKey(), JSONObject.toJSONString(FfwyAreaOne));
        return AjaxResult.success(FfwyAreaOne);
    }
    @ApiOperation("获取一级数据")
    @PostMapping("/getAreaOne")
    public AjaxResult getAreaOne() {
        String redisKey ="getAreaOne";
        Object object = redisCacheHelper.hget(RedisKeyEnum.AREAALL.getKey(),redisKey);
        if(null != object)    return AjaxResult.success(JSONObject.parseArray(object.toString()));

        FfwyArea ffwyAreaDB = new FfwyArea();ffwyAreaDB.setPerentCode("0");
        List<FfwyArea> ffwyAreas = ffwyAreaService.selectFfwyAreaList(ffwyAreaDB);

        redisCacheHelper.hset(RedisKeyEnum.AREAALL.getKey(),redisKey,JSONObject.toJSONString(ffwyAreas));
        return AjaxResult.success(ffwyAreas);
    }

    @ApiOperation("根据code查询下级数据")
    @PostMapping("/getArea")
    public AjaxResult getAreaByCode(@RequestBody FfwyArea ffwyArea) {
       if(StringUtils.isEmpty(ffwyArea.getCode()))return AjaxResult.error("CODE IS NULL");
        Object object = redisCacheHelper.hget(RedisKeyEnum.AREAALL.getKey(),ffwyArea.getCode());
        if(null != object)    return AjaxResult.success(JSONObject.parseArray(object.toString()));

        FfwyArea ffwyAreaDB = new FfwyArea();ffwyAreaDB.setPerentCode(ffwyArea.getCode());
        List<FfwyArea> ffwyAreas = ffwyAreaService.selectFfwyAreaList(ffwyAreaDB);

        redisCacheHelper.hset(RedisKeyEnum.AREAALL.getKey(),ffwyArea.getCode(),JSONObject.toJSONString(ffwyAreas));
        return AjaxResult.success(ffwyAreas);
    }
    /**
     * 获取下一级内容
     * @param ffwyAreaList
     * @param code
     * @return
     */
    public List<FfwyArea> getChildren(List<FfwyArea> ffwyAreaList,String code){
        List<FfwyArea>  collect = ffwyAreaList.stream().filter(ffwyArea -> ffwyArea.getPerentCode().equals(code)).collect(Collectors.toList());
        collect.stream().forEach(ffwyAreaThree -> ffwyAreaThree.setFfwyAreaList(ffwyAreaList.stream().filter
                (ffwyArea -> ffwyArea.getPerentCode().equals(ffwyAreaThree.getCode())).collect(Collectors.toList())));
        return collect;
    }

}
