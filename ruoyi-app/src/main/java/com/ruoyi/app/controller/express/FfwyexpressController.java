package com.ruoyi.app.controller.express;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.KuaiDiCode;
import com.ruoyi.system.service.FfwyexpressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 郭晓康
 * @create 2021-06-11 上午10:46
 */
@RestController
@RequestMapping( "/app/open")
@Api(value = "KuaiDi100Controller", tags = "KuaiDi  -->  快递100")
public class FfwyexpressController {

    @Autowired
    private FfwyexpressService ffwyexpressService;


    @ApiOperation(value = "根据快递单号查询物流信息", notes = "" +
            "\r\n 1、自动识别单号" +
            "\r\n 2、返回参数查看：https://api.kuaidi100.com/help/doc/?code=5f0ffb5ebc8da837cbd8aefc&openKey=%E5%AE%9E%E6%97%B6%E5%BF%AB%E9%80%92%E6%9F%A5%E8%AF%A2#part2" +
            "\r\n 3、测试单号：75429527532676" +
            "")
    @GetMapping(value = "/findOrder")
    public AjaxResult findOrder(String trackIngNumber) {

        return ffwyexpressService.findOrder(trackIngNumber);
    }

}