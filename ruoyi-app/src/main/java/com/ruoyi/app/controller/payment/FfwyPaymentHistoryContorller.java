package com.ruoyi.app.controller.payment;

import com.ruoyi.back.domain.FfwyReportType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.FfwyPayRecord;
import com.ruoyi.system.domain.vo.PaymentHistoryVo;
import com.ruoyi.system.service.IFfwyOrderService;
import com.ruoyi.system.service.IFfwyPayRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "支付记录")
@RestController
@RequestMapping("/app/payment")
public class FfwyPaymentHistoryContorller extends BaseController {

    @Autowired
    private IFfwyPayRecordService iFfwyPayRecordService;

    /**
     * 查询【请填写功能名称】列表
     */
    @ApiOperation("查询所有支付记录")
    @GetMapping("/list")
    public TableDataInfo list(HttpServletRequest request)
    {
       Long userId = JWTUtils.getId(request.getHeader("token"));
        List<PaymentHistoryVo> plist =new ArrayList<>();
        startPage();
        FfwyPayRecord ffwyPayRecord = new FfwyPayRecord();
        ffwyPayRecord.setUserId(userId);
        List<FfwyPayRecord> list = iFfwyPayRecordService.selectFfwyPayRecordList(ffwyPayRecord);
        list.forEach( payRecord ->{
            String payMoney = payRecord.getPayMoney();
            BigDecimal money = new BigDecimal(payMoney).divide(new BigDecimal(100));
            plist.add(new PaymentHistoryVo(money,
                    payRecord.getProductMsg(),
                    payRecord.getCreateTime(),
                    payRecord.getCteateOrderTime(),
                    payRecord.getPayTyep(),
                    payRecord.getUserId(),
                    null,
                    null
                    )
            );
        });


//        List<PaymentHistoryVo> list = ffwyOrderService.selectFfwyOrderandCombomealList(userId);
        return getDataTable(plist);
    }
}
