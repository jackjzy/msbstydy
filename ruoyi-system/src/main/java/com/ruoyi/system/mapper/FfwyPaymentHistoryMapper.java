package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.vo.PaymentHistoryVo;

import java.util.List;

public interface FfwyPaymentHistoryMapper {

    List<PaymentHistoryVo> selectOrderpaymentAll(PaymentHistoryVo paymentHistoryVo);

    List<PaymentHistoryVo> selectCombomealPaymentAll(PaymentHistoryVo paymentHistoryVo);
}
