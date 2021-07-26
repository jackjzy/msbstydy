package com.ruoyi.app.util.hz.factory.antfinsign;


import com.ruoyi.app.util.hz.enums.RequestType;
import com.ruoyi.app.util.hz.factory.request.Request;
import com.ruoyi.app.util.hz.factory.response.CheckAntfinNotaryResponse;

/**
 * @description  悟空API核验签署文件上链信息
 * @author  澄泓
 * @date  2020/10/28 17:41
 * @version JDK1.7
 */
public class CheckAntfinNotary extends Request<CheckAntfinNotaryResponse> {
    private String docHash;
    private String antTxHash;

    private CheckAntfinNotary(){};
    public CheckAntfinNotary(String docHash, String antTxHash) {
        this.docHash = docHash;
        this.antTxHash = antTxHash;
    }

    public String getDocHash() {
        return docHash;
    }

    public void setDocHash(String docHash) {
        this.docHash = docHash;
    }

    public String getAntTxHash() {
        return antTxHash;
    }

    public void setAntTxHash(String antTxHash) {
        this.antTxHash = antTxHash;
    }

    @Override
    public void build() {
        super.setUrl("/v1/checkAntfinNotary");
        super.setRequestType(RequestType.POST);
    }
}
