package com.example.demo.controller;

import com.example.demo.test.PdfUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * @description: 用于处理Pdf相关的请求
 */
@Controller
@RequestMapping("pdf")
public class PdfController {

    @PostMapping("image/to")
    public void imageToPdf(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception{
        PdfUtils.imageToPdf(file,response);
    }


    /**
     * 生成pdf文件
     * @author zengwei
     * @email 1014483974@qq.com
     * @param response
     * @throws Exception
     */
    @PostMapping("/downloadPdfFile")
    public void downloadPdfFile(HttpServletResponse response) throws Exception {

        String path = "template/"; //ftl路径
        String templateName = "template";//需要选用的模板名称
        String outFilePath = "template.pdf";

        List<Map<String, Object>> labelList = new ArrayList<>();
        Map<String, Object> label = new HashMap<>();
        label.put("imgUrl", "https://profile.csdnimg.cn/0/6/D/3_zengweib208");
        label.put("name", "NO1 CSDN:田间稻草人");
        labelList.add(label);

        Map<String, Object> label2 = new HashMap<>();
        label2.put("imgUrl", "https://profile.csdnimg.cn/0/6/D/3_zengweib208");
        label2.put("name", "NO2 CSDN:田间稻草人");
        labelList.add(label2);

        // 能日赚30的手赚试玩平台，亲测有效
        // https://mp.weixin.qq.com/s?__biz=MzIyODgxNjkyOQ==&mid=100000040&idx=1&sn=47c0245f9dbe70f3ad6b2540209af2c2&chksm=684d60665f3ae97095ba07d8c6804bac4f55dbc6e7100fbb233945f65364df88682d41332eb7&xtrack=1&scene=0&subscene=10000&clicktime=1616647421&enterid=1616647421&ascene=7&devicetype=android-29&version=28000165&nettype=WIFI&abtest_cookie=AAACAA%3D%3D&lang=zh_CN&exportkey=AdxLWFyJlgtM6uFZCpgWtBk%3D&pass_ticket=G8rEeGdox4FPpICRkrKy6ho2QZozCzXi%2Be7gV5bXnQaXoZK2pw4S8Wf2j%2Bt3D8mi&wx_header=1

        Map<String, Object> map = new HashMap<>();
        map.put("labelList", labelList);

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/PDF;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + outFilePath);
//        generateToServletOutputStream(path, templateName + ".ftl", path + "/images/", map, response);

    }

    /**
     * 生成pdf文件
     * @author zengwei
     * @email 1014483974@qq.com
     * @param ftlPath
     * @param ftlName
     * @param imageDiskPath
     * @param data
     * @param response
     * @return
     * @throws Exception
     */
    /*public static OutputStream generateToServletOutputStream(String ftlPath, String ftlName, String imageDiskPath,
                                                             Object data, HttpServletResponse response) throws Exception {
        // 获取Freemarker配置
        Configuration config = new Configuration(Configuration.VERSION_2.3.20);
        config.setDirectoryForTemplateLoading(new File(ftlPath));
        config.setEncoding(Locale.CHINA, "utf-8");

        // 得到模板内容
        Template tpl = config.getTemplate(ftlName);
        StringWriter writer = new StringWriter();
        tpl.process(data, writer);
        writer.flush();
        String html = writer.toString();
        OutputStream out = response.getOutputStream();

        // 添加字体，以支持中文
        ITextRenderer render = new ITextRenderer();
        render.getFontResolver().addFont("simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        render.setDocumentFromString(html);

        // html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径
        if (imageDiskPath != null && !"".equals(imageDiskPath)) {
            render.getSharedContext().setBaseURL("file:/" + imageDiskPath);
        }


        render.layout();
        render.createPDF(out);
        render.finishPDF();
        return out;

    }
*/


}
