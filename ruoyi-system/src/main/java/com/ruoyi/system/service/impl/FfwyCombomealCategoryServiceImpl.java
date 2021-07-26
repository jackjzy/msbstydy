package com.ruoyi.system.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.combomeal.*;
import com.ruoyi.system.mapper.combomeal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IFfwyCombomealCategoryService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-25
 */
@Service
public class FfwyCombomealCategoryServiceImpl implements IFfwyCombomealCategoryService {
    @Autowired
    private FfwyCombomealCategoryMapper ffwyCombomealCategoryMapper;

    @Autowired
    private FfwyCombomealWiringMapper ffwyCombomealWiringMapper;

    @Autowired
    private FfwyCombomealSoftMapper ffwyCombomealSoftMapper;

    @Autowired
    private FfwyCombomealCommodityMapper ffwyCombomealCommodityMapper;

    @Autowired
    private FfwyCombomealHardMapper ffwyCombomealHardMapper;

    @Autowired
    private FfwyCombomealSmartMapper ffwyCombomealSmartMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param categoryId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomealCategory selectFfwyCombomealCategoryById(Long categoryId) {
        return ffwyCombomealCategoryMapper.selectFfwyCombomealCategoryById(categoryId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyCombomealCategory> selectFfwyCombomealCategoryList(FfwyCombomealCategory ffwyCombomealCategory) {
        return ffwyCombomealCategoryMapper.selectFfwyCombomealCategoryList(ffwyCombomealCategory);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealCategory(FfwyCombomealCategory ffwyCombomealCategory) {
        ffwyCombomealCategory.setCreateTime(DateUtils.getNowDate());
        return ffwyCombomealCategoryMapper.insertFfwyCombomealCategory(ffwyCombomealCategory);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public Map<String, Object> insertFfwyCombomealCategoryPC(FfwyCombomealCategory ffwyCombomealCategory,
                                                             MultipartFile fileCover, MultipartFile fileCategoryNote) {

        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put("code", 0);

        ffwyCombomealCategory.setCreateTime(DateUtils.getNowDate());

//        if (null == ffwyCombomealCategory.getParentId() || "".equals( ffwyCombomealCategory.getParentId() )) {
//            dataMap.put("resmsg", "二级分类id不能为空");
//            return dataMap;
//        }
        if (null == ffwyCombomealCategory.getCombomaealName() || "".equals(ffwyCombomealCategory.getCombomaealName())) {
            dataMap.put("resmsg", "套餐名称不能为空");
            return dataMap;
        } else {
            FfwyCombomealCategory ffwyCombomealCategoryTemp = ffwyCombomealCategoryMapper.
                    selectFfwyCombomealCategoryByCombomaealName(ffwyCombomealCategory.getCombomaealName());
            if (null != ffwyCombomealCategoryTemp) {
                dataMap.put("resmsg", "套餐已存在！");
                return dataMap;
            }
        }
        if (null == ffwyCombomealCategory.getPrice() || "".equals(ffwyCombomealCategory.getPrice())) {
            dataMap.put("resmsg", "套餐价格不能为空");
            return dataMap;
        }
        if (null == ffwyCombomealCategory.getWiringProductId() || "".equals(ffwyCombomealCategory.getWiringProductId())) {
            dataMap.put("resmsg", "家电产品(商品id)不能为空");
            return dataMap;
        } else {
            FfwyCombomealWiring ffwyCombomealWiring = ffwyCombomealWiringMapper.
                    selectFfwyCombomealWiringById(ffwyCombomealCategory.getWiringProductId());
            if (null == ffwyCombomealWiring) {
                dataMap.put("resmsg", "家电产品不存在！");
                return dataMap;
            }
        }
        if (null == ffwyCombomealCategory.getSoftProductId() || "".equals(ffwyCombomealCategory.getSoftProductId())) {
            dataMap.put("resmsg", "软装产品(商品id)不能为空");
            return dataMap;
        } else {
            FfwyCombomealSoft ffwyCombomealSoft = ffwyCombomealSoftMapper.
                    selectFfwyCombomealSoftById(ffwyCombomealCategory.getSoftProductId());
            if (null == ffwyCombomealSoft) {
                dataMap.put("resmsg", "软装产品不存在！");
                return dataMap;
            }
        }
        if (null == ffwyCombomealCategory.getDityProductId() || "".equals(ffwyCombomealCategory.getDityProductId())) {
            dataMap.put("resmsg", "生活日用品(商品id)不能为空");
            return dataMap;
        } else {
            FfwyCombomealCommodity ffwyCombomealCommodity = ffwyCombomealCommodityMapper.
                    selectFfwyCombomealCommodityById(ffwyCombomealCategory.getDityProductId());
            if (null == ffwyCombomealCommodity) {
                dataMap.put("resmsg", "生活日用品不存在！");
                return dataMap;
            }
        }
//        if (null == ffwyCombomealCategory.getCoverFile() || "".equals( ffwyCombomealCategory.getCoverFile() )) {
//            dataMap.put("resmsg", "封面图3D链接和图片不能为空");
//            return dataMap;
//        }
//        if (null == ffwyCombomealCategory.getCategoryNoteFile() || "".equals( ffwyCombomealCategory.getCategoryNoteFile() )) {
//            dataMap.put("resmsg", "套餐说明和套餐详情图/添加轮播图(轮播图链接和图片)不能为空");
//            return dataMap;
//        }
        if (null == ffwyCombomealCategory.getHardId() || "".equals(ffwyCombomealCategory.getHardId())) {
            dataMap.put("resmsg", "基础硬装不能为空");
            return dataMap;
        } else {
            FfwyCombomealHard ffwyCombomealHard = ffwyCombomealHardMapper.
                    selectFfwyCombomealHardById(ffwyCombomealCategory.getHardId());
            if (null == ffwyCombomealHard) {
                dataMap.put("resmsg", "基础硬装不存在！");
                return dataMap;
            }
        }
        if (null == ffwyCombomealCategory.getSmartId() || "".equals(ffwyCombomealCategory.getSmartId())) {
            dataMap.put("resmsg", "智能化设备不能为空");
            return dataMap;
        } else {
            FfwyCombomealSmart ffwyCombomealSmart = ffwyCombomealSmartMapper.
                    selectFfwyCombomealSmartById(ffwyCombomealCategory.getSmartId());
            if (null == ffwyCombomealSmart) {
                dataMap.put("resmsg", "智能化设备不存在！");
                return dataMap;
            }
        }
        if (null == ffwyCombomealCategory.getSpaceLayout() || "".equals(ffwyCombomealCategory.getSpaceLayout())) {
            dataMap.put("resmsg", "空间布置不能为空");
            return dataMap;
        }

        // 上传文件
        if (fileCover == null) {
            dataMap.put("resmsg", "封面图3D链接和图片不能为空");
            return dataMap;
        } else {
            String path = null;
            try {
                path = CosUtil.CosUpload(fileCover);
                ffwyCombomealCategory.setCoverFile(path);
            } catch (Exception e) {
//                e.printStackTrace();
                dataMap.put("resmsg", "封面图3D链接和图片文件错误上传失败！");
                return dataMap;
            }
        }

        if (fileCategoryNote == null) {
            dataMap.put("resmsg", "套餐说明和套餐详情图/添加轮播图(轮播图链接和图片)不能为空");
            return dataMap;
        } else {
            String path = null;
            try {
                path = CosUtil.CosUpload(fileCategoryNote);
                ffwyCombomealCategory.setCategoryFile(path);
            } catch (Exception e) {
//                e.printStackTrace();
                dataMap.put("resmsg", "套餐说明和套餐详情图/添加轮播图(轮播图链接和图片)文件错误上传失败！");
                return dataMap;
            }
        }

        int insertCode = ffwyCombomealCategoryMapper.insertFfwyCombomealCategory(ffwyCombomealCategory);

        if (1 == insertCode) {
            dataMap.put("code", "1");
            dataMap.put("data", ffwyCombomealCategory);
//            dataMap.put("resmsg", "操作成功！");
            return dataMap;
        }

        return dataMap;
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param ffwyCombomealCategory 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealCategory(FfwyCombomealCategory ffwyCombomealCategory) {
        ffwyCombomealCategory.setUpdateTime(DateUtils.getNowDate());
        return ffwyCombomealCategoryMapper.updateFfwyCombomealCategory(ffwyCombomealCategory);
    }






    /**
     * 批量删除【请填写功能名称】
     *
     * @param categoryIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealCategoryByIds(Long[] categoryIds) {
        return ffwyCombomealCategoryMapper.deleteFfwyCombomealCategoryByIds(categoryIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param categoryId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealCategoryById(Long categoryId) {
        return ffwyCombomealCategoryMapper.deleteFfwyCombomealCategoryById(categoryId);
    }
}
