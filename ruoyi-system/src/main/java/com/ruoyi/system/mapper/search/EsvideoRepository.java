package com.ruoyi.system.mapper.search;

import com.ruoyi.system.domain.search.FfwyProductSearch;
import com.ruoyi.system.domain.search.FfwyVideoSearch;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author 郭晓康
 * @create 2021-05-11 下午10:11
 */
public interface EsvideoRepository extends ElasticsearchRepository<FfwyVideoSearch, Long> {

}
