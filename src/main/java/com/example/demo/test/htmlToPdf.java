package com.example.demo.test;

import com.spire.pdf.graphics.PdfMargins;
import com.spire.pdf.htmlconverter.qt.HtmlConverter;
import com.spire.pdf.htmlconverter.qt.Size;

/**
 * @ClassName htmlToPdf
 * @Description TODO
 * @Author Administrator
 * @Date 2021/5/10 18:32
 * @Version 1.0
 **/
public class htmlToPdf {

   /* public static void main(String[] args) {
        String url="http://winceshi.yunlvkeji.xyz/jsp/admin/index.jsp";
        String filePath="C:\\Users\\Administrator\\Desktop\\htmlPdf.pdf";
        HtmlConverter.convert(url,filePath,true,1000000,new Size(600f,900f),new PdfMargins(0));
    }*/

    public static void main(String[] args) {
        //定义需要转换的HTML
        String url = "https://www.baidu.com/";

        //转换后的结果文档（结果文档保存在Java项目程序文件下）
        String fileName = "HtmlToPDF.pdf";

        //解压后的插件本地地址（这里是把插件包放在了Java项目文件夹下，也可以自定义其他本地路径）
        String pluginPath = "C:\\Users\\Administrator\\IdeaProjects\\Conversion_PDF\\plugins-windows-x64";
        HtmlConverter.setPluginPath(pluginPath);

        //调用方法转换到PDF并设置PDF尺寸
        HtmlConverter.convert(url, fileName, true, 1000, new Size(700f, 800f), new PdfMargins(0));
    }



}
