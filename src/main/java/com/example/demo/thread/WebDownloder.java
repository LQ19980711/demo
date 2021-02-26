package com.example.demo.thread;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @ClassName WebDownloder
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/22 17:46
 * @Version 1.0
 **/
public class WebDownloder {

    public void download(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("图片下载失败");
        }
    }
}
