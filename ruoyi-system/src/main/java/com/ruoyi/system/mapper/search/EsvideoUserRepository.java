package com.ruoyi.system.mapper.search;

import com.ruoyi.system.domain.search.FfwyVideoUserSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsvideoUserRepository extends ElasticsearchRepository<FfwyVideoUserSearch, Long> {
}
