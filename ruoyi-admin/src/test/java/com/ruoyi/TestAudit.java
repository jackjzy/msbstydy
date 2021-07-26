package com.ruoyi;

import com.ruoyi.back.domain.FfwyAudit;
import com.ruoyi.back.domain.FfwyAuditStatus;
import com.ruoyi.back.domain.queryentity.QueryAudti;
import com.ruoyi.back.service.IFfwyAuditService;
import com.ruoyi.back.service.IFfwyAuditStatusService;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestAudit {

    @Autowired
    IFfwyAuditService iFfwyAuditService;
    @Autowired
    IFfwyAuditStatusService iFfwyAuditStatusService;
    @Autowired
    ISysUserService iSysUserService;

    @Test
    public void test2(){
        SysUser sysUser = iSysUserService.selectUserById((long) 120);
        System.err.println(sysUser);

    }

    @Test
    public void test1(){
        List<FfwyAuditStatus> ffwyAuditStatuses = iFfwyAuditStatusService.selectFfwyAuditStatusList(null);
        for (FfwyAuditStatus ffwyAuditStatus : ffwyAuditStatuses) {
            System.out.println(ffwyAuditStatus);
        }
    }
}
