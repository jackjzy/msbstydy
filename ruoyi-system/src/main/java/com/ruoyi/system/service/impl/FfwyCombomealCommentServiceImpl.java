package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.cos.CosUtil;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.domain.combomeal.FfwyCombomealComment;
import com.ruoyi.system.domain.combomeal.FfwyCombomealCommentPhoto;
import com.ruoyi.system.domain.combomealorders.phaseMsg.FfwyPhasePhoto;
import com.ruoyi.system.mapper.combomeal.FfwyCombomealCommentPhotoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FfwyCombomealCommentMapper;
import com.ruoyi.system.service.IFfwyCombomealCommentService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-27
 */
@Service
public class FfwyCombomealCommentServiceImpl implements IFfwyCombomealCommentService {
    @Autowired
    private FfwyCombomealCommentMapper ffwyCombomealCommentMapper;

    @Autowired
    private FfwyCombomealCommentPhotoMapper ffwyCombomealCommentPhotoMapper;

    @Value("${cos.prefixUrl}")
    private String prefixUrl;

    /**
     * 查询【请填写功能名称】
     *
     * @param commentId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyCombomealComment selectFfwyCombomealCommentById(Long commentId) {
        return ffwyCombomealCommentMapper.selectFfwyCombomealCommentById(commentId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyCombomealComment> selectFfwyCombomealCommentList(FfwyCombomealComment ffwyCombomealComment) {
        List<FfwyCombomealComment> ffwyCombomealComments = ffwyCombomealCommentMapper.selectFfwyCombomealCommentAndPhoto(ffwyCombomealComment);
        for (FfwyCombomealComment combomealComment : ffwyCombomealComments) {

            FfwyUser ffwyUser = new FfwyUser();
            ffwyUser.setPhoto(prefixUrl + combomealComment.getFfwyUser().getPhoto());
            combomealComment.setFfwyUser(ffwyUser);

            List<FfwyCombomealCommentPhoto> photos = combomealComment.getPhotos();
            for (FfwyCombomealCommentPhoto combomealCommentPhoto : photos) {
                String photoPath = combomealCommentPhoto.getPhotoPath();
                combomealCommentPhoto.setPhotoPath(prefixUrl + photoPath);
            }

            combomealComment.setPhotos(photos);

        }
        return ffwyCombomealComments;
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyCombomealComment(FfwyCombomealComment ffwyCombomealComment) {
        ffwyCombomealComment.setCreateTime(DateUtils.getNowDate());
        return ffwyCombomealCommentMapper.insertFfwyCombomealComment(ffwyCombomealComment);
    }

    @Override
    public int insertFfwyCombomealCommentAndPhoto(FfwyCombomealComment ffwyCombomealComment, MultipartFile[] files) {
        int sum = 0;
        ffwyCombomealComment.setCreateTime(DateUtils.getNowDate());
        System.err.println(ffwyCombomealComment);

        int i1 = ffwyCombomealCommentMapper.insertFfwyCombomealComment(ffwyCombomealComment);
        sum = sum + i1;
        Long commentId = ffwyCombomealComment.getCommentId();
        if (files!=null&&files.length > 0) {

            for (int i = 0; i < files.length; i++) {
                System.err.println(files[i].getOriginalFilename());
                /*
                 * 上传腾讯云返回路径
                 *
                 * */
                String path = null;
                try {
                    path = CosUtil.CosUpload(files[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //创建节点图片对象
                FfwyCombomealCommentPhoto photo = new FfwyCombomealCommentPhoto();
                photo.setCombomealCommentId(commentId);
                photo.setPhotoPath(path);
                int i2 = ffwyCombomealCommentPhotoMapper.insertFfwyCombomealCommentPhoto(photo);
                sum = sum + i2;
            }
        }
        return sum;
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param ffwyCombomealComment 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyCombomealComment(FfwyCombomealComment ffwyCombomealComment) {
        return ffwyCombomealCommentMapper.updateFfwyCombomealComment(ffwyCombomealComment);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param commentIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealCommentByIds(Long[] commentIds) {
        return ffwyCombomealCommentMapper.deleteFfwyCombomealCommentByIds(commentIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param commentId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyCombomealCommentById(Long commentId) {
        return ffwyCombomealCommentMapper.deleteFfwyCombomealCommentById(commentId);
    }
}
