package com.example.demo.thread;

/**
 * @ClassName TDownloder
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/22 17:55
 * @Version 1.0
 **/
public class TDownloder extends Thread {

    @Override
    public void run() {
        WebDownloder wb=new WebDownloder();
        wb.download(url,name);
    }

    private String url;
    private String name;

    public TDownloder(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public static void main(String[] args) {
        TDownloder td=new TDownloder("https://img-pre.ivsky.com/img/tupian/pre/202007/31/aiqianniu-003.jpg","hua.jpg");
        td.start();
    }
}
