package com.example.demo.thread;

/**
 * @ClassName StartThread
 * @Description TODO
 * @Author Administrator
 * @Date 2021/2/22 17:37
 * @Version 1.0
 **/
public class StartThread extends Thread {
    //继承Thread，重写run方法，执行线程必须调用start，不一定立即执行
    @Override
    public void run() {
        for(int i=0;i<20;i++){
            System.out.println("一边听歌");
        }
    }

    public static void main(String[] args) {
        StartThread st=new StartThread();
        st.start();
        for(int i=0;i<20;i++){
            System.out.println("一边coding");
        }
    }
}
