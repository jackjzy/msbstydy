/*
package com.ruoyi.common.utils.vod;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.utils.cos.CosCofig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.java.Log;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Log
public class VodUtil {
    private static VodUploadClient client=null;

    private static  String bucketName=CosCofig.getBucketName();


    private static String secretId=CosCofig.getSecretId();

    private static String secretKey=CosCofig.getSecretKey();


    static {
        // 1 初始化用户身份信息（secretId, secretKey）。


         client = new VodUploadClient(secretId, secretKey);

    }

    public static String CosUpload(MultipartFile file) throws Exception {
        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath("/Users/zhangxu/Desktop/aaa.mp4");

        try {
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            log.info("Upload FileId "+response.getFileId());

        } catch (Exception e) {
            // 业务方进行异常处理
            log.info("Upload Err"+e);
        }
        return null;
    }

    */
/**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     *//*

    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }
    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deletePhoto(String path){
        //cosClient.deleteObject(bucketName, path);
    }

    public static void main(String[] args) {

        String secretId = "AKIDXsypcBn58C0by6CYsxiFFzMVNVNzoTKw";
        String secretKey = "ZtHQ5oHo5tqXCbfldS2Lj3A881viJ5Z0";
        VodUploadClient client = new VodUploadClient(secretId, secretKey);

        VodUploadRequest request = new VodUploadRequest();
        request.setMediaFilePath("/Users/zhangxu/Desktop/aaa.mp4");

        try {
            VodUploadResponse response = client.upload("ap-beijing", request);
            log.info("Upload FileId "+response.getFileId());
            log.info("videourl"+response.getMediaUrl());
        } catch (Exception e) {
            // 业务方进行异常处理
            log.info("Upload Err"+e);
        }
    }
}
*/
