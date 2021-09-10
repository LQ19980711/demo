package com.example.demo.test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @ClassName PdfUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2021/4/26 15:48
 * @Version 1.0
 **/
public class PdfUtils {

    /**
     * 图片转换PDF的公共接口
     *
     * @param file   SpringMVC获取的图片文件
     * @param response HttpServletResponse
     * @throws IOException    IO异常
     * @throws DocumentException PDF文档异常
     */
    public static void imageToPdf(MultipartFile file, HttpServletResponse response) throws IOException, DocumentException {
        File pdfFile = generatePdfFile(file);
        downloadPdfFile(pdfFile, response);
    }

    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\17322\\Documents\\File\\2020-02\\whatsapp.txt");
        File file = new File(path.toUri());
        FileInputStream fileInputStream = null;
        MultipartFile multipartFile =null;
        try {
            fileInputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(), fileInputStream);
            File pdfFile = generatePdfFile(multipartFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将图片转换为PDF文件
     *
     * @param file SpringMVC获取的图片文件
     * @return PDF文件
     * @throws IOException    IO异常
     * @throws DocumentException PDF文档异常
     */
    private static File generatePdfFile(MultipartFile file) throws IOException, DocumentException {
        String fileName = file.getOriginalFilename();
        String pdfFileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".pdf";
        Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter.getInstance(doc, new FileOutputStream(pdfFileName));
        doc.open();
        doc.newPage();
        Image image = Image.getInstance(file.getBytes());
        float height = image.getHeight();
        float width = image.getWidth();
        int percent = getPercent(height, width);
        image.setAlignment(Image.MIDDLE);
        image.scalePercent(percent);
        doc.add(image);
        doc.close();
        File pdfFile = new File(pdfFileName);
        return pdfFile;
    }

    /**
     *
     * 用于下载PDF文件
     *
     * @param pdfFile PDF文件
     * @param response HttpServletResponse
     * @throws IOException IO异常
     */
    private static void downloadPdfFile(File pdfFile, HttpServletResponse response) throws IOException {
        FileInputStream fis = new FileInputStream(pdfFile);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();

        response.reset();
        response.setHeader("Content-Type", "application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(pdfFile.getName(), "UTF-8"));
        OutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
    }


    /**
     * 等比压缩，获取压缩百分比
     *
     * @param height 图片的高度
     * @param weight 图片的宽度
     * @return 压缩百分比
     */
    private static int getPercent(float height, float weight) {
        float percent = 0.0F;
        if (height > weight) {
            percent = PageSize.A4.getHeight() / height * 100;
        } else {
            percent = PageSize.A4.getWidth() / weight * 100;
        }
        return Math.round(percent);
    }


}
