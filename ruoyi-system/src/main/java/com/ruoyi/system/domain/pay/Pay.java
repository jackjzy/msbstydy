package com.ruoyi.system.domain.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 支付信息
 *
 * @author yzj
 * @date 2021-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pay {
    /**
     * 商品id
     */
    private String orderId;
    /**
     * 支付金额（单位分）
     */
    private String txnAmt;
    /**
     * 支付时间
     */
    private Date txnTime;
    /**
     * 支付类型
     */
    private String OrderType;
}
