package com.ruoyi.app.controller.search;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.search.FfwyProductSearch;
import com.ruoyi.system.domain.search.SearchParam;
import com.ruoyi.system.service.FfwySearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 商品类别Controller
 *
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "es查询")
@RestController
@RequestMapping("/app/es/product")
public class FfwyProductSearchController {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private FfwySearchService searchService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation("初始化Emapping映射")
    @GetMapping("/inner/createIndex")
    public AjaxResult createIndex() {
        try {
            //1:创建索引库    相当于 Mysql中创建数据库
            elasticsearchRestTemplate.createIndex(FfwyProductSearch.class);
            //2:映射索引库   相当于 Mysql中创建表
            elasticsearchRestTemplate.putMapping(FfwyProductSearch.class);
        } catch (Exception e) {
            System.err.println(JSONObject.toJSONString(e));
            if (JSONObject.toJSONString(e).contains("already exists")) return AjaxResult.success("索引已创建!");
        }

        return AjaxResult.success();
    }

    @ApiOperation(value = "上架商品到es中")
    @PostMapping(value = "/inner/upperGoods")
    @ResponseBody
    public AjaxResult importAllList(Long productId) {
        searchService.importAll(productId);
        return AjaxResult.success();
    }

    //下架商品
    @ApiOperation("下架商品、删除索引库的数据")
    @GetMapping("/inner/lowerProductSearch")
    public AjaxResult lowerProductSearch(Long productId) {
        searchService.lowerProductSearch(productId);
        return AjaxResult.success();
    }

    @ApiOperation("查询")
    @GetMapping("/search")
    public AjaxResult search(SearchParam searchParam) throws IOException {
        //执行搜索
        return searchService.search(searchParam);
    }

    @ApiOperation("删除历史搜索")
    @PostMapping("/searchDelect")
    public AjaxResult searchDelect(@RequestBody SearchParam searchParam) throws IOException {
        //执行搜索
        return searchService.searchDelect(searchParam);
    }

    @ApiOperation("历史查询")
    @GetMapping("/searchList")
    public AjaxResult searchList(SearchParam searchParam) throws IOException {
        //执行搜索
        return searchService.searchList(searchParam);
    }
}
