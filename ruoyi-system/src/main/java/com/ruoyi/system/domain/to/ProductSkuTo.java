package com.ruoyi.system.domain.to;

import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.vo.SpeVO;
import lombok.Data;

import java.util.List;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/6/18
 **/
@Data
public class ProductSkuTo {
    private List<SpeVO> speVOS;
    private List<FfwyProductSku> productSkus;
    private Long productId;
}
