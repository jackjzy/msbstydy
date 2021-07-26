package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FfwyFaqs;
import com.ruoyi.system.domain.vo.FaqsVo;
import com.ruoyi.system.domain.vo.FfwyFaqsVo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface IFfwyFaqsService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param faqsId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyFaqs selectFfwyFaqsById(Long faqsId);

    /**
     * 查询常见问题
     * 
     * @return 结果
     */
    public List<FfwyFaqsVo> selectFfwyFaqsList(FfwyFaqs ffwyFaqs);
    /**
     * 查询常见问题
     *
     * @return 结果
     */
    public List<FfwyFaqs> selectFfwyFaqsAndReplyList(FfwyFaqs ffwyFaqs);

    /**
     * 新增常见问题
     * 
     * @return 结果
     */
    public int insertFfwyFaqs(FaqsVo faqsVo);

    /**
     * 修改常见问题
     * 
     * @param ffwyFaqsVo 大实体类
     * @return 结果
     */
    public int updateFfwyFaqs(FfwyFaqsVo ffwyFaqsVo);

    /**
     * 删除常见问题
     * 
     * @param faqId 需要删除的常见问题ID
     * @return 结果
     */
    public int deleteFfwyFaqsById(Integer faqId);

}
