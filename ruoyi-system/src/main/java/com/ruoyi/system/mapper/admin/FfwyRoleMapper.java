package com.ruoyi.system.mapper.admin;

import com.ruoyi.system.domain.admin.FfwyRole;

import java.util.List;

/**
 * 角色信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
public interface FfwyRoleMapper 
{
    /**
     * 查询角色信息
     * 
     * @param roleId 角色信息ID
     * @return 角色信息
     */
    public FfwyRole selectFfwyRoleById(Long roleId);

    /**
     * 查询角色信息列表
     * 
     * @param ffwyRole 角色信息
     * @return 角色信息集合
     */
    public List<FfwyRole> selectFfwyRoleList(FfwyRole ffwyRole);

    /**
     * 新增角色信息
     * 
     * @param ffwyRole 角色信息
     * @return 结果
     */
    public int insertFfwyRole(FfwyRole ffwyRole);

    /**
     * 修改角色信息
     * 
     * @param ffwyRole 角色信息
     * @return 结果
     */
    public int updateFfwyRole(FfwyRole ffwyRole);

    /**
     * 删除角色信息
     * 
     * @param roleId 角色信息ID
     * @return 结果
     */
    public int deleteFfwyRoleById(Long roleId);

    /**
     * 批量删除角色信息
     * 
     * @param roleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFfwyRoleByIds(Long[] roleIds);
}
