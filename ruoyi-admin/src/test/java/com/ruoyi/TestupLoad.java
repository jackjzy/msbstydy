package com.ruoyi;

import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.common.utils.cos.CosUtil;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RuoYiAdminlication.class})
public class TestupLoad {



    @Test
    public void test1() throws Exception {
//        System.err.println(secretId);
//        System.err.println(secretKey);

        File pdfFile = new File("C:\\Users\\Administrator\\Desktop\\video\\part-00248-3559.jpg");
        FileInputStream fileInputStream = new FileInputStream(pdfFile);
        MultipartFile multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);

        String s = CosUtil.CosUpload(multipartFile);
        System.err.println(s);

    }
}
