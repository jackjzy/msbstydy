package com.ruoyi.system.mapper.search;

import com.ruoyi.system.domain.search.FfwyProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.DynamicTemplates;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 搜索商品ES操作类
 */
public interface EsProductRepository extends ElasticsearchRepository<FfwyProductSearch, Long> {

}
