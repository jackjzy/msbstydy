package com.ruoyi.app.service;

import com.ruoyi.system.domain.FfwyPayRecord;

public interface AlipayService {

    public FfwyPayRecord tradeAppPay(FfwyPayRecord pay);


    public Boolean tradeRefund(String orderNo, Integer refundFee);
}
