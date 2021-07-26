package com.ruoyi.system.domain.admin;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色信息对象 ffwy_role
 * 
 * @author ruoyi
 * @date 2021-04-15
 */
@Data
@NoArgsConstructor
public class FfwyRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色ID */
    private Long roleId;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 角色权限字符串  0：普通权限；  1：工长权限*/
    @Excel(name = "角色权限字符串")
    private String roleKey;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Long roleSort;

    /** 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限） */
    @Excel(name = "数据范围", readConverterExp = "1=：全部数据权限,2=：自定数据权限,3=：本部门数据权限,4=：本部门及以下数据权限")
    private String dataScope;

    /** 菜单树选择项是否关联显示 */
    @Excel(name = "菜单树选择项是否关联显示")
    private Integer menuCheckStrictly;

    /** 部门树选择项是否关联显示 */
    @Excel(name = "部门树选择项是否关联显示")
    private Integer deptCheckStrictly;

    /** 角色状态（0正常 1停用） */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public FfwyRole(String roleName, String roleKey, String status, String delFlag) {
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.status = status;
        this.delFlag = delFlag;
    }

    public FfwyRole(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
