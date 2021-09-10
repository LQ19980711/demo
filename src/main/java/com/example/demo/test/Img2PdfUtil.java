package com.example.demo.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName Img2PdfUtil
 * @Description TODO
 * @Author Administrator
 * @Date 2021/4/27 15:31
 * @Version 1.0
 **/
public class Img2PdfUtil {
    private static final Logger logger = LoggerFactory.getLogger(Img2PdfUtil.class);

    /**
     * @param outPdfFilepath 生成pdf文件路径
     * @param imageFiles     需要转换的图片File类Array,按array的顺序合成图片
     */
    public static void imagesToPdf(String outPdfFilepath, File[] imageFiles) throws IOException, DocumentException {
        logger.info("进入图片合成PDF工具方法");

        File file = new File(outPdfFilepath);//第一步：创建一个document对象。

        Document document = new Document();

        document.setMargins(0, 0, 0, 0);//第二步：//创建一个PdfWriter实例，

        PdfWriter.getInstance(document, new FileOutputStream(file));//第三步：打开文档。

        document.open();//第四步：在文档中增加图片。

        int len = imageFiles.length;
        for (int i = 0; i < len; i++) {
            if (imageFiles[i].getName().toLowerCase().endsWith(".bmp") || imageFiles[i].getName().toLowerCase().endsWith(".jpg") || imageFiles[i].getName().toLowerCase().endsWith(".jpeg") || imageFiles[i].getName().toLowerCase().endsWith(".gif") || imageFiles[i].getName().toLowerCase().endsWith(".png")) {
                String temp = imageFiles[i].getAbsolutePath();

                logger.info("图片路径：" + temp);

                Image img = Image.getInstance(temp);

                img.setAlignment(Image.ALIGN_CENTER);

                img.scaleAbsolute(597, 844);//直接设定显示尺寸//根据图片大小设置页面，一定要先设置页面，再newPage()，否则无效//document.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));

                document.setPageSize(new Rectangle(597, 844));

                document.newPage();

                document.add(img);

            }

        }//第五步：关闭文档。

        document.close();

        logger.info("图片合成PDF完成");

    }

    public static void main(String[] args) {
        String outPdfPath= "C:\\Users\\Administrator\\Desktop\\图片\\Img2pdf.pdf";

        String imagesPath= "C:\\Users\\Administrator\\Desktop\\图片\\";

        String[] imgNameArray= new String[] { "1_0.png", "1_1.png", "1_2.png", "1_3.png", "1_4.png", "1_5.png","1_6.png"};
        File[] imageFile=new File[]{new File("1_0.png"),new File("1_1.png")};
        try {
            Img2PdfUtil.imagesToPdf(outPdfPath, imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}
