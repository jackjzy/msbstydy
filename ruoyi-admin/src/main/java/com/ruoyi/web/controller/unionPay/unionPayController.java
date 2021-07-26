package com.ruoyi.web.controller.unionPay;


import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.PayType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.FfwyPayRecord;
import com.ruoyi.system.service.IFfwyPayRefundService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


@RequestMapping("/unionPay")
@Controller
public class unionPayController {


    @Autowired
    private IFfwyPayRefundService ffwyPayRefundService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){

        System.err.println("123");
        return "hello  unionPay";
    }


    /**
     * 退款回调
     */
    @ApiOperation("退款回调")
    @RequestMapping("/callBackRefund")
    public void callBackRefund( HttpServletRequest req,HttpServletResponse resp) throws IOException {
        ffwyPayRefundService.callBackRefund(req,resp);
    }

}
