package com.example.demo.test;

import com.aspose.words.*;
import com.aspose.words.Shape;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * @ClassName wordtopdf
 * @Description TODO
 * @Author Administrator
 * @Date 2021/5/10 10:26
 * @Version 1.0
 **/
public class wordtopdf {

    public static void main(String[] args) throws Exception {
        String docPath = "C:\\Users\\Administrator\\Desktop\\【Y】测试线路120……_20210508_104507 - 副本.doc";
        String pdfPath = "C:\\Users\\Administrator\\Desktop\\test【Y】测试线路120……_20210508_104507 - 副本.pdf";
        doc2pdf(docPath, pdfPath);

       /*String aa="C:\\Users\\Administrator\\Desktop\\图片";
        ArrayList<String> inputImgPaths=new ArrayList<>();
        inputImgPaths.add(aa+"\\1_0.png");
        inputImgPaths.add(aa+"\\1_1.png");
        inputImgPaths.add(aa+"\\1_3.png");
        inputImgPaths.add(aa+"\\1_4.png");
        inputImgPaths.add(aa+"\\1_5.png");
        inputImgPaths.add(aa+"\\1_6.png");

        convertImageToPdf(inputImgPaths,"C:\\Users\\Administrator\\Desktop\\图片\\testImg.pdf");*/
    }

    /**
     * word和txt文件转换图片
     * @param inputStream
     * @param pageNum
     * @return
     * @throws Exception
     */
   /* private static List<BufferedImage> wordToImg(InputStream inputStream, int pageNum) throws Exception {
        if (!getLicense()) {
            return null;
        }
        try {
            Date start = new Date();
            Document doc = new Document(inputStream);
            ImageSaveOptions options = new ImageSaveOptions(SaveFormat.PNG);
            options.setPrettyFormat(true);
            options.setUseAntiAliasing(true);
            options.setUseHighQualityRendering(true);
            int pageCount = doc.getPageCount();
            //生成前pageCount张，这可以限制输出长图时的页数
           *//*if (pageCount > pageNum) {
               pageCount = pageNum;
           }*//*
            List<BufferedImage> imageList = new ArrayList<BufferedImage>();
            for (int i = 0; i < pageCount; i++) {
                OutputStream output = new ByteArrayOutputStream();
                options.setPageIndex(i);

                doc.save(output, options);
                ImageInputStream imageInputStream = javax.imageio.ImageIO.createImageInputStream(parse(output));
                imageList.add(javax.imageio.ImageIO.read(imageInputStream));
            }
            List<BufferedImage> imageList2 = new ArrayList<BufferedImage>();
            //这个重新生成新的图片是因为直接输出的图片底色为红色
            for(int j=0; j<imageList.size(); j++){
                // 生成新图片
                BufferedImage destImage = imageList.get(j);
                int w1 = destImage.getWidth();
                int h1 = destImage.getHeight();
                destImage = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = (Graphics2D) destImage.getGraphics();
                g2.setBackground(Color.LIGHT_GRAY);
                g2.clearRect(0, 0, w1, h1);
                g2.setPaint(Color.RED);
                // 从图片中读取RGB
                int[] ImageArrayOne = new int[w1 * h1];
                ImageArrayOne = imageList.get(j).getRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 逐行扫描图像中各个像素的RGB到数组中
                destImage.setRGB(0, 0, w1, h1, ImageArrayOne, 0, w1); // 设置上半部分或左半部分的RGB
                imageList2.add(destImage);
            }
            Date end = new Date();
            long l=end.getTime()-start.getTime();
            long hour= l / (1000 * 60 * 60);
            long min=  (l-hour*(1000 * 60 * 60 ))/(1000* 60);
            long s= (l-hour*(1000 * 60 * 60 )-min*1000*60)/(1000);
            long ss= (l-hour*(1000 * 60 * 60 )-min*1000*60 -s*1000)/(1000/60);
            System.out.println("word转图片时间:"+min+"分"+s+"秒" + ss + "毫秒");//hour+"小时"+
            return imageList2;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

*/
    /**
     * 获取license * * @return
     */
    private static boolean getLicense() {
        boolean result = false;
        try {
            // 凭证
            String licenseStr =
                    "<License>\n"
                            + " <Data>\n"
                            + " <Products>\n"
                            + " <Product>Aspose.Total for Java</Product>\n"
                            + " <Product>Aspose.Words for Java</Product>\n"
                            + " </Products>\n"
                            + " <EditionType>Enterprise</EditionType>\n"
                            + " <SubscriptionExpiry>20991231</SubscriptionExpiry>\n"
                            + " <LicenseExpiry>20991231</LicenseExpiry>\n"
                            + " <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n"
                            + " </Data>\n"
                            + " <Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature>\n"
                            + "</License>";
            InputStream license = new ByteArrayInputStream(licenseStr.getBytes("UTF-8"));
            License asposeLic = new License();
            asposeLic.setLicense(license);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void doc2pdf(String inPath, String outPath) {
        FileOutputStream os = null;
        if (!getLicense()) { // 验证License 若不验证则转化出的pdf文档会有水印产生
            System.out.println("oppppppppppppppp");
            return;
        }
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

    public static void convertImageToPdf(ArrayList<String> inputImgPaths, String outputFileName) throws Exception {
        // 验证License
        if (!getLicense()) {
            return;
        }
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        try {
            for (int i = 0; i < inputImgPaths.size(); i++) {
                if (i != 0)
                    builder.insertBreak(BreakType.SECTION_BREAK_NEW_PAGE);
                File file = new File(inputImgPaths.get(i));// 本地图片
                BufferedImage image = (BufferedImage) ImageIO.read(file);
                double maxPageHeight = 1584;
                double maxPageWidth = 1584;
                double currentImageHeight = ConvertUtil.pixelToPoint(image.getHeight());
                double currentImageWidth = ConvertUtil.pixelToPoint(image.getWidth());
                if (currentImageWidth >= maxPageWidth || currentImageHeight >= maxPageHeight) {
                    double[] size = CalculateImageSize(image, maxPageHeight, maxPageWidth, currentImageHeight,
                            currentImageWidth);
                    currentImageWidth = size[0];
                    currentImageHeight = size[1];
                }
                PageSetup ps = builder.getPageSetup();
                ps.setPageWidth(currentImageWidth);
                ps.setPageHeight(currentImageHeight);
                Shape shape = builder.insertImage(
                        image,
                        RelativeHorizontalPosition.PAGE,
                        0,
                        RelativeVerticalPosition.PAGE,
                        0,
                        ps.getPageWidth(),
                        ps.getPageHeight(),
                        WrapType.NONE);
            }
        } finally {
        }
        // Save the document to PDF.
        doc.save(outputFileName);
    }

    // 等比计算图片尺寸
    public static double[] CalculateImageSize(BufferedImage img, double containerHeight, double containerWidth,
                                              double targetHeight, double targetWidth) throws Exception {
        // Calculate width and height
        targetHeight = containerHeight;
        targetWidth = containerWidth;
        // Get size of an image
        double imgHeight = ConvertUtil.pixelToPoint(img.getHeight());
        double imgWidth = ConvertUtil.pixelToPoint(img.getWidth());
        if (imgHeight < targetHeight && imgWidth < targetWidth) {
            targetHeight = imgHeight;
            targetWidth = imgWidth;
        } else {
            // 计算文档中图像的大小
            double ratioWidth = imgWidth / targetWidth;
            double ratioHeight = imgHeight / targetHeight;
            if (ratioWidth > ratioHeight) targetHeight = (targetHeight * (ratioHeight / ratioWidth));
            else
                targetWidth = (targetWidth * (ratioWidth / ratioHeight));
        }
        double[] size = new double[2];
        size[0] = targetWidth; // width
        size[1] = targetHeight; // height
        return (size);
    }
  /*  public static boolean getLicense() {
        boolean result = false;
        try {
            System.out.println(wordtopdf.class.getClassLoader());
            InputStream is = wordtopdf.class.getClassLoader().getResourceAsStream("\\license.xml");
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
    }*/
}




