package com.ruoyi.web.controller.combomeal;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.combomeal.*;
import com.ruoyi.system.domain.product.FfwyProduct;
import com.ruoyi.system.domain.product.FfwyProductCategory;
import com.ruoyi.system.domain.product.FfwyProductSku;
import com.ruoyi.system.domain.product.FfwySpecification;
import com.ruoyi.system.domain.vo.*;
import com.ruoyi.system.service.IFfwyCombomealCategoryService;
import com.ruoyi.system.service.IFfwyCombomealService;
import com.ruoyi.system.service.IFfwyProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2021-04-21
 */
@Api(tags = "套餐管理")
@RestController
@RequestMapping("/back/combomeal")
public class FfwyCombomealController extends BaseController {
    @Autowired
    private IFfwyCombomealService ffwyCombomealService;

    @Autowired
    private IFfwyProductCategoryService ffwyProductCategoryService;

    @Autowired
    private IFfwyCombomealCategoryService ffwyCombomealCategoryService;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;


    @ApiOperation("查询所有")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping
    public TableDataInfo findAll(FfwyCombomeal ffwyCombomeal) {
        startPage();
        List<FfwyCombomeal> list = ffwyCombomealService.selectCombomeal(ffwyCombomeal);
        return getDataTable(list);
    }

    /**
     * 查询所有、筛选、搜索套餐订单
     */
    @ApiOperation("套餐内容详情")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/details")
    public TableDataInfo findAllCombomeal(FfwyOrderVo ffwyOrderVo) {
        List<ProductVo> list = ffwyCombomealService.selectFfwyCombomealList(ffwyOrderVo);
        return getDataTable(list);
    }

    /*  *//**
     * 查询所有、筛选、搜索套餐订单
     *//*
    @ApiOperation("套餐商品及商品分类")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/findByproductcat")
    public AjaxResult findByproductcat()
    {
        Map map = ffwyCombomealService.selectfindByproductcat();
        return AjaxResult.success("查询成功",map);

    }
*/


    /**
     * 查询产品分类
     */
    @ApiOperation("查询一级分类")
    @GetMapping("/findByhomeappliancesyj")
    public TableDataInfo findByproductcat() {
        List<String> list = ffwyCombomealService.selectFfwyfindByproductcat();
        return getDataTable(list);

    }

    @ApiOperation("查询家电产品二级目录")
    @RequestMapping(value = "/findByhomeappliancesej/{productCategoryId}", method = RequestMethod.GET)
    public TableDataInfo findByproductcatej(@PathVariable Long productCategoryId) {
        List<String> list = ffwyCombomealService.selectFfwyfindByproductcatej(productCategoryId);
        return getDataTable(list);

    }


    @ApiOperation("查询家电产品、软件、生活日用品、智能化设备三级目录")
    @RequestMapping(value = "/findByhomeappliancesj/{productId}", method = RequestMethod.GET)
    public TableDataInfo findByproductcatsj(@PathVariable Long productId) {
        List<FfwyProduct> list1 = ffwyCombomealService.selectFfwyfindByproductcatName(productId);
        for (FfwyProduct ffwyProduct : list1) {
            String productName = ffwyProduct.getProductName();
            List<FfwyProductSku> list = ffwyCombomealService.selectFfwyfindByproductcatsj(productId);
            for (FfwyProductSku ffwyProductSku : list) {
                String skuSpec = ffwyProductSku.getSkuSpec();
                ffwyProductSku.setSkuSpec(productName + skuSpec);
            }
            return getDataTable(list);
        }
        return null;

    }



    /**
     * 查询所有、筛选、搜索套餐订单
     */
    @ApiOperation("查询商品分类详情")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/findByhomeappliancesdesc/{categoryId}")
    public TableDataInfo findByhomeappliancesdesc(@PathVariable Long categoryId) {
        List<FfwyProductCategory> list = ffwyCombomealService.selectFfwyfindByproductcatdesc(categoryId);
        return getDataTable(list);

    }

    /**
     * 查询所有、筛选、搜索套餐订单
     */
    @ApiOperation("查询商品规格")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:list')")
    @GetMapping("/findByhomeappliancesspe/{parentId}")
    public TableDataInfo findByhomeapplianspe(@PathVariable Long parentId) {
        List<FfwySpecification> list = ffwyCombomealService.selectFfwyfindByproductcatspe(parentId);
        return getDataTable(list);

    }

    /**
     * 新增套餐分类
     */
    @ApiOperation("添加套餐分类")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_product_category:add')")
    @Log(title = "添加套餐分类", businessType = BusinessType.INSERT)
    @PostMapping("/category")
    public AjaxResult insertCatgory(FfwyCombomealCategory ffwyCombomealCategory,
                                    MultipartFile fileCover, MultipartFile fileCategoryNote) {
        Map<String, Object> dataMap = ffwyCombomealCategoryService.insertFfwyCombomealCategoryPC(ffwyCombomealCategory,
                fileCover, fileCategoryNote);
        if ("1".equals(dataMap.get("code"))) {
            return AjaxResult.success(dataMap.get("data"));
        } else {
            return AjaxResult.error((String) dataMap.get("resmsg"));
        }
    }


    /**
     * 添加简装、毛培房套餐
     */
    @ApiOperation("添加简装套餐")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_product_category:add')")
    @Log(title = "添加简装房套餐", businessType = BusinessType.INSERT)
    @PostMapping("/insertFfwyCombomeal")
    public AjaxResult insertFfwyCombomeal(@RequestBody FfwyCombomeal ffwyCombomeal) {
        ffwyCombomeal.setCategoryId(2L);

        return toAjax(ffwyCombomealService.insertFfwyCombomeal(ffwyCombomeal));
    }

    /**
     * 添加简装、毛培房套餐
     */
    @ApiOperation("添加毛坯房套餐")
    //@PreAuthorize("@ss.hasPermi('system:ffwy_product_category:add')")
    @Log(title = "添加毛坯房套餐", businessType = BusinessType.INSERT)
    @PostMapping("/insertFfwyCombomealmp")
    public AjaxResult insertFfwyCombomealmp(@RequestBody FfwyCombomeal ffwyCombomeal) {
        ffwyCombomeal.setCategoryId(1L);

        return toAjax(ffwyCombomealService.insertFfwyCombomeal(ffwyCombomeal));
    }

    /**
     * 编辑套餐分类
     */
    @ApiOperation("编辑套餐分类")
    //@PreAuthorize("@ss.hasPermi('system:combomeal:edit')")
    @Log(title = "【编辑套餐分类】", businessType = BusinessType.UPDATE)
        @PostMapping("/edit")
    public AjaxResult edit(@RequestBody FfwyCombomeal ffwyCombomeal) {
        
        return toAjax(ffwyCombomealService.editFfwyCombomeal(ffwyCombomeal));

    }

    /**
     * 查询套餐分类信息
     */
    @ApiOperation("查询套餐分类信息")
//    @PreAuthorize("@ss.hasPermi('system:ffwy_shop_photo:query')")
    @GetMapping(value = "/getInfo/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId) {
        return AjaxResult.success(ffwyCombomealCategoryService.selectFfwyCombomealCategoryById(categoryId));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:combomeal:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{combomealIds}")
    public AjaxResult remove(@PathVariable Long[] combomealIds) {
        return toAjax(ffwyCombomealService.deleteFfwyCombomealByIds(combomealIds));
    }


    @ApiOperation("图片上传腾讯云方法")
    @PostMapping(value = "/addPhotos",headers = "content-type=multipart/form-data")
    public List<String> addPhotos(MultipartFile[] file)
    {
        List<String> path= new ArrayList<>();
        if (file!=null && file.length>0){
            for (int i=0;i<file.length;i++){

                String path1 = null;
                try {
                    path1 = CosUtil.CosUpload(file[i]);
                    path.add(prefixUrl+path1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return path;
    }


    @ApiOperation("刪除图片腾讯云方法")
    @PostMapping(value = "/deletePhotos")
    public int deletePhotos(String path) {
        /*
         * 上传腾讯云返回路径
         *
         * */

        int i = 0;
        try {
            CosUtil.deletePhoto(StringUtils.remove(path,prefixUrl));
            ffwyCombomealService.deletePhopos(StringUtils.remove(path,prefixUrl));
            i++;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return i;
    }


}
