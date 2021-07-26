package com.ruoyi.system.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.hutool.core.io.file.FileReader;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.protocol.FfwyProtocol;
import com.ruoyi.system.mapper.protocol.FfwyProtocolMapper;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.service.IFfwyProtocolService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-30
 */
@Service
public class FfwyProtocolServiceImpl implements IFfwyProtocolService 
{
    @Autowired
    private FfwyProtocolMapper ffwyProtocolMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param protocolId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public FfwyProtocol selectFfwyProtocolById(Integer protocolId)
    {
        return ffwyProtocolMapper.selectFfwyProtocolById(protocolId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param ffwyProtocol 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<FfwyProtocol> selectFfwyProtocolList(FfwyProtocol ffwyProtocol)
    {
        return ffwyProtocolMapper.selectFfwyProtocolList(ffwyProtocol);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param ffwyProtocol 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertFfwyProtocol(FfwyProtocol ffwyProtocol)
    {
        ffwyProtocol.setCreateTime(DateUtils.getNowDate());
        return ffwyProtocolMapper.insertFfwyProtocol(ffwyProtocol);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param ffwyProtocol 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateFfwyProtocol(FfwyProtocol ffwyProtocol)
    {

        ffwyProtocol.setUpdateTime(DateUtils.getNowDate());
        return ffwyProtocolMapper.updateFfwyProtocol(ffwyProtocol);
    }

    @Override
    public int chageProtocol(MultipartFile file) {
        InputStream inputStream=null;
        String upload=null;
        String content = "";
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath();
        try {
            inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            String suff = fileName.substring(fileName.lastIndexOf(".") + 1);
            upload = FileUploadUtils.upload(filePath,file);
            if (suff.equals("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath + upload);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                content = extractor.getText();
                opcPackage.close();
            } else if (suff.equals("doc")) {
                FileInputStream is = new FileInputStream(filePath + upload);
                WordExtractor ex = new WordExtractor(is);
                content = ex.getText();
                is.close();
            } else if (suff.equals("txt")){
                FileReader fileReader = new FileReader(filePath+upload,"GBK");
                content = fileReader.readString();

            } else if (suff.equals("pdf")){
                content = FileUploadUtils.redingPdf(filePath + upload);
            }else {
                System.out.println("此文件不是word文件");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlException e) {
            e.printStackTrace();
        } catch (OpenXML4JException e) {
            e.printStackTrace();
        }
        FileUtils.deleteFile(filePath + upload);
        System.err.println(content);
        FfwyProtocol ffwyProtocol = new FfwyProtocol();
        ffwyProtocol.setProtocolId(1);
        ffwyProtocol.setProtocolMsg(content);

        return ffwyProtocolMapper.updateFfwyProtocol(ffwyProtocol);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param protocolIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProtocolByIds(Integer[] protocolIds)
    {
        return ffwyProtocolMapper.deleteFfwyProtocolByIds(protocolIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param protocolId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteFfwyProtocolById(Integer protocolId)
    {
        return ffwyProtocolMapper.deleteFfwyProtocolById(protocolId);
    }
}
