package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.search.FfwyProductSearch;
import com.ruoyi.system.domain.search.SearchParam;
import com.ruoyi.system.domain.search.SearchVideo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * @author 郭晓康
 * @create 2021-05-11 下午7:14
 */
@Repository
public interface FfwySearchService {
    void importAll(Long productId);

    void lowerProductSearch(Long productId);

    AjaxResult search(SearchParam searchParam)throws IOException;

    void importAllvideo(Long videoId);

    AjaxResult searchvideo(SearchVideo searchVideo) throws IOException;

    void lowerVideoSearch(Long videoId);

    AjaxResult searchDelect(SearchParam searchParam);

    AjaxResult searchDelectvideo(SearchVideo searchVideo);

    AjaxResult searchList(SearchParam searchParam);

    AjaxResult searchvideoList(SearchVideo searchVideo);


    AjaxResult searchVideoDelect(SearchVideo searchVideo);


    void importUserName(Long userId);

    void searchVideoDelectUser(Long videoId);

    void lowervideoUserSearch(Long userId);
}
