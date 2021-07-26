//package com.ruoyi;
//
//
//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.ooxml.POIXMLDocument;
//import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//
//import java.io.FileInputStream;
//
///**
// *  .doc .docx 文档测试类
// *
// */
//public class TestPoi {
//
//
//    public static void main(String[] args) {
//        TestPoi tp=new TestPoi();
//        //.docx和doc文件的读取
//        String content = tp.readWord("C:\\Users\\Administrator\\Desktop\\疑难杂症\\新建 Microsoft Word 2007 文档.docx");
//        System.out.println("content===="+content);
//    }
//
//    /**
//     * 读取word文件内容
//     *
//     * @param path
//     * @return buffer
//     */
//    public String readWord(String path) {
//        String buffer = "";
//        try {
//            if (path.endsWith(".doc")) {
//                FileInputStream is = new FileInputStream(path);
//                WordExtractor ex = new WordExtractor(is);
//                buffer = ex.getText();
//                is.close();
//            } else if (path.endsWith("docx")) {
//                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
//                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
//                buffer = extractor.getText();
//                opcPackage.close();
//            } else {
//                System.out.println("此文件不是word文件！");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return buffer;
//    }}