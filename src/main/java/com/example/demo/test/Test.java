package com.example.demo.test;
import cn.hutool.core.util.IdcardUtil;
import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

//import com.spire.doc.Document;
//import com.spire.doc.FileFormat;

import java.io.*;

/**
 * @ClassName Test
 * @Description TODO
 * @Author Administrator
 * @Date 2021/5/7 18:07
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) throws Exception {

        /*String docPath = "C:\\Users\\Administrator\\Desktop\\测试0416_20210517_174622.doc";
        String newDocPath = "C:\\Users\\Administrator\\Desktop\\new-测试0416_20210517_174622.doc";
        String pdfPath = "C:\\Users\\Administrator\\Desktop\\PDF-复制内容.pdf";
*/
//        copy01(docPath,newDocPath);

        //加载Word测试文档  word转pdf
        //com.spire.doc.Document
       /* Document doc = new Document();
        //保存为PDF格式的文件
        doc.loadFromFile(docPath);
        doc.saveToFile(pdfPath, FileFormat.PDF);
        doc.close();*/

//        doc2pdf(docPath,pdfPath);

//        doc2doc(docPath,newDocPath);
//        Office4Pdf.Office2Pdf(docPath,pdfPath);

        //文件路径
        /*String filePath = System.getProperty("user.dir")+"/src/main/resources/";
        Map dataMap=new HashMap();
        dataMap.put("userName","张三");
        dataMap.put("currDate","2021-05-12");
        dataMap.put("content"," 哈士奇叼着沙包跑回来放到了扔的人脚下。有一个朋友觉得很有意思，便把他的足球踢了出去，哈士奇又把足球捡了回来，哇!太好玩了，可它的主人叫它回家了，我恋恋不舍地说：“再见，朋友。”");
        Map<String , Object> map = WordUtil.createWord(dataMap, "freemarker.ftl", filePath,"作文.doc");
*/



    }

    /**
     * 复制文件
     * @param docPath
     * @param newDocPath
     * @throws IOException
     */
    public static void  copy(String docPath,String newDocPath) throws IOException {
        File file = new File(docPath);
        File outFile=new File(newDocPath);
        if (file.exists()) {
            FileInputStream is = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(outFile);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            is.close();
            fos.close();
        }
    }

    public static void copy01(String sourceFile,String targetFile){
        //	创建对象
        FileReader fr=null;
        FileWriter fw=null;
        try {
            fr=new FileReader(sourceFile);
            fw=new FileWriter(targetFile);
            //		循环读和循环写
            int len=0;
            while((len=fr.read())!=-1)
            {
                fw.write((char)len);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            if(fr!=null)
            {
                try {
                    fr.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(fw!=null)
            {
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 去除水印
     * @return
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void doc2pdf(String inPath, String outPath) {

        if (!getLicense()) {
            return;
        }
        FileOutputStream os = null;
        try {
            File file = new File(outPath); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(inPath); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void doc2doc(String inPath, String outPath) {

        if (!getLicense()) {
            return;
        }
        InputStream is = null;
        try {
            is = new FileInputStream(inPath);
            Document doc = new Document(is);
            doc.save(outPath);//转pdf、docx都可以
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
