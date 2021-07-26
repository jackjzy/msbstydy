package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.admin.FfwyRole;
import com.ruoyi.system.mapper.admin.FfwyRoleMapper;
import com.ruoyi.system.service.IFfwyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Service
public class FfwyRoleServiceImpl implements IFfwyRoleService
{
    @Autowired
    private FfwyRoleMapper ffwyRoleMapper;

    /**
     * 查询角色信息
     * 
     * @param roleId 角色信息ID
     * @return 角色信息
     */
    @Override
    public FfwyRole selectFfwyRoleById(Long roleId)
    {
        return ffwyRoleMapper.selectFfwyRoleById(roleId);
    }

    /**
     * 查询角色信息列表
     * 
     * @param ffwyRole 角色信息
     * @return 角色信息
     */
    @Override
    public List<FfwyRole> selectFfwyRoleList(FfwyRole ffwyRole)
    {
        return ffwyRoleMapper.selectFfwyRoleList(ffwyRole);
    }

    /**
     * 新增角色信息
     * 
     * @param ffwyRole 角色信息
     * @return 结果
     */
    @Override
    public int insertFfwyRole(String ffwyRole)
    {
        FfwyRole ffwyRole1 = new FfwyRole(ffwyRole,"0","0","0");
        ffwyRole1.setCreateTime(DateUtils.getNowDate());
        ffwyRole1.setRoleSort(1L);
        return ffwyRoleMapper.insertFfwyRole(ffwyRole1);
    }

    /**
     * 修改角色信息
     * 
     * @param ffwyRole 角色信息
     * @return 结果
     */
    @Override
    public int updateFfwyRole(FfwyRole ffwyRole)
    {
        ffwyRole.setUpdateTime(DateUtils.getNowDate());
        return ffwyRoleMapper.updateFfwyRole(ffwyRole);
    }

    /**
     * 批量删除角色信息
     * 
     * @param roleIds 需要删除的角色信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyRoleByIds(Long[] roleIds)
    {
        return ffwyRoleMapper.deleteFfwyRoleByIds(roleIds);
    }

    /**
     * 删除角色信息信息
     * 
     * @param roleId 角色信息ID
     * @return 结果
     */
    @Override
    public int deleteFfwyRoleById(Long roleId)
    {
        return ffwyRoleMapper.deleteFfwyRoleById(roleId);
    }
}
