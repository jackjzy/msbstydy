package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.FfwyFeedbackPhoto;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.vo.FeedbckVo;
import com.ruoyi.system.mapper.FfwyFeedbackPhotoMapper;
import com.ruoyi.system.mapper.admin.FfwyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyFeedbackMapper;
import com.ruoyi.system.domain.FfwyFeedback;
import com.ruoyi.system.service.IFfwyFeedbackService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyFeedbackServiceImpl implements IFfwyFeedbackService 
{
    @Autowired
    private FfwyFeedbackMapper ffwyFeedbackMapper;

    @Autowired
    private FfwyUserMapper ffwyUserMapper;

    @Autowired
    private FfwyFeedbackPhotoMapper ffwyFeedbackPhotoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param feedbackId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyFeedback selectFfwyFeedbackById(Long feedbackId)
    {
        return ffwyFeedbackMapper.selectFfwyFeedbackById(feedbackId);
    }

    /**
     * 查询所有Feedback列表
     * 
     * @return listFeedbck
     */
    @Override
    public List<FeedbckVo> selectFfwyFeedbackList(FfwyFeedback ffwyFeedback)
    {
        List<FeedbckVo> listFeedbck = null;
        Date actcreateTime = ffwyFeedback.getCreateTime();
        Date begcreateTime = ffwyFeedback.getBeginTime();
        Long feedbackId = ffwyFeedback.getFeedbackId();
        Date createTime = ffwyFeedback.getCreateTime();
        String searchStr = ffwyFeedback.getSearchStr();
        if(actcreateTime != null || begcreateTime != null || feedbackId != null || createTime != null || searchStr != null) {
            listFeedbck = ffwyFeedbackMapper.selectFfwyFeedbackList(ffwyFeedback);
        }
        if(actcreateTime == null && begcreateTime == null && feedbackId == null && createTime == null && searchStr == null){
        listFeedbck = ffwyFeedbackMapper.selectFfwyFeedbackLists();

        }
        return listFeedbck;
    }

    @Override
    public List<FfwyFeedback> selectFfwyFeedback(FfwyFeedback ffwyFeedback) {
        return ffwyFeedbackMapper.selectFfwyFeedback(ffwyFeedback);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyFeedback 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyFeedback(FfwyFeedback ffwyFeedback,Long userId)
    {

        ffwyFeedback.setCreateTime(DateUtils.getNowDate());
        ffwyFeedback.setUserId(userId);
        int i1 = ffwyFeedbackMapper.insertFfwyFeedback(ffwyFeedback);
        List<FfwyFeedbackPhoto> list = ffwyFeedback.getPhotos();
        list.forEach(ffwyFeedbackPhoto -> {
            ffwyFeedbackPhoto.setCrateTime(DateUtils.getNowDate());
            ffwyFeedbackPhoto.setFeedbackId(ffwyFeedback.getFeedbackId());
            ffwyFeedbackPhoto.setCreateTime(DateUtils.getNowDate());
        });
        System.err.println(ffwyFeedback);
        for (FfwyFeedbackPhoto ffwyFeedbackPhoto : list) {
            System.err.println(ffwyFeedbackPhoto);
        }
        ffwyFeedbackPhotoMapper.insertFfwyFeedbackPhoto(list);
        return i1;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyFeedback 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyFeedback(FfwyFeedback ffwyFeedback)
    {
        return ffwyFeedbackMapper.updateFfwyFeedback(ffwyFeedback);
    }

    /**
     * 删除系统信息
     * 
     * @param feedbackId 反馈ID
     * @return 结果
     */
    @Override
    public int deleteFfwyFeedbackById(Integer feedbackId)
    {
        return ffwyFeedbackMapper.deleteFfwyFeedbackById(feedbackId);
    }
}
