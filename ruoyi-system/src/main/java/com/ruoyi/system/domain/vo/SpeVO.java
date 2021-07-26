package com.ruoyi.system.domain.vo;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.Model;
import com.ruoyi.system.domain.product.FfwyProductSku;
import lombok.*;

import java.util.*;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SpeVO {
    private Integer speId;
    /**
     * 商品Id
     */
    private Long productId;
    /**
     * 是否最后一层
     */
    private Boolean isEnd;
    private String speName;
    /**
     * 规格值
     */
    private String speValue;
    /**
     * 规格值数组
     */
    private String[] speValues;
    private String valueSelect;

    private List<SpeVO> childSpe;


    private FfwyProductSku productSku;


    public SpeVO(String speName, String speValue) {
        this.speName = speName;
        this.speValue = speValue;
    }

    public SpeVO(Long productId, String speName, String speValue) {
        this.productId = productId;
        this.speName = speName;
        this.speValue = speValue;
    }

    public SpeVO(Integer specificationId, Long productId, String speName, String speValue) {
        this.speId = specificationId;
        this.productId = productId;
        this.speName = speName;
        this.speValue = speValue;
    }

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("红色");
        list.add("绿色");
        list.add("黑色");
        map.put("颜色", list);
        list = new ArrayList<>();
        list.add("大码");list.add("小码");
//        list.add("X");list.add("S");list.add("M");list.add("L");
        map.put("尺码", list);
        list = new ArrayList<>();
        list.add("上衣");
        list.add("裤子");
        map.put("类型", list);

        System.out.println(map);

        List<JSONObject> jsonObjects = SpecificationSort.specificationMapAscendJSON(map);

        System.err.println(jsonObjects);

    }



}
