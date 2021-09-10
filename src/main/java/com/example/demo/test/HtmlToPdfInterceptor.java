package com.example.demo.test;

import java.io.*;

/**
 * @ClassName HtmlToPdfInterceptor
 * @Description TODO
 * @Author Administrator
 * @Date 2021/5/20 15:16
 * @Version 1.0
 **/
public class HtmlToPdfInterceptor extends Thread{

    private InputStream is;

    public HtmlToPdfInterceptor(InputStream is){
        this.is = is;
    }

    public void run(){
        try{
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line.toString()); //输出内容
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
