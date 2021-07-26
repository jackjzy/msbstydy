package com.ruoyi.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EqbConfig {
    private static String host;
    private static String projectId;
    private static String projectScert;
    private static String idNumberOrg;

    public static String getHost() {
        return host;
    }
    @Value("${sass.host}")
    public  void setHost(String host) {
        EqbConfig.host = host;
    }

    public static String getProjectId() {
        return projectId;
    }
    @Value("${sass.projectId}")
    public  void setProjectId(String projectId) {
        EqbConfig.projectId = projectId;
    }

    public static String getProjectScert() {
        return projectScert;
    }
    @Value("${sass.projectScert}")
    public  void setProjectScert(String projectScert) {
        EqbConfig.projectScert = projectScert;
    }

    public static String getIdNumberOrg() {
        return idNumberOrg;
    }
    @Value("${sass.idNumberOrg}")
    public  void setIdNumberOrg(String idNumberOrg) {
        EqbConfig.idNumberOrg = idNumberOrg;
    }
}
