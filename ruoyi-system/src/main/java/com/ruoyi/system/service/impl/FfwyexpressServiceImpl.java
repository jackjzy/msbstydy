package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.KuaiDi100Util;
import com.ruoyi.common.utils.KuaiDiCode;
import com.ruoyi.system.domain.order.FfwyOrderDetails;
import com.ruoyi.system.mapper.order.FfwyOrderDetailsMapper;
import com.ruoyi.system.service.FfwyexpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 快递100 相关操作
 *
 * @author wangsong
 * @version 1.0.0
 * @mail 1720696548@qq.com
 * @date 2020/9/16 0016 10:26
 */
@Service
public class FfwyexpressServiceImpl implements FfwyexpressService {

    @Autowired
    private KuaiDi100Util kuaiDi100Util;
    @Autowired
    private FfwyOrderDetailsMapper ffwyOrderDetailsMapper;
    @Override
    public AjaxResult findOrder(String trackIngNumber) {
        //获取快递编码

        List<KuaiDiCode> kuaiDiCodeList = findKuaiDiCode(trackIngNumber);
        if (kuaiDiCodeList.size() == 0) {
            return AjaxResult.error(10099,"无法识别该快递单号");
        }
        String orderJson = null;
        // 遍历快递公司： 找找时间立即跳出
        for (KuaiDiCode kuaiDiCode : kuaiDiCodeList) {
            // 没有找到： {"result":false,"returnCode":"500","message":"查询无结果，请隔段时间再查"}
    orderJson = kuaiDi100Util.findOrder(trackIngNumber, kuaiDiCode.getComCode());
            JSONObject jsonObject = JSON.parseObject(orderJson);
            // 当没有找到快递, 会出现result=false,找到了物流,没有result字段
            Boolean result = (Boolean) jsonObject.get("result");
            if (result == null) {
                break;
            }
        }
        //判断是否找到物流信息
        JSONObject jsonObject = JSON.parseObject(orderJson);
        Boolean result = (Boolean) jsonObject.get("result");
        if (result != null && !result) {
            return AjaxResult.error(10099,"查询无结果，请隔段时间再查");
        }
       return AjaxResult.success(jsonObject);
    }


    /**
     * 智能识别快递单号
     * @param trackIngNumber
     * @return
     */
    private List<KuaiDiCode> findKuaiDiCode(String trackIngNumber) {
        List<KuaiDiCode> kuaiDiCode = kuaiDi100Util.findKuaiDiCode(trackIngNumber);
        return kuaiDiCode;
    }
}

