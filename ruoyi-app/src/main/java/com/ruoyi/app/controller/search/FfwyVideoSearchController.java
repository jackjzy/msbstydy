package com.ruoyi.app.controller.search;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.combomeal.FfwyCombomeal;
import com.ruoyi.system.domain.search.*;
import com.ruoyi.system.service.FfwySearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 商品类别Controller
 *
 * @author ruoyi
 * @date 2021-04-15
 */
@Api(tags = "esvideo查询")
@RestController
@RequestMapping("/app/esvideo")
public class FfwyVideoSearchController {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private FfwySearchService searchService;

    @ApiOperation("初始化Emapping映射")
    @GetMapping("/inner/createIndex")
    public AjaxResult createIndex() {
        //1:创建索引库    相当于 Mysql中创建数据库
        elasticsearchRestTemplate.createIndex(FfwyVideoSearch.class);
        //2:映射索引库   相当于 Mysql中创建表
        elasticsearchRestTemplate.putMapping(FfwyVideoSearch.class);
        return AjaxResult.success();
    }

    @ApiOperation("初始化userEmapping映射")
    @GetMapping("/inner/createUserIndex")
    public AjaxResult createUserIndex() {
        //1:创建索引库    相当于 Mysql中创建数据库
        elasticsearchRestTemplate.createIndex(FfwyVideoUserSearch.class);
        //2:映射索引库   相当于 Mysql中创建表
        elasticsearchRestTemplate.putMapping(FfwyVideoUserSearch.class);
        return AjaxResult.success();
    }

    @ApiOperation(value = "导入所有数据库中视频简介到ES")
    @RequestMapping(value = "/importAllvideo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult importAllvideoList(Long videoId ) {
        searchService.importAllvideo(videoId);
        return AjaxResult.success();
    }

    @ApiOperation(value = "导入所有数据库中用户简介到ES")
    @RequestMapping(value = "/importUserName", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult importUserName(Long userId) {
        searchService.importUserName(userId);
        return AjaxResult.success();
    }


    //删除视频
    @ApiOperation("删除视频、删除索引库的数据")
    @GetMapping("/inner/lowervideoSearch")
    public AjaxResult lowerProductSearch(Long videoId) {
        searchService.lowerVideoSearch(videoId);
        return AjaxResult.success();
    }

    //删除视频
    @ApiOperation("删除用户")
    @GetMapping("/inner/lowervideoUserSearch")
    public AjaxResult lowervideoUserSearch(Long userId) {
        searchService.lowervideoUserSearch(userId);
        return AjaxResult.success();
    }

    /**
     * 修改
     */
  /*  @PutMapping("修改视频")
    public AjaxResult edit(@RequestBody FfwyCombomeal ffwyCombomeal)
    {
        return AjaxResult.success();
    }*/
    @ApiOperation("搜索")
    @PostMapping("/searchvideo")
    public AjaxResult searchvideo(@RequestBody SearchVideo searchVideo) throws IOException {
        //执行搜索
        return searchService.searchvideo(searchVideo);
    }

    @ApiOperation("历史查询")
    @PostMapping("/searchvideoList")
    public AjaxResult searchvideoList(SearchVideo searchVideo) throws IOException {
        //执行搜索
        return searchService.searchvideoList(searchVideo);
    }

    @ApiOperation("删除历史搜索")
    @PostMapping("/searchVideoDelect")
    public AjaxResult searchVideoDelect(SearchVideo searchVideo) {
        //执行搜索
        return searchService.searchVideoDelect(searchVideo);
    }

    //删除关注
    @ApiOperation("下架商品、删除索引库的数据")
    @GetMapping("/searchVideoDelectUser")
    public AjaxResult searchVideoDelectUser(Long videoId) {
        searchService.searchVideoDelectUser(videoId);
        return AjaxResult.success();
    }

}
