package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.FfwyFaqs;
import com.ruoyi.system.domain.vo.FfwyFaqsVo;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface FfwyFaqsMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param faqsId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public FfwyFaqs selectFfwyFaqsById(Long faqsId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @return 【请填写功能名称】集合
     */
    public List<FfwyFaqsVo> selectFfwyFaqsList(FfwyFaqs ffwyFaqs);


    /**
     * 查询【请填写功能名称】列表
     *
     * @return 【请填写功能名称】集合
     */
    public List<FfwyFaqs> selectFfwyFaqsAndReplyList(FfwyFaqs ffwyFaqs);

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyFaqs 【请填写功能名称】
     * @return 结果
     */
    public int insertFfwyFaqs(FfwyFaqs ffwyFaqs);

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFaqs 【请填写功能名称】
     * @return 结果
     */
    public int updateFfwyFaqs(FfwyFaqs ffwyFaqs);

    /**
     * 删除常见问题
     * 
     * @param faqId 常见问题ID
     * @return 结果
     */
    public int deleteFfwyFaqsById(Integer faqId);

    List<FfwyFaqsVo> selectFfwyFaqsLists();
}
