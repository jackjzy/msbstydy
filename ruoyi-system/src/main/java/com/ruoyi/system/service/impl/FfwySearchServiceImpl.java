package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.FfwyVideoForward;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.admin.FfwyUserFans;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductComment;
import com.ruoyi.system.domain.search.*;
import com.ruoyi.system.domain.shop.FfwyShop;
import com.ruoyi.system.domain.video.FfwyAppVideo;
import com.ruoyi.system.domain.video.FfwyVideoComment;
import com.ruoyi.system.domain.video.FfwyVideoLove;
import com.ruoyi.system.domain.video.FfwyVideoNotlove;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;
import com.ruoyi.system.mapper.FfwyVideoLoveMapper;
import com.ruoyi.system.mapper.admin.FfwyUserFansMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import com.ruoyi.system.mapper.product.FfwyProductCommentMapper;
import com.ruoyi.system.mapper.product.FfwyProductMapper;
import com.ruoyi.system.mapper.search.EsProductRepository;
import com.ruoyi.system.mapper.search.EsvideoRepository;
import com.ruoyi.system.mapper.search.EsvideoUserRepository;
import com.ruoyi.system.mapper.search.FfwysearchMapper;
import com.ruoyi.system.mapper.shop.FfwyShopMapper;
import com.ruoyi.system.mapper.video.FfwyAppVideoMapper;
import com.ruoyi.system.mapper.video.FfwyVideoCommentMapper;
import com.ruoyi.system.mapper.video.FfwyVideoForwardMapper;
import com.ruoyi.system.service.FfwySearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author 郭晓康
 * @create 2021-05-11 下午7:15
 */
@Service
public class FfwySearchServiceImpl implements FfwySearchService {
    @Autowired
    private FfwyProductMapper ffwyProductMapper;
    @Autowired
    private FfwyProductCommentMapper ffwyProductCommentMapper;
    @Autowired
    private EsProductRepository productRepository;
    @Autowired
    private FfwyShopMapper ffwyShopMapper;
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private EsvideoRepository esvideoRepository;
    @Autowired
    private EsvideoUserRepository esvideoUserRepository;
    @Autowired
    private FfwysearchMapper ffwysearchMapper;
    @Autowired
    private FfwyUserMapper ffwyUserMapper;
    @Autowired
    private FfwyVideoLoveMapper ffwyVideoLoveMapper;
    @Autowired
    private FfwyVideoCommentMapper ffwyVideoCommentMapper;
    @Autowired
    private FfwyUserFansMapper ffwyUserFansMapper;
    @Value("${cos.prefixUrl}")
    private String prefixUrl;
    @Autowired
    private FfwyVideoForwardMapper ffwyVideoForwardMapper;
    @Autowired
    private FfwyAppVideoMapper ffwyVideoMapper;


    @Override
    public void importAll(Long productId) {
        FfwyProduct ffwyProduct = ffwyProductMapper.selectFfwyProductById(productId);
        //获取店铺  获取店铺的名字
        List<FfwyShop> ffwyShops = ffwyShopMapper.selectFfwyShopIdList(ffwyProduct.getShopId());
        //获取评价总数
        FfwyProductComment ffwyProductComments = ffwyProductCommentMapper.selectFfwyProductCommentCount(ffwyProduct.getProductId());
        FfwyProductSearch ffwyProductSearch = new FfwyProductSearch();
        ffwyProductSearch.setCommentNumber(ffwyProductComments.getCommentcount());
        ffwyProductSearch.setCurrentPrice(ffwyProduct.getCurrentPrice().doubleValue());
        ffwyProductSearch.setPhoto(prefixUrl + ffwyProduct.getPhoto());
        ffwyProductSearch.setProductName(ffwyProduct.getProductName());
        if (ffwyShops != null && ffwyShops.size() > 0) {
            ffwyProductSearch.setShopName(ffwyShops.get(0).getShopName());
        }
        ffwyProductSearch.setProductId(ffwyProduct.getProductId());
        productRepository.save(ffwyProductSearch);
    }

    @Override
    public void lowerProductSearch(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public AjaxResult search(SearchParam searchParam) throws IOException {

        SearchRequest searchRequest = buildDslQuery(searchParam);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        List list = new ArrayList();
        for (SearchHit hit : search.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
        }
        return AjaxResult.success("es查询成功", list);
    }

    @Override
    public void importAllvideo(Long videoId) {
        List<FfwyVideoSearch> videoSearches = ffwysearchMapper.selectvideo(videoId);
        for (FfwyVideoSearch videoSearch : videoSearches) {
            FfwyVideoSearch ffwyVideoSearch = new FfwyVideoSearch();
            ffwyVideoSearch.setVideoTrans(videoSearch.getVideoTrans());
            ffwyVideoSearch.setVideoTitle(videoSearch.getVideoTitle());
            ffwyVideoSearch.setVideoPath( videoSearch.getVideoPath());
            ffwyVideoSearch.setVideoLove(videoSearch.getVideoLove());
            ffwyVideoSearch.setVideoIntro(videoSearch.getVideoIntro());
            ffwyVideoSearch.setVideoId(videoSearch.getVideoId());
            ffwyVideoSearch.setVideoComment(videoSearch.getVideoComment());
            ffwyVideoSearch.setUserName(videoSearch.getUserName());
            ffwyVideoSearch.setPhoto(prefixUrl + videoSearch.getPhoto());
            ffwyVideoSearch.setCountcomment(videoSearch.getCountcomment());
            ffwyVideoSearch.setCommentname(videoSearch.getCommentname());
            ffwyVideoSearch.setCommentTime(videoSearch.getCommentTime());
            ffwyVideoSearch.setCreateTime(videoSearch.getCreateTime());
            esvideoRepository.save(ffwyVideoSearch);
        }
    }

    @Override
    public AjaxResult searchvideo(SearchVideo searchVideo) throws IOException {
        if (searchVideo.getType().equals("1")) {
            SearchRequest searchRequest = buildVideoQuery(searchVideo);
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            List list = new ArrayList();
            for (SearchHit hi : search.getHits().getHits()) {
                Integer id = (Integer) hi.getSourceAsMap().get("videoId");
                //视频点赞数量
                FfwyVideoNotlove ffwyVideoNotlove = ffwyVideoLoveMapper.selectFfwyvideoCount(id);
                //视频评论
                FfwyVideoComment ffwyVideoComment = ffwyVideoCommentMapper.selectFfwyVideoCommentCounts(Long.valueOf(id));
                //视频评论第一条
                FfwyVideoComment comment = ffwyVideoCommentMapper.selectFfwyVideoComment(Long.valueOf(id));

                //获取真实用户的id
                //随意在任何地方获取请求响应对象
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                HttpServletRequest request = requestAttributes.getRequest();
                //获取当前用户传入进来的数量
                //真实用户
                FfwyVideoLove ffwyVideoLove = new FfwyVideoLove();
                if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
                    Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
                    //视频本人是否点赞
                    ffwyVideoLove.setVideoId(Long.valueOf(id));
                    ffwyVideoLove.setUserId(Long.valueOf(userId));
                }
                List<FfwyVideoLove> ffwyVideoLoves = ffwyVideoLoveMapper.selectFfwyVideoLoveList(ffwyVideoLove);
                //转发统计数量
                FfwyVideoForward ffwyVideoForward =
                        ffwyVideoForwardMapper.selectFfwyVideoForwardIdCount(Long.valueOf(id));
                FfwyUser user = ffwyUserMapper.selectFfwyUserById(ffwyVideoLove.getUserId());
                FfwyAppVideo ffwyAppVideo = new FfwyAppVideo();
                ffwyAppVideo.setVideoId(Long.valueOf(id));
                List<FfwyvideouserVo> ffwyvideouserVoList = ffwyVideoMapper.selectFfwyVideoList(ffwyAppVideo);
                if (ffwyVideoLove == null) {
                    hi.getSourceAsMap().put("countId", 0);
                }
                if (ffwyVideoLove != null) {
                    hi.getSourceAsMap().put("countId", ffwyVideoNotlove.getCountId());
                }
                if (ffwyVideoComment == null) {
                    hi.getSourceAsMap().put("commentId", 0);
                }
                if (ffwyVideoComment != null) {
                    hi.getSourceAsMap().put("commentId", ffwyVideoComment.getCommentId());
                }
                if (comment == null) {
                    hi.getSourceAsMap().put("comuserId", null);
                    hi.getSourceAsMap().put("commentUserName", 0);
                    hi.getSourceAsMap().put("videoComment", null);
                }
                if (user == null) {
                    hi.getSourceAsMap().put("commentUserphoto", null);
                }
                if (user != null) {
                    hi.getSourceAsMap().put("commentUserphoto",
                            prefixUrl+user.getPhoto());
                }

                if (comment != null) {
                    String userName = comment.getUserName();
                    String videoComment = comment.getVideoComment();
                    Long userId = comment.getUserId();

                    hi.getSourceAsMap().put("commentUserName", userName);
                    hi.getSourceAsMap().put("videoComment", videoComment);
                    hi.getSourceAsMap().put("comuserId", userId);
                }
                if (ffwyVideoForward == null) {
                    hi.getSourceAsMap().put("transpond", null);
                }
                if (ffwyVideoForward != null) {
                    hi.getSourceAsMap().put("transpond",
                            ffwyVideoForward.getVideoId());
                }
                hi.getSourceAsMap().put("commentUserId",
                        ffwyVideoLoves.size());
                if (StringUtils.isEmpty(ffwyvideouserVoList)){
                    hi.getSourceAsMap().put("videoCover",null);
                } else {
                    hi.getSourceAsMap().put("videoCover",ffwyvideouserVoList.get(0).getVideoCover());

                }


                hi.getSourceAsMap().put("userId", ffwyvideouserVoList.get(0).getUserId());
                list.add(hi.getSourceAsMap());
//                {
//                    user.setPhoto(prefixUrl + user.getPhoto());
//                    String userName = comment.getUserName();
//                    String videoComment = comment.getVideoComment();
//                    hi.getSourceAsMap().put("countId", ffwyVideoNotlove.getCountId());
//                    hi.getSourceAsMap().put("commentId", ffwyVideoComment.getCommentId());
//                    //转发代码未写
//                    hi.getSourceAsMap().put("transpond",
//                            ffwyVideoForward.getVideoId());
//                    hi.getSourceAsMap().put("commentUserphoto", user.getPhoto());
//                    hi.getSourceAsMap().put("commentUserName", userName);
//                    hi.getSourceAsMap().put("videoComment", videoComment);
//                    hi.getSourceAsMap().put("commentUserId",
//                            ffwyVideoLoves.size());
//                    list.add(hi.getSourceAsMap());
//                }
            }
            return AjaxResult.success("es查询视频成功", list);
        } else if (searchVideo.getType().equals("2")) {
            SearchRequest searchRequest = buildVideoUserQuery(searchVideo);
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            //获取真实用户的id
            //随意在任何地方获取请求响应对象
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            //获取当前用户传入进来的数量
            //真实用户
            List list = new ArrayList();
            if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
                Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
                if (userId != null) {
                    for (SearchHit hi : search.getHits().getHits()) {
                        Integer vuserId = (Integer) hi.getSourceAsMap().get("userId");
                        FfwyUser ffwyUser = ffwyUserMapper.selectFfwyUserById(Long.valueOf(vuserId));
                        hi.getSourceAsMap().put("photo", prefixUrl + ffwyUser.getPhoto());
                        FfwyUserFans ffwyUserFans = new FfwyUserFans();
                        ffwyUserFans.setUserId(Long.valueOf(vuserId));
                        ffwyUserFans.setFansId(userId);
                        //查询粉丝数量
                        FfwyUserFans user = ffwyUserFansMapper.selectFfwyUserFansByIdCount(Long.valueOf(vuserId));
                        hi.getSourceAsMap().put("type", 0);
                        hi.getSourceAsMap().put("fans", user.getUserId());
                        //查询列表我关注
                        List<FfwyUserFans> ffwyUserFans1 = ffwyUserFansMapper.selectFfwyUserFansList(ffwyUserFans);
                        if (ffwyUserFans1.size() > 0) {
                            hi.getSourceAsMap().put("type", 1);
                            FfwyUserFans userFans = new FfwyUserFans();
                            userFans.setUserId(userId);
                            userFans.setFansId(Long.valueOf(vuserId));
                            //查询相互关注
                            List<FfwyUserFans> fans = ffwyUserFansMapper.selectFfwyUserFansList(userFans);
                            if (fans.size() > 0) {
                                hi.getSourceAsMap().put("type", 2);
                            }
                        }


                        list.add(hi.getSourceAsMap());

                    }
                    List page = page(list, searchVideo.getPageNum(),
                            searchVideo.getPageSize());
                    return AjaxResult.success("es用户登陆查询成功", page);
                }
            }
            for (SearchHit hi : search.getHits().getHits()) {
                list.add(hi.getSourceAsMap());
            }
            List page = page(list, searchVideo.getPageNum(), searchVideo.getPageSize());
            return AjaxResult.success("es用户没有登陆查询成功", page);
        }
        return null;
    }
    /**
     * 循环截取某页列表进行分页
     * @param dataList 分页数据
     * @param pageSize  页面大小
     * @param pageNum   当前页面
     */
    public static List<Map> page(List<Map> dataList, int pageNum,
                               int pageSize) {
        List<Map> currentPageList = new ArrayList<>();
        if (dataList != null && dataList.size() > 0) {
            int currIdx = (pageNum > 1 ? (pageNum - 1) * pageSize : 0);
            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {
                Map data = dataList.get(currIdx + i);
                currentPageList.add(data);
            }
        }
        return currentPageList;
    }

    private SearchRequest buildVideoUserQuery(SearchVideo searchVideo) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取当前用户传入进来的数量
        //真实用户
        if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
            Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
            if (searchVideo.getVideoIntro() != null) {
                //历史搜索存放到redis
                StringBuilder stringBuilder = new StringBuilder("searchvideo:");
                stringBuilder.append(userId);
                List<Object> cacheList = redisCache.getCacheList(stringBuilder.toString());
                if (cacheList.size() >= 10) {
                    String o = (String) cacheList.get(0);
                    for (int i = 0; i < cacheList.size(); i++) {
                        if (searchVideo.getVideoIntro().equals(cacheList.get(i))) {
                            String j = (String) cacheList.get(i);
                            redisCache.remove(stringBuilder.toString(), j, 1);
                            StringBuilder str = new StringBuilder("searchvideo:");
                            str.append(userId);
                            List list = new ArrayList<>();
                            list.add(searchVideo.getVideoIntro());
                            redisCache.setCacheList(str.toString(), list);
                            //搜索资源构建对象
                            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                            //条件的组合对象
                            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                            String videoIntro = searchVideo.getVideoIntro();
                            if (!StringUtils.isEmpty(videoIntro)) {
                                //设置关键词到组合对象中   分词后模糊查询再交集 and
                                boolQueryBuilder.should(QueryBuilders.matchQuery("userName", videoIntro));
                            }
                            sourceBuilder.query(boolQueryBuilder);
                            //搜索请求对象  设置 索引库的名称
                            SearchRequest searchRequest = new SearchRequest("videouser");//指定的要索引的索引库名称
                            //设置索引库中type的名称
                            //  searchRequest.types("info");
                            searchRequest.source(sourceBuilder);
                            return searchRequest;
                        }
                    }
                    redisCache.remove(stringBuilder.toString(), o, 1);
                    StringBuilder str = new StringBuilder("searchvideo:");
                    str.append(userId);
                    List list = new ArrayList<>();
                    list.add(searchVideo.getVideoIntro());
                    redisCache.setCacheList(str.toString(), list);
                    //搜索资源构建对象
                    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                    //条件的组合对象
                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                    String videoIntro = searchVideo.getVideoIntro();
                    if (!StringUtils.isEmpty(videoIntro)) {
                        //设置关键词到组合对象中   分词后模糊查询再交集 and
                        boolQueryBuilder.should(QueryBuilders.matchQuery("userName", videoIntro));
                    }
                    sourceBuilder.query(boolQueryBuilder);
                    //搜索请求对象  设置 索引库的名称
                    SearchRequest searchRequest = new SearchRequest("videouser");//指定的要索引的索引库名称
                    //设置索引库中type的名称
                    //    searchRequest.types("info");
                    searchRequest.source(sourceBuilder);
                    return searchRequest;
                }
                for (int i = 0; i < cacheList.size(); i++) {
                    if (searchVideo.getVideoIntro().equals(cacheList.get(i))) {
                        String o = (String) cacheList.get(i);
                        redisCache.remove(stringBuilder.toString(), o, 1);
                        StringBuilder str = new StringBuilder("searchvideo:");
                        str.append(userId);
                        List list = new ArrayList<>();
                        list.add(searchVideo.getVideoIntro());
                        redisCache.setCacheList(str.toString(), list);
                        //搜索资源构建对象
                        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                        //条件的组合对象
                        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                        String videoIntro = searchVideo.getVideoIntro();
                        if (!StringUtils.isEmpty(videoIntro)) {
                            //设置关键词到组合对象中   分词后模糊查询再交集 and
                            boolQueryBuilder.should(QueryBuilders.matchQuery("userName", videoIntro));
                        }
                        sourceBuilder.query(boolQueryBuilder);
                        //搜索请求对象  设置 索引库的名称
                        SearchRequest searchRequest = new SearchRequest("videouser");//指定的要索引的索引库名称
                        //设置索引库中type的名称
                        //   searchRequest.types("info");
                        searchRequest.source(sourceBuilder);
                        return searchRequest;
                    }
                }
                //历史搜索存放到redis
                StringBuilder str = new StringBuilder("searchvideo:");
                str.append(userId);
                List list = new ArrayList<>();
                list.add(searchVideo.getVideoIntro());
                redisCache.setCacheList(str.toString(), list);
                //搜索资源构建对象
                SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                //条件的组合对象
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                String videoIntro = searchVideo.getVideoIntro();
                if (!StringUtils.isEmpty(videoIntro)) {
                    //设置关键词到组合对象中   分词后模糊查询再交集 and
                    boolQueryBuilder.should(QueryBuilders.matchQuery("userName", videoIntro));
                }
                sourceBuilder.query(boolQueryBuilder);
                //搜索请求对象  设置 索引库的名称
                SearchRequest searchRequest = new SearchRequest("videouser");//指定的要索引的索引库名称
                //设置索引库中type的名称
                //  searchRequest.types("info");
                searchRequest.source(sourceBuilder);
                return searchRequest;
            }
        }
        //搜索资源构建对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //条件的组合对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        String videoIntro = searchVideo.getVideoIntro();
        if (!StringUtils.isEmpty(videoIntro)) {
            //设置关键词到组合对象中   分词后模糊查询再交集 or
            boolQueryBuilder.should(QueryBuilders.matchQuery("userName", videoIntro));
        }
        sourceBuilder.query(boolQueryBuilder);
        //搜索请求对象  设置 索引库的名称
        SearchRequest searchRequest = new SearchRequest("videouser");//指定的要索引的索引库名称
        //设置索引库中type的名称
        //  searchRequest.types("info");
        searchRequest.source(sourceBuilder);
        return searchRequest;

    }

    @Override
    public void lowerVideoSearch(Long videoId) {
        esvideoRepository.deleteById(videoId);
    }

    @Override
    public AjaxResult searchDelect(SearchParam searchParam) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取当前用户传入进来的数量
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        if (userId != null) {
            if (searchParam.getProductName() == null) {
                //redis删除
                StringBuilder stringBuilder = new StringBuilder("search:");
                stringBuilder.append(userId).append("*");
                Set<String> keysList = stringRedisTemplate.keys(stringBuilder.toString());
                return AjaxResult.success("删除成功", stringRedisTemplate.delete(keysList));
            } else if (userId != null && searchParam.getProductName() != null && !searchParam.getProductName().equals("")) {
                StringBuilder stringBuilder = new StringBuilder("search:");
                stringBuilder.append(userId);
                List<Object> cacheList = redisCache.getCacheList(stringBuilder.toString());
                for (int i = 0; i < cacheList.size(); i++) {
                    String o = (String) cacheList.get(0);
                    redisCache.remove(stringBuilder.toString(), o, 1);
                    return AjaxResult.success();
                }


            }

        }
        return AjaxResult.error();
    }


    @Override
    public AjaxResult searchList(SearchParam searchParam) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        StringBuilder stringBuilder = new StringBuilder("search:");
        stringBuilder.append(userId);
        List<String> cacheList = redisCache.getCacheList(stringBuilder.toString());
        //倒序排序
        Collections.reverse(cacheList);
        return AjaxResult.success("历史查询成功", cacheList);
    }

    @Override
    public AjaxResult searchvideoList(SearchVideo searchVideo) {

        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        //获取当前用户传入进来的数量
        StringBuilder stringBuilder = new StringBuilder("searchvideo:");
        stringBuilder.append(userId);
        List<Object> cacheList = redisCache.getCacheList(stringBuilder.toString());
        //倒序排序
        Collections.reverse(cacheList);
        return AjaxResult.success("查询成功", cacheList);
    }

    @Override
    public AjaxResult searchVideoDelect(SearchVideo searchVideo) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取当前用户传入进来的数量
        //真实用户
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        if (userId != null) {
            if (searchVideo.getVideoIntro() == null) {
                //redis删除
                StringBuilder stringBuilder = new StringBuilder("searchvideo:");
                stringBuilder.append(userId).append("*");
                Set<String> keysList = stringRedisTemplate.keys(stringBuilder.toString());
                return AjaxResult.success("删除成功", stringRedisTemplate.delete(keysList));
            } else if (userId != null && searchVideo.getVideoIntro() != null && !searchVideo.getVideoIntro().equals("")) {
                StringBuilder stringBuilder = new StringBuilder("searchvideo:");
                stringBuilder.append(userId);
                List<Object> cacheList = redisCache.getCacheList(stringBuilder.toString());
                for (int i = 0; i < cacheList.size(); i++) {
                    String o = (String) cacheList.get(0);
                    redisCache.remove(stringBuilder.toString(), o, 1);
                    return AjaxResult.success();
                }


            }

        }
        return AjaxResult.error();

    }

    @Override
    public void importUserName(Long userId) {
        List<FfwyUser> users = ffwyUserMapper.selectFfwyUserName(userId);
        users.forEach(user -> {
            FfwyVideoUserSearch ffwyVideoUserSearch = new FfwyVideoUserSearch();
            ffwyVideoUserSearch.setUserName(user.getUserName());
            ffwyVideoUserSearch.setUserId(user.getUserId());
            esvideoUserRepository.save(ffwyVideoUserSearch);
        });
    }

    @Override
    public void searchVideoDelectUser(Long videoId) {
        esvideoRepository.deleteById(videoId);

    }

    @Override
    public void lowervideoUserSearch(Long userId) {
        esvideoUserRepository.deleteById(userId);
    }


    @Override
    public AjaxResult searchDelectvideo(SearchVideo searchVideo) {
        //历史搜索存放到redis
        StringBuilder stringBuilder = new StringBuilder("search:");
        stringBuilder.append(searchVideo.getUserId()).append(searchVideo.getVideoIntro());
        return AjaxResult.success("删除成功", redisCache.deleteObject(stringBuilder.toString()));
    }

    private SearchRequest buildDslQuery(SearchParam searchParam) {
        //获取真实用户的id
        //随意在任何地方获取请求响应对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取当前用户传入进来的数量
        //真实用户
        if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
            Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
            if (searchParam.getProductName() != null) {
                //用户id
                //历史搜索存放到redis
                //获取当前用户传入进来的数量
                StringBuilder stringBuilder = new StringBuilder("search:");
                stringBuilder.append(userId);
                List<Object> cacheList = redisCache.getCacheList(stringBuilder.toString());
                if (cacheList.size() >= 10) {
                    for (int i = 0; i < cacheList.size(); i++) {
                        if (searchParam.getProductName().equals(cacheList.get(i))) {
                            String j = (String) cacheList.get(i);
                            redisCache.remove(stringBuilder.toString(), j, 1);
                            StringBuilder str = new StringBuilder("search:");
                            str.append(userId);
                            List list = new ArrayList<>();
                            list.add(searchParam.getProductName());
                            redisCache.setCacheList(str.toString(), list);
                            //搜索资源构建对象
                            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                            //条件的组合对象
                            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                            String productName = searchParam.getProductName();
                            if (!StringUtils.isEmpty(productName)) {
                                //设置关键词到组合对象中   分词后模糊查询再交集 and
                                boolQueryBuilder.must(QueryBuilders.matchQuery("productName", productName)
                                        .operator(Operator.AND));
                            }
                            sourceBuilder.query(boolQueryBuilder);
                            //搜索请求对象  设置 索引库的名称
                            SearchRequest searchRequest = new SearchRequest("product");//指定的要索引的索引库名称
                            //设置索引库中type的名称
                            searchRequest.types("info");
                            searchRequest.source(sourceBuilder);
                            return searchRequest;
                        }
                    }
                    String o = (String) cacheList.get(0);
                    redisCache.remove(stringBuilder.toString(), o, 1);
                    StringBuilder str = new StringBuilder("search:");
                    str.append(userId);
                    List list = new ArrayList<>();
                    list.add(searchParam.getProductName());
                    redisCache.setCacheList(str.toString(), list);
                    //搜索资源构建对象
                    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                    //条件的组合对象
                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                    String productName = searchParam.getProductName();
                    if (!StringUtils.isEmpty(productName)) {
                        //设置关键词到组合对象中   分词后模糊查询再交集 and
                        boolQueryBuilder.must(QueryBuilders.matchQuery("productName", productName)
                                .operator(Operator.AND));
                    }
                    sourceBuilder.query(boolQueryBuilder);
                    //搜索请求对象  设置 索引库的名称
                    SearchRequest searchRequest = new SearchRequest("product");//指定的要索引的索引库名称
                    //设置索引库中type的名称
                    searchRequest.types("info");
                    searchRequest.source(sourceBuilder);
                    return searchRequest;
                }
                for (int i = 0; i < cacheList.size(); i++) {
                    if (searchParam.getProductName().equals(cacheList.get(i))) {
                        String o = (String) cacheList.get(i);
                        redisCache.remove(stringBuilder.toString(), o, 1);
                        StringBuilder str = new StringBuilder("search:");
                        str.append(userId);
                        List list = new ArrayList<>();
                        list.add(searchParam.getProductName());
                        redisCache.setCacheList(str.toString(), list);
                        //搜索资源构建对象
                        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                        //条件的组合对象
                        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                        String productName = searchParam.getProductName();
                        if (!StringUtils.isEmpty(productName)) {
                            //设置关键词到组合对象中   分词后模糊查询再交集 and
                            boolQueryBuilder.must(QueryBuilders.matchQuery("productName", productName)
                                    .operator(Operator.AND));
                        }
                        sourceBuilder.query(boolQueryBuilder);
                        //搜索请求对象  设置 索引库的名称
                        SearchRequest searchRequest = new SearchRequest("product");//指定的要索引的索引库名称
                        //设置索引库中type的名称
                        searchRequest.types("info");
                        searchRequest.source(sourceBuilder);
                        return searchRequest;
                    }
                }
                StringBuilder str = new StringBuilder("search:");
                str.append(userId);
                List list = new ArrayList<>();
                list.add(searchParam.getProductName());
                redisCache.setCacheList(str.toString(), list);
                //搜索资源构建对象
                SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                //条件的组合对象
                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                String productName = searchParam.getProductName();
                if (!StringUtils.isEmpty(productName)) {
                    //设置关键词到组合对象中   分词后模糊查询再交集 and
                    boolQueryBuilder.must(QueryBuilders.matchQuery("productName", productName)
                            .operator(Operator.AND));
                }
                sourceBuilder.query(boolQueryBuilder);
                //搜索请求对象  设置 索引库的名称
                SearchRequest searchRequest = new SearchRequest("product");//指定的要索引的索引库名称
                //设置索引库中type的名称
                searchRequest.types("info");
                searchRequest.source(sourceBuilder);
                return searchRequest;
            }
        }
        //搜索资源构建对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //条件的组合对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        String productName = searchParam.getProductName();
        if (!StringUtils.isEmpty(productName)) {
            //设置关键词到组合对象中   分词后模糊查询再交集 and

            //MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("productName", productName);
            //BoolQueryBuilder shouldBuilder = new BoolQueryBuilder();
            //shouldBuilder.should(matchQueryBuilder);
            //boolQueryBuilder.must(shouldBuilder);
            boolQueryBuilder.must(QueryBuilders.matchQuery("productName", productName).operator(Operator.AND));

        }
        sourceBuilder.query(boolQueryBuilder);
        //搜索请求对象  设置 索引库的名称
        SearchRequest searchRequest = new SearchRequest("product");//指定的要索引的索引库名称
        //设置索引库中type的名称  --不需要设置
        //searchRequest.types("info");
        searchRequest.source(sourceBuilder);
        return searchRequest;
    }

    private SearchRequest buildVideoQuery(SearchVideo searchVideo) {
        if (searchVideo.getType().equals("1")) {
            //获取真实用户的id
            //随意在任何地方获取请求响应对象
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            //获取当前用户传入进来的数量
            //真实用户
            if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
                Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
                if (searchVideo.getVideoIntro() != null) {
                    //历史搜索存放到redis
                    StringBuilder stringBuilder = new StringBuilder("searchvideo:");
                    stringBuilder.append(userId);
                    List<Object> cacheList = redisCache.getCacheList(stringBuilder.toString());
                    if (cacheList.size() >= 10) {
                        String o = (String) cacheList.get(0);
                        for (int i = 0; i < cacheList.size(); i++) {
                            if (searchVideo.getVideoIntro().equals(cacheList.get(i))) {
                                String j = (String) cacheList.get(i);
                                redisCache.remove(stringBuilder.toString(), j, 1);
                                StringBuilder str = new StringBuilder("searchvideo:");
                                str.append(userId);
                                List list = new ArrayList<>();
                                list.add(searchVideo.getVideoIntro());
                                redisCache.setCacheList(str.toString(), list);
                                //搜索资源构建对象
                                SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                                //条件的组合对象
                                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                                String videoIntro = searchVideo.getVideoIntro();
                                if (!StringUtils.isEmpty(videoIntro)) {
                                    //设置关键词到组合对象中   分词后模糊查询再交集 and
                                    boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
                                    boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
                                }
                                sourceBuilder.query(boolQueryBuilder);
                                //搜索请求对象  设置 索引库的名称
                                SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
                                //设置索引库中type的名称
                                //  searchRequest.types("info");
                                searchRequest.source(sourceBuilder);
                                return searchRequest;
                            }
                        }
                        redisCache.remove(stringBuilder.toString(), o, 1);
                        StringBuilder str = new StringBuilder("searchvideo:");
                        str.append(userId);
                        List list = new ArrayList<>();
                        list.add(searchVideo.getVideoIntro());
                        redisCache.setCacheList(str.toString(), list);
                        //搜索资源构建对象
                        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                        //条件的组合对象
                        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                        String videoIntro = searchVideo.getVideoIntro();
                        if (!StringUtils.isEmpty(videoIntro)) {
                            //设置关键词到组合对象中   分词后模糊查询再交集 and
                            boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
                            boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
                        }
                        sourceBuilder.query(boolQueryBuilder);
                        //搜索请求对象  设置 索引库的名称
                        SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
                        //设置索引库中type的名称
                        //    searchRequest.types("info");
                        searchRequest.source(sourceBuilder);
                        return searchRequest;
                    }
                    for (int i = 0; i < cacheList.size(); i++) {
                        if (searchVideo.getVideoIntro().equals(cacheList.get(i))) {
                            String o = (String) cacheList.get(i);
                            redisCache.remove(stringBuilder.toString(), o, 1);
                            StringBuilder str = new StringBuilder("searchvideo:");
                            str.append(userId);
                            List list = new ArrayList<>();
                            list.add(searchVideo.getVideoIntro());
                            redisCache.setCacheList(str.toString(), list);
                            //搜索资源构建对象
                            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                            //条件的组合对象
                            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                            String videoIntro = searchVideo.getVideoIntro();
                            if (!StringUtils.isEmpty(videoIntro)) {
                                //设置关键词到组合对象中   分词后模糊查询再交集 and
                                boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
                                boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
                            }
                            sourceBuilder.query(boolQueryBuilder);
                            //搜索请求对象  设置 索引库的名称
                            SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
                            //设置索引库中type的名称
                            //   searchRequest.types("info");
                            searchRequest.source(sourceBuilder);
                            return searchRequest;
                        }
                    }
                    //历史搜索存放到redis
                    StringBuilder str = new StringBuilder("searchvideo:");
                    str.append(userId);
                    List list = new ArrayList<>();
                    list.add(searchVideo.getVideoIntro());
                    redisCache.setCacheList(str.toString(), list);
                    //搜索资源构建对象
                    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
                    //条件的组合对象
                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
                    String videoIntro = searchVideo.getVideoIntro();
                    if (!StringUtils.isEmpty(videoIntro)) {
                        //设置关键词到组合对象中   分词后模糊查询再交集 and
                        boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
                        boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
                    }
                    sourceBuilder.query(boolQueryBuilder);
                    //搜索请求对象  设置 索引库的名称
                    SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
                    //设置索引库中type的名称
                    //  searchRequest.types("info");
                    searchRequest.source(sourceBuilder);
                    return searchRequest;
                }
            }
            //搜索资源构建对象
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            //条件的组合对象
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String videoIntro = searchVideo.getVideoIntro();
            if (!StringUtils.isEmpty(videoIntro)) {
                //设置关键词到组合对象中   分词后模糊查询再交集 or
                boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
                boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
            }
            sourceBuilder.query(boolQueryBuilder);
            //搜索请求对象  设置 索引库的名称
            SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
            //设置索引库中type的名称
            //  searchRequest.types("info");
            searchRequest.source(sourceBuilder);
            return searchRequest;
        } else if (searchVideo.getType().equals("2")) {

            //搜索资源构建对象
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            //条件的组合对象
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            String videoIntro = searchVideo.getVideoIntro();
            if (!StringUtils.isEmpty(videoIntro)) {
                //设置关键词到组合对象中   分词后模糊查询再交集 and
                boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
                boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
            }
            sourceBuilder.query(boolQueryBuilder);
            //搜索请求对象  设置 索引库的名称
            SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
            //设置索引库中type的名称
            //  searchRequest.types("info");
            searchRequest.source(sourceBuilder);
            return searchRequest;
//            //获取真实用户的id
//            //随意在任何地方获取请求响应对象
//            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = requestAttributes.getRequest();
//            //获取当前用户传入进来的数量
//            //真实用户
//            if (request.getHeader(JWTUtils.TOKRN) != null && !"".equals(request.getHeader(JWTUtils.TOKRN))) {
//                Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
//                if (searchVideo.getVideoIntro() != null) {
//                    //历史搜索存放到redis
//                    StringBuilder stringBuilder = new StringBuilder("searchvideo:");
//                    stringBuilder.append(userId);
//                    List<Object> cacheList = redisCache.getCacheList(stringBuilder.toString());
//                    if (cacheList.size() >= 10) {
//                        String o = (String) cacheList.get(0);
//                        for (int i = 0; i < cacheList.size(); i++) {
//                            if (searchVideo.getVideoIntro().equals(cacheList.get(i))) {
//                                String j = (String) cacheList.get(i);
//                                redisCache.remove(stringBuilder.toString(), j, 1);
//                                StringBuilder str = new StringBuilder("searchvideo:");
//                                str.append(userId);
//                                List list = new ArrayList<>();
//                                list.add(searchVideo.getVideoIntro());
//                                redisCache.setCacheList(str.toString(), list);
//                                //搜索资源构建对象
//                                SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//                                //条件的组合对象
//                                BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//                                String videoIntro = searchVideo.getVideoIntro();
//                                if (!StringUtils.isEmpty(videoIntro)) {
//                                    //设置关键词到组合对象中   分词后模糊查询再交集 and
//                                    boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
//                                    boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
//                                }
//                                sourceBuilder.query(boolQueryBuilder);
//                                //搜索请求对象  设置 索引库的名称
//                                SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
//                                //设置索引库中type的名称
//                                //  searchRequest.types("info");
//                                searchRequest.source(sourceBuilder);
//                                return searchRequest;
//                            }
//                        }
//                        redisCache.remove(stringBuilder.toString(), o, 1);
//                        StringBuilder str = new StringBuilder("searchvideo:");
//                        str.append(userId);
//                        List list = new ArrayList<>();
//                        list.add(searchVideo.getVideoIntro());
//                        redisCache.setCacheList(str.toString(), list);
//                        //搜索资源构建对象
//                        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//                        //条件的组合对象
//                        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//                        String videoIntro = searchVideo.getVideoIntro();
//                        if (!StringUtils.isEmpty(videoIntro)) {
//                            //设置关键词到组合对象中   分词后模糊查询再交集 and
//                            boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
//                            boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
//                        }
//                        sourceBuilder.query(boolQueryBuilder);
//                        //搜索请求对象  设置 索引库的名称
//                        SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
//                        //设置索引库中type的名称
//                        //    searchRequest.types("info");
//                        searchRequest.source(sourceBuilder);
//                        return searchRequest;
//                    }
//                    for (int i = 0; i < cacheList.size(); i++) {
//                        if (searchVideo.getVideoIntro().equals(cacheList.get(i))) {
//                            String o = (String) cacheList.get(i);
//                            redisCache.remove(stringBuilder.toString(), o, 1);
//                            StringBuilder str = new StringBuilder("searchvideo:");
//                            str.append(userId);
//                            List list = new ArrayList<>();
//                            list.add(searchVideo.getVideoIntro());
//                            redisCache.setCacheList(str.toString(), list);
//                            //搜索资源构建对象
//                            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//                            //条件的组合对象
//                            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//                            String videoIntro = searchVideo.getVideoIntro();
//                            if (!StringUtils.isEmpty(videoIntro)) {
//                                //设置关键词到组合对象中   分词后模糊查询再交集 and
//                                boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
//                                boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
//                            }
//                            sourceBuilder.query(boolQueryBuilder);
//                            //搜索请求对象  设置 索引库的名称
//                            SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
//                            //设置索引库中type的名称
//                            //   searchRequest.types("info");
//                            searchRequest.source(sourceBuilder);
//                            return searchRequest;
//                        }
//                    }
//                    //历史搜索存放到redis
//                    StringBuilder str = new StringBuilder("searchvideo:");
//                    str.append(userId);
//                    List list = new ArrayList<>();
//                    list.add(searchVideo.getVideoIntro());
//                    redisCache.setCacheList(str.toString(), list);
//                    //搜索资源构建对象
//                    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//                    //条件的组合对象
//                    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//                    String videoIntro = searchVideo.getVideoIntro();
//                    if (!StringUtils.isEmpty(videoIntro)) {
//                        //设置关键词到组合对象中   分词后模糊查询再交集 and
//                        boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
//                        boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
//                    }
//                    sourceBuilder.query(boolQueryBuilder);
//                    //搜索请求对象  设置 索引库的名称
//                    SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
//                    //设置索引库中type的名称
//                    //  searchRequest.types("info");
//                    searchRequest.source(sourceBuilder);
//                    return searchRequest;
//                }
//            }
//            //搜索资源构建对象
//            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//            //条件的组合对象
//            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//            String videoIntro = searchVideo.getVideoIntro();
//            if (!StringUtils.isEmpty(videoIntro)) {
//                //设置关键词到组合对象中   分词后模糊查询再交集 or
//                boolQueryBuilder.should(QueryBuilders.matchQuery("videoIntro", videoIntro));
//                boolQueryBuilder.should(QueryBuilders.matchQuery("videoTitle", videoIntro));
//            }
//            sourceBuilder.query(boolQueryBuilder);
//            //搜索请求对象  设置 索引库的名称
//            SearchRequest searchRequest = new SearchRequest("video");//指定的要索引的索引库名称
//            //设置索引库中type的名称
//            //  searchRequest.types("info");
//            searchRequest.source(sourceBuilder);
//            return searchRequest;
        }
        return null;
    }


}
