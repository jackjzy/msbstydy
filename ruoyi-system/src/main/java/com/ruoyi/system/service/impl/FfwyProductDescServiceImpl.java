package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.system.mapper.product.FfwyProductPhotoMapper;

import com.ruoyi.common.utils.cos.CosUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.product.FfwyProductDescMapper;
import com.ruoyi.system.domain.product.FfwyProductDesc;
import com.ruoyi.system.service.IFfwyProductDescService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
@Service
public class FfwyProductDescServiceImpl implements IFfwyProductDescService 
{
    private final static Logger LOGGER = LoggerFactory.getLogger(FfwyProductDescServiceImpl.class);
    @Autowired
    private FfwyProductDescMapper ffwyProductDescMapper;
    @Autowired
    private FfwyProductPhotoMapper ffwyProductPhotoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param descId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyProductDesc selectFfwyProductDescById(Long descId)
    {
        return ffwyProductDescMapper.selectFfwyProductDescById(descId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyProductDesc 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyProductDesc> selectFfwyProductDescList(FfwyProductDesc ffwyProductDesc)
    {
        List<FfwyProductDesc> ffwyProductDescs = ffwyProductDescMapper.selectFfwyProductDescList(ffwyProductDesc);
        ffwyProductDescs.forEach(ffwyProduct->{
            //获取详情图片
            ffwyProduct.setPhotot(ffwyProductPhotoMapper.selectFfwyProductPhotoListParticulars(ffwyProduct.getProductId()));

        });
        return ffwyProductDescs;
    }

    /**
     *
     * @param file
     * @param desc
     * @param productId
     * @return
     */
    @Override
    public int insertFfwyProductDesc(FfwyProductDesc productDesc, Long productId)
    {
        return insertDesc(productDesc,productId);
    }

    @Override
    public int insertFfwyProductDescList(List<FfwyProductDesc> productDescs, Long productId) {
        int i = 0;
        for (FfwyProductDesc productDesc : productDescs) {

            i = insertDesc(productDesc,productId);
        }
        return i;
    }

    /**
     * 添加详情描述
     * @param desc
     * @param productId
     * @return
     */
    private int insertDesc(FfwyProductDesc ffwyProductDesc,Long productId){

        List<FfwyProductDesc> ffwyProductDescs = ffwyProductDescMapper.selectFfwyProductDescList(new FfwyProductDesc(productId, "0", true));
        // 如果已有文字描述，先下架
        if (ffwyProductDescs.size() > 0){
            FfwyProductDesc roductDesc = ffwyProductDescs.get(0);
            roductDesc.setDescStatus(false);
            ffwyProductDescMapper.updateFfwyProductDesc(roductDesc);
        }
        ffwyProductDesc.setDescSort(0L);
        ffwyProductDesc.setDescStatus(true);
        ffwyProductDesc.setProductId(productId);
        LOGGER.info("FfwyProductDescServiceImpl  insertDesc  ffwyProductDesc:",ffwyProductDesc);
        return ffwyProductDescMapper.insertFfwyProductDesc(ffwyProductDesc);
    }

    /**
     * 上架/下架
     * @param descId
     * @param status
     * @return
     */
    @Override
    public int updateFfwyProductDesc(Long descId, Boolean status)
    {
        return ffwyProductDescMapper.updateFfwyProductDesc(new FfwyProductDesc(descId,status));
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param descIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductDescByIds(Long[] descIds)
    {
        return ffwyProductDescMapper.deleteFfwyProductDescByIds(descIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param descId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProductDescById(Long descId)
    {
        return ffwyProductDescMapper.deleteFfwyProductDescById(descId);
    }

    @Override
    public int deleteFfwyProductDescByProductId(Long productId) {
        return ffwyProductDescMapper.deleteFfwyProductDescByProductId(productId);
    }
}
