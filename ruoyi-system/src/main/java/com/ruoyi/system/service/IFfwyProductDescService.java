package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.product.FfwyProductDesc;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-21
 */
public interface IFfwyProductDescService
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param descId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyProductDesc selectFfwyProductDescById(Long descId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyProductDesc 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<FfwyProductDesc> selectFfwyProductDescList(FfwyProductDesc ffwyProductDesc);

    /**
     * 新增单条
     * @param file
     * @param desc
     * @param productId
     * @return
     */
    public int insertFfwyProductDesc(FfwyProductDesc productDesc, Long productId);

    /**
     *
     * @param file
     * @param desc
     * @param productId
     * @return
     */
    public int insertFfwyProductDescList(List<FfwyProductDesc> productDescs, Long productId);

    /**
     * 上架/下架
     * @param descId
     * @param status
     * @return
     */
    public int updateFfwyProductDesc(Long descId, Boolean status);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param descIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyProductDescByIds(Long[] descIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param descId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteFfwyProductDescById(Long descId);

    public int  deleteFfwyProductDescByProductId(Long productId);
}
