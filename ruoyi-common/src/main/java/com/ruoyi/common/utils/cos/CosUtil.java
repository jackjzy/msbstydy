package com.ruoyi.common.utils.cos;

import cn.hutool.core.io.FileUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.Bucket;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.ruoyi.common.exception.file.FileNameLengthLimitExceededException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;


public class CosUtil {
    private static COSClient cosClient=null;

    private static  String bucketName=CosCofig.getBucketName();


    private static String secretId=CosCofig.getSecretId();

    private static String secretKey=CosCofig.getSecretKey();


    static {
        // 1 初始化用户身份信息（secretId, secretKey）。


        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-beijing");

        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议

        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        cosClient= new COSClient(cred, clientConfig);


    }

    public static String CosUpload(MultipartFile file){
        //验证文件名长度
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        //MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION可更改需要使用的格式
        //文件校验
        try {
            FileUploadUtils.assertAllowed(file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (InvalidExtensionException e) {
            e.printStackTrace();
        }

        //生成文件名
        String name =FileUploadUtils.extractFilename(file);
        String fileName="images"+"/"+name;

        File file1 = null;
        try {
            file1 = multipartFileToFile(file);
            file1.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String path = file1.getPath();

        String substring = file1.toURI().toString().substring(6);
        try{
            //        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file1);
            cosClient.putObject(putObjectRequest);
        }catch (Exception e){
            //显示异常信息
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file1);
            cosClient.putObject(putObjectRequest);
        }finally {
            //删除本地文件
            File f=new File(substring);

            f.delete();
        }





        //删除生成的本地文件


        return fileName;
    }


    public static String CosUploadtest(MultipartFile file){
        //验证文件名长度
        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH)
        {
            throw new FileNameLengthLimitExceededException(FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }
        //MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION可更改需要使用的格式
        //文件校验
        try {
            FileUploadUtils.assertAllowed(file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        } catch (InvalidExtensionException e) {
            e.printStackTrace();
        }

        //生成文件名
        String name =FileUploadUtils.extractFilename(file);
        String fileName="text"+"/"+name;

        File file1 = null;
        try {
            file1 = multipartFileToFile(file);
            file1.deleteOnExit();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file1);
        cosClient.putObject(putObjectRequest);
        String path = file1.getPath();

        String substring = file1.toURI().toString().substring(6);


        //删除生成的本地文件
        File f=new File(substring);

        f.delete();

        return fileName;
    }




    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
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

    /**
     * 判断是否是视频
     * @param file
     * @return
     */
    public static boolean isFileType(MultipartFile file){
        String filename = file.getOriginalFilename();
        assert filename != null;
        String extended = filename.substring(filename.lastIndexOf("."));

        if (extended.equals(".wmv") || extended.equals(".mov") || extended.equals(".mpeg")
                || extended.equals(".mpg") || extended.equals(".rmvb") || extended.equals(".rm")
                || extended.equals(".avi") || extended.equals(".mp4") || extended.equals(".3gp")
                || extended.equals(".flv") || extended.equals(".dat")){
            return true;
        }
        return false;
    }


    public static void deletePhoto(String path){
        cosClient.deleteObject(bucketName, path);
    }


}
