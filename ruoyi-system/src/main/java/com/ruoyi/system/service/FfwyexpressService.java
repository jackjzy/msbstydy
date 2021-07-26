package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.stereotype.Repository;

/**
 * 快递100 相关操作
 * @author wangsong
 * @mail 1720696548@qq.com
 * @date 2020/9/16 0016 10:26
 * @version 1.0.0
 */
@Repository
public interface FfwyexpressService {




    AjaxResult findOrder(String trackIngNumber);




    /**
     * 获取快递信息
     * @param orderId
     */
   // public AjaxResult findOrder(String orderId);

//    /**
//     * 获取快递公司编号信息
//     * @param orderId
//     * @return
//     */
//    public List<KuaiDiCode> findKuaiDiCode(String orderId);
}
