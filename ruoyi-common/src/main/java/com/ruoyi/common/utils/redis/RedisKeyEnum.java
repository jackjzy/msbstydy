package com.ruoyi.common.utils.redis;

import lombok.Getter;

@Getter
public enum RedisKeyEnum {
	ORDER("order:","商品详情"),
	PRODUCTSKULIST("productSkusList:","商品SKU列表"),
	AREAALL("area_all:","省市县所有数据"),
	EN_TOKEN("en_token:","环信token"),
	;
	private String key;
	private String desc;
	RedisKeyEnum(String key,String desc){
		this.key = key;
		this.desc = desc;
	}
}
