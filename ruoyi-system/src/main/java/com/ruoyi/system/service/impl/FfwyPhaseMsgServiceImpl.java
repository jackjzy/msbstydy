package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhaseMsg;
import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhasePhoto;
import com.ruoyi.system.domain.shop.FfwyShopPhoto;
import com.ruoyi.system.mapper.combomealorders.FfwyPhaseMsgMapper;
import com.ruoyi.system.mapper.combomealorders.FfwyPhasePhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyPhaseMsgService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-22
 */
@Service
public class FfwyPhaseMsgServiceImpl implements IFfwyPhaseMsgService 
{
    @Autowired
    private FfwyPhaseMsgMapper ffwyPhaseMsgMapper;

    @Autowired
    private FfwyPhasePhotoMapper ffwyPhasePhotoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param phaseMsgId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyPhaseMsg selectFfwyPhaseMsgById(Long phaseMsgId)
    {
        return ffwyPhaseMsgMapper.selectFfwyPhaseMsgById(phaseMsgId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyPhaseMsg> selectFfwyPhaseMsgList(FfwyPhaseMsg ffwyPhaseMsg)
    {
        return ffwyPhaseMsgMapper.selectFfwyPhaseMsgList(ffwyPhaseMsg);
    }

    @Override
    public List<FfwyPhaseMsg> selectFfwyPhaseMsgAndPhoto(FfwyPhaseMsg ffwyPhaseMsg) {
        List<FfwyPhaseMsg> ffwyPhaseMsgs = ffwyPhaseMsgMapper.selectFfwyPhaseMsgAndPhoto(ffwyPhaseMsg);
        ffwyPhaseMsgs.forEach(ffwyPhaseMsg1 -> {
            List<FfwyPhasePhoto> ffwyPhasePhotos = ffwyPhaseMsg1.getFfwyPhasePhotos();
            ffwyPhasePhotos.forEach(ffwyPhasePhoto -> {
                ffwyPhasePhoto.setPhasePhoto(CosCofig.getPrefixUrl()+ffwyPhasePhoto.getPhasePhoto());
            });
        });
        return ffwyPhaseMsgs;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyPhaseMsg(FfwyPhaseMsg ffwyPhaseMsg)
    {
        ffwyPhaseMsg.setCreateTime(DateUtils.getNowDate());
        return ffwyPhaseMsgMapper.insertFfwyPhaseMsg(ffwyPhaseMsg);
    }

    @Override
    public int insertFfwyPhaseMsgAndPhoto(FfwyPhaseMsg ffwyPhaseMsg, MultipartFile[] files) {
        int sum=0;
        int i2 = ffwyPhaseMsgMapper.insertFfwyPhaseMsg(ffwyPhaseMsg);
        sum=sum+i2;
        Long msgId = ffwyPhaseMsg.getPhaseMsgId();
        if (files.length>0){

            for (int i=0;i<files.length;i++){
                /*
                 * 上传腾讯云返回路径
                 *
                 * */
                String path=null;
                try {
                    path = CosUtil.CosUpload(files[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //创建节点图片对象
                FfwyPhasePhoto ffwyPhasePhoto = new FfwyPhasePhoto();
                ffwyPhasePhoto.setPhasePhoto(path);
                ffwyPhasePhoto.setPhaseMsgId(msgId);
                ffwyPhasePhoto.setCreateTime(new Date());
                ffwyPhasePhoto.setUpdateTime(new Date());
                int i1 = ffwyPhasePhotoMapper.insertFfwyPhasePhoto(ffwyPhasePhoto);
                sum=sum+i1;
            }
        }
        //返回修改数据的条数
        return sum;
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyPhaseMsg 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyPhaseMsg(FfwyPhaseMsg ffwyPhaseMsg)
    {
        ffwyPhaseMsg.setUpdateTime(DateUtils.getNowDate());
        return ffwyPhaseMsgMapper.updateFfwyPhaseMsg(ffwyPhaseMsg);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param phaseMsgIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPhaseMsgByIds(Long[] phaseMsgIds)
    {
        return ffwyPhaseMsgMapper.deleteFfwyPhaseMsgByIds(phaseMsgIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param phaseMsgId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyPhaseMsgById(Long phaseMsgId)
    {
        return ffwyPhaseMsgMapper.deleteFfwyPhaseMsgById(phaseMsgId);
    }
}
