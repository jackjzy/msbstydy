package com.ruoyi.app.util.hz.factory.response.other;
/**
 * @description  悟空API
 * @author  澄泓
 * @date  2020/10/30 10:21
 * @version 
 */
public class DataSignInfo {
    private Cert cert;
    private Signature signature;

    public Cert getCert() {
        return cert;
    }

    public void setCert(Cert cert) {
        this.cert = cert;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }
}
