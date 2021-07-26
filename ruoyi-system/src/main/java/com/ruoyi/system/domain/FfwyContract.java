package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 合同信息对象 ffwy_contract
 * 
 * @author ruoyi
 * @date 2021-07-15
 */
public class FfwyContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 合同id */
    private Long contractId;

    /** 所属的用户 */
    @Excel(name = "所属的用户")
    private Long userId;

    /** 用户生成的用户账号 */
    @Excel(name = "用户生成的用户账号")
    private String accountId;

    /** 生成的企业账号 */
    @Excel(name = "生成的企业账号")
    private String orgId;

    /** 个人印章id */
    @Excel(name = "个人印章id")
    private String psnSealId;

    /** 企业印章id */
    @Excel(name = "企业印章id")
    private String orgSealId;

    /** 文件id */
    @Excel(name = "文件id")
    private String fileId;

    /** 上传路径 */
    @Excel(name = "上传路径")
    private String uploadUrl;

    /** 流程id */
    @Excel(name = "流程id")
    private String flowid;

    /** 签署短连接（三十天有效） */
    @Excel(name = "签署短连接", readConverterExp = "三=十天有效")
    private String shorturl;

    /** 签署长链接（永久） */
    @Excel(name = "签署长链接", readConverterExp = "永=久")
    private String url;

    /** 创建用户账号唯一标识 */
    @Excel(name = "创建用户账号唯一标识")
    private String thirdPartyUserIdPsn;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String namePsn;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String idTypePsn;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idNumberPsn;

    /** 用户手机号码 */
    @Excel(name = "用户手机号码")
    private String mobilePsn;

    /** 创建企业账号唯一标识 */
    @Excel(name = "创建企业账号唯一标识")
    private String thirdPartyUserIdOrg;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String nameOrg;

    /** 企业证件类型 */
    @Excel(name = "企业证件类型")
    private String idTypeOrg;

    /** 企业证件号码 */
    @Excel(name = "企业证件号码")
    private String idNumberOrg;

    /** 文件储存路径 */
    @Excel(name = "文件储存路径")
    private String filePhas;
    /** 文件储存路径 */
    @Excel(name = "是否在e签宝创建流程")
    private String createStatus;
    /** 文件储存路径 */
    @Excel(name = "创建时间")
    private Date createTime;
    /** 文件储存路径 */
    @Excel(name = "签署流程")
    private Date sigeTime;


    public String getCreateStatus() {
        return createStatus;
    }

    public void setCreateStatus(String createStatus) {
        this.createStatus = createStatus;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSigeTime() {
        return sigeTime;
    }

    public void setSigeTime(Date sigeTime) {
        this.sigeTime = sigeTime;
    }

    public String getFilePhas() {
        return filePhas;
    }

    public void setFilePhas(String filePhas) {
        this.filePhas = filePhas;
    }

    public void setContractId(Long contractId)
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAccountId(String accountId) 
    {
        this.accountId = accountId;
    }

    public String getAccountId() 
    {
        return accountId;
    }
    public void setOrgId(String orgId) 
    {
        this.orgId = orgId;
    }

    public String getOrgId() 
    {
        return orgId;
    }
    public void setPsnSealId(String psnSealId) 
    {
        this.psnSealId = psnSealId;
    }

    public String getPsnSealId() 
    {
        return psnSealId;
    }
    public void setOrgSealId(String orgSealId) 
    {
        this.orgSealId = orgSealId;
    }

    public String getOrgSealId() 
    {
        return orgSealId;
    }
    public void setFileId(String fileId) 
    {
        this.fileId = fileId;
    }

    public String getFileId() 
    {
        return fileId;
    }
    public void setUploadUrl(String uploadUrl) 
    {
        this.uploadUrl = uploadUrl;
    }

    public String getUploadUrl() 
    {
        return uploadUrl;
    }
    public void setFlowid(String flowid) 
    {
        this.flowid = flowid;
    }

    public String getFlowid() 
    {
        return flowid;
    }
    public void setShorturl(String shorturl) 
    {
        this.shorturl = shorturl;
    }

    public String getShorturl() 
    {
        return shorturl;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setThirdPartyUserIdPsn(String thirdPartyUserIdPsn) 
    {
        this.thirdPartyUserIdPsn = thirdPartyUserIdPsn;
    }

    public String getThirdPartyUserIdPsn() 
    {
        return thirdPartyUserIdPsn;
    }
    public void setNamePsn(String namePsn) 
    {
        this.namePsn = namePsn;
    }

    public String getNamePsn() 
    {
        return namePsn;
    }
    public void setIdTypePsn(String idTypePsn) 
    {
        this.idTypePsn = idTypePsn;
    }

    public String getIdTypePsn() 
    {
        return idTypePsn;
    }
    public void setIdNumberPsn(String idNumberPsn) 
    {
        this.idNumberPsn = idNumberPsn;
    }

    public String getIdNumberPsn() 
    {
        return idNumberPsn;
    }
    public void setMobilePsn(String mobilePsn) 
    {
        this.mobilePsn = mobilePsn;
    }

    public String getMobilePsn() 
    {
        return mobilePsn;
    }
    public void setThirdPartyUserIdOrg(String thirdPartyUserIdOrg) 
    {
        this.thirdPartyUserIdOrg = thirdPartyUserIdOrg;
    }

    public String getThirdPartyUserIdOrg() 
    {
        return thirdPartyUserIdOrg;
    }
    public void setNameOrg(String nameOrg) 
    {
        this.nameOrg = nameOrg;
    }

    public String getNameOrg() 
    {
        return nameOrg;
    }
    public void setIdTypeOrg(String idTypeOrg) 
    {
        this.idTypeOrg = idTypeOrg;
    }

    public String getIdTypeOrg() 
    {
        return idTypeOrg;
    }
    public void setIdNumberOrg(String idNumberOrg) 
    {
        this.idNumberOrg = idNumberOrg;
    }

    public String getIdNumberOrg() 
    {
        return idNumberOrg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("contractId", getContractId())
            .append("userId", getUserId())
            .append("accountId", getAccountId())
            .append("orgId", getOrgId())
            .append("psnSealId", getPsnSealId())
            .append("orgSealId", getOrgSealId())
            .append("fileId", getFileId())
            .append("uploadUrl", getUploadUrl())
            .append("flowid", getFlowid())
            .append("shorturl", getShorturl())
            .append("url", getUrl())
            .append("thirdPartyUserIdPsn", getThirdPartyUserIdPsn())
            .append("namePsn", getNamePsn())
            .append("idTypePsn", getIdTypePsn())
            .append("idNumberPsn", getIdNumberPsn())
            .append("mobilePsn", getMobilePsn())
            .append("thirdPartyUserIdOrg", getThirdPartyUserIdOrg())
            .append("nameOrg", getNameOrg())
            .append("idTypeOrg", getIdTypeOrg())
            .append("idNumberOrg", getIdNumberOrg())
            .append("filePhas",getFilePhas())
            .append("createStatus",getCreateStatus())
            .append("createTime",getCreateTime())
            .append("sigeTime",getSigeTime())
            .toString();
    }
}
