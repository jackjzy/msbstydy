package com.ruoyi.common.utils;


import java.math.BigDecimal;
import java.util.Map;

public class FormatUtils {

    /**
     * 1. 钱的元到分
     * 2. 佣金比例的百分之到万分之
     *
     * @param yuan
     * @return
     */
    public static int yuan2Fen(BigDecimal yuan) {
        if (yuan == null) return 0;
        return yuan.multiply(BigDecimal.valueOf(100)).intValue();
    }

    public static Integer yuanToFen(String yuan) {
        return (new BigDecimal(yuan)).setScale(2, 4).multiply(new BigDecimal(100)).intValue();
    }

    public static int yuan2Fen(Double yuan) {
        if (yuan == null) {
            return 0;
        }
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(yuan));
        return yuan2Fen(bigDecimal);
    }

    public static int yuan2Fen(int yuan) {
        if (yuan <= 0) return 0;
        return yuan * 100;
    }

    /**
     * 1. 钱的元到分
     * 2. 佣金比例的万分之到百分之
     *
     * @param fen
     * @return
     */
    public static BigDecimal fen2Yuan(Integer fen) {
        if (fen == null) {
            return BigDecimal.valueOf(0);
        }
        return BigDecimal.valueOf(fen).divide(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_FLOOR);
    }

    public static Integer fen2YuanInt(Integer fen) {
        if (fen == null) {
            return 0;
        }
        return fen / 100;
    }

    public static String fenToYuan(Integer fen) {
        BigDecimal bigDecimal = fen2Yuan(fen);
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }

    /**
     * 返回 （金额 * 百分比）（分）
     *
     * @param amount 金额 分
     * @param rate   百分比
     * @return
     */
    public static Integer amountToRate(Integer amount, Integer rate) {
        if ((amount == null) || (rate == null)) {
            return null;
        }
        BigDecimal multiply = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(rate).divide(BigDecimal.valueOf(100)));
        return multiply.intValue();
    }

    /**
     * 返回 （金额 * 万分比）（分）
     *
     * @param amount 金额 分
     * @param rate   万分比
     * @return
     */
    public static Integer amountToRateV2(Integer amount, Integer rate) {
        if ((amount == null) || (rate == null)) {
            return null;
        }
        BigDecimal multiply = BigDecimal.valueOf(amount).multiply(BigDecimal.valueOf(rate).divide(BigDecimal.valueOf(10000)));
        return multiply.intValue();
    }

}