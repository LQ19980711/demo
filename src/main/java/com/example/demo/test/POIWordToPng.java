package com.example.demo.test;

/**
 * @ClassName POIWordToPng
 * @Description TODO
 * @Author Administrator
 * @Date 2021/5/10 17:09
 * @Version 1.0
 **/


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class POIWordToPng {
    /**
     * 使用jacob进行Word文档格式互转(例:doc2docx、docx2doc)
     *
     * @author Harley Hong
     * @created 2017 /08/09 16:09:32
     */
    /**
     * doc格式
     */
    private static final int DOC_FMT = 0;
    /**
     * docx格式
     */
    private static final int DOCX_FMT = 12;

    static String picp = null;
    static String docn = "/" + new Date().getTime() + ".doc";

    public static String getSystemFileCharset() {
        Properties pro = System.getProperties();
        return pro.getProperty("file.encoding");
    }

    public static void main(String[] args) throws IOException {
        String sourcePath = "C:\\Users\\Administrator\\Desktop\\word.doc";
        String pdfPath = "C:/Users/Administrator/Desktop";
        String picturesPath = "D:\\wordCreateImage";
        try {
//            convertDocFmt(sourcePath, picturesPath+docn, DOC_FMT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        convert(new File(sourcePath), pdfPath);

    }

    /**
     * 将doc文档转换成pdf文档
     *
     * @param docFile  需要转换的word文档
     * @param filepath 转换之后html的存放路径
     * @return 转换之后的html文件
     */
    public static File convert(File docFile, String filepath) throws IOException {
        // 创建保存html的文件
        String allpath = filepath + "/" + new Date().getTime() + ".pdf";
        File htmlFile = new File(allpath);
//        picp=allpath.substring(54);
        // 启动Openoffice
        String OpenOffice_HOME = "C:\\Program Files (x86)\\OpenOffice 4";
        if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
            OpenOffice_HOME += "\\";
        }
        String command = OpenOffice_HOME
                + "program\\soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
        Process pro = Runtime.getRuntime().exec(command);
        // 创建Openoffice连接
        OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
        connection.connect();

        // 创建转换器
// 		DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        // 转换文档
        converter.convert(docFile, htmlFile);
        // 关闭openoffice连接
        connection.disconnect();
        // 关闭OpenOffice服务的进程
        pro.destroy();
        return htmlFile;
    }

    /**
     * 根据docx类型转换doc文件
     *
     * @param srcPath  doc path 源文件
     * @param descPath the docx path 目标文件
     * @param fmt      fmt 所转格式
     * @return the file
     * @throws Exception the exception
     */
    public static File convertDocFmt(String srcPath, String descPath, int fmt) throws Exception {
        // 实例化ComThread线程与ActiveXComponent
        ComThread.InitSTA();
        ActiveXComponent app = new ActiveXComponent("Word.Application");
        try {
// 文档隐藏时进行应用操作
            app.setProperty("Visible", new Variant(false));
// 实例化模板Document对象
            Dispatch document = app.getProperty("Documents").toDispatch();
// 打开Document进行另存为操作
            Dispatch doc = Dispatch.invoke(document, "Open", Dispatch.Method,
                    new Object[]{srcPath, new Variant(true), new Variant(true)}, new int[1]).toDispatch();
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[]{descPath, new Variant(fmt)}, new int[1]);
            Dispatch.call(doc, "Close", new Variant(false));
            return new File(descPath);
        } catch (Exception e) {
            throw e;
        } finally {
// 释放线程与ActiveXComponent
            app.invoke("Quit", new Variant[]{});
            ComThread.Release();
        }
    }

    /**
     * 合并任数量的图片成一张图片
     *
     * @param isHorizontal true代表水平合并，fasle代表垂直合并
     * @param imgs         欲合并的图片数组
     * @return
     * @throws IOException
     */
    public static BufferedImage mergeImage(boolean isHorizontal, BufferedImage... imgs) throws IOException {
        // 生成新图片
        BufferedImage destImage = null;

        // 计算新图片的长和高
        int allw = 0, allh = 0, allwMax = 0, allhMax = 0;
        for (BufferedImage img : imgs) {
            allw += img.getWidth();
            allh += img.getHeight();
            if (img.getWidth() > allwMax) {
                allwMax = img.getWidth();
            }
            if (img.getHeight() > allhMax) {
                allhMax = img.getHeight();
            }
        }
        // 创建新图片
        if (isHorizontal) {
            destImage = new BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB);
        } else {
            destImage = new BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB);
        }

        // 合并所有子图片到新图片
        int wx = 0, wy = 0;
        for (int i = 0; i < imgs.length; i++) {
            BufferedImage img = imgs[i];
            int w1 = img.getWidth();
            int h1 = img.getHeight();
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[w1 * h1];
            ImageArrayOne = img.getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
            if (isHorizontal) { // 水平方向合并
                destImage.setRGB(wx, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            } else { // 垂直方向合并
                destImage.setRGB(0, wy, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
            }
            wx += w1;
            wy += h1;
        }
        return destImage;
    }
}


