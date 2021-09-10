package com.example.demo.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName JpgToPdf
 * @Description TODO
 * @Author Administrator
 * @Date 2021/4/26 15:43
 * @Version 1.0
 **/
public class JpgToPdf {

    public static void main(String[] args) {
        toPdf("C:\\Users\\Administrator\\Desktop\\图片", "C:\\Users\\Administrator\\Desktop\\图片\\hebing.pdf");
    }

    public static void toPdf(String imageFolderPath, String pdfPath) {
        try {
            // 图片文件夹地址
            // String imageFolderPath = "G:\\image\\";
            // 图片地址
            String imagePath = null;
            // PDF文件保存地址
            // String pdfPath = "G:\\hebing.pdf";
            // 输入流
//            FileOutputStream fos = new FileOutputStream(pdfPath);
            // 创建文档
            Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
            //doc.open();
            // 写入PDF文档
            File fileTemp = new File(pdfPath);//第一步：创建一个document对象。
            PdfWriter.getInstance(doc,  new FileOutputStream(fileTemp));
            doc.open();
            // 读取图片流
            BufferedImage img = null;
            // 实例化图片
            Image image = null;
            // 获取图片文件夹对象
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();

            // 循环获取图片文件夹内的图片
            for (File file1 : files) {
                if (file1.getName().endsWith(".png") || file1.getName().endsWith(".jpg") || file1.getName().endsWith(".gif")
                        || file1.getName().endsWith(".jpeg") || file1.getName().endsWith(".tif")) {
                    // System.out.println(file1.getName());
                    imagePath = imageFolderPath+"\\"+file1.getName();
                    // 读取图片流
                    img = ImageIO.read(new File(imagePath));
                    doc.setPageSize(new Rectangle(img.getWidth(), img
                            .getHeight()));
                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(img.getWidth(), img
                            .getHeight()));
                    // 实例化图片
                    image = Image.getInstance(imagePath);
                    // 添加图片到文档
                    doc.newPage();
                    doc.add(image);
                }
            }
            // 关闭文档
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
