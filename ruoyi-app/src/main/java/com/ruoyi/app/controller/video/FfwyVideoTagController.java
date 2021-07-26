package com.ruoyi.app.controller.video;

import com.ruoyi.back.domain.FfwyVideo;
import com.ruoyi.back.service.IFfwyVideoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.JacksonUtil;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.FfwyTag;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.vo.FfwyvideouserVo;
import com.ruoyi.system.service.IFfwyTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.AjAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(tags = "发布")
@RestController
@RequestMapping("/app/apitag")
public class FfwyVideoTagController extends BaseController {

    @Autowired
    private IFfwyTagService ffwyTagService;

    @Autowired
    private IFfwyVideoService iFfwyVideoService;

    /**
     * 查询标签
     * @param ffwyTag
     * @return
     */
    @ApiOperation("查询标签")
    @GetMapping
    public AjaxResult findByvideotag(FfwyTag ffwyTag)
    {
        return AjaxResult.success(ffwyTagService.selectFfwyTagList(ffwyTag));
    }

    @ApiOperation("发布")
    @PostMapping("/pubVido")
    public AjaxResult pubVido(HttpServletRequest request, @RequestBody FfwyVideo ffwyVideo)
    {
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));

        int i = iFfwyVideoService.insertFfwyVideoandTag(ffwyVideo,  userId);
        return toAjax(i);
    }

    @ApiOperation("不感兴趣")
    @GetMapping("/notLoveVido")
    public AjaxResult notLoveVido(HttpServletRequest request, Integer videoId)
    {
        Long userId = JWTUtils.getId(request.getHeader(JWTUtils.TOKRN));
        startPage();
        int i = iFfwyVideoService.insertFfwyNotLoveVideo(userId.intValue(),videoId);
        return toAjax(i);
    }


    /**
     * 查询店铺商品列表
     */
    @ApiOperation("链接中商品列表")
    @GetMapping("/productList")
    public TableDataInfo productList(String userId) {
        startPage();
        List<FfwyProduct> list = iFfwyVideoService.selectFfwyproductList(Long.valueOf(userId));
        if (list != null && list.size()>0) {
            return getDataTable(list);
        } else {

            String msg = "";
            return getDataTable1(msg);
        }

    }

}
