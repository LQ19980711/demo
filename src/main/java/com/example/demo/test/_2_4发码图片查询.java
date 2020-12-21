package com.example.demo.test;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.Finishings;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
/**获取二维码长链接**/
public class _2_4发码图片查询{
	private static final Logger logger = LoggerFactory.getLogger(_2_4发码图片查询.class);

	
	public static void main(String[] args) throws ParseException{

	PWBRequest pwbRequest = new PWBRequest();
    Header header = new Header();
    header.setRequestTime(new Date());
    header.setApplication("SendCode");
    pwbRequest.setHeader(header);
    pwbRequest.setTransactionName("SEND_CODE_IMG_REQ");
    IdentityInfo identityInfo = new IdentityInfo();
    identityInfo.setCorpCode("TESTFX");
    identityInfo.setUserName("admin");
    pwbRequest.setIdentityInfo(identityInfo);
    OrderRequest orderRequest =new OrderRequest();
    Order order = new Order();
    order.setOrderCode("4f1c0c336751421580c751f61a07a707");
    orderRequest.setOrder(order);
    pwbRequest.setOrderRequest(orderRequest);
    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
  		if(null==rsp){
  			System.out.println("请求智游宝返回空");
  		}
  		if(0==rsp.getCode()){
  			System.out.println("操作成功");
  			logger.info(rsp.getImg());
  			base64ToFile("4f1c0c336751421580c751f61a07a707", rsp.getImg());
  			logger.info("");
  			
  		}else{
  			System.out.println("请求失败");
  		}
}
 	//base64字符串转化成图片  
	public static void base64ToFile(String name,String base64){
		try {
			BASE64Decoder decoder = new BASE64Decoder();
	        byte[] bytes1 = decoder.decodeBuffer(base64);
	        OutputStream out=new FileOutputStream("E://"+name+".jpg");
	        out.write(bytes1);
	        out.flush();
	        out.close();
		} catch (Exception e) {
			logger.info("二维码处理异常{}",e);
		}
	}
}