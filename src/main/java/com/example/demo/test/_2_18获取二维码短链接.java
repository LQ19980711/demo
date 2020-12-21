package com.example.demo.test;


import java.text.ParseException;
import java.util.Date;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**获取二维码长链接**/
public class _2_18获取二维码短链接{
	
	private static final Logger logger = LoggerFactory.getLogger(_2_18获取二维码短链接.class);

	public static void main(String[] args) throws ParseException{

	PWBRequest pwbRequest = new PWBRequest();
    Header header = new Header();
    header.setRequestTime(new Date());
    header.setApplication("SendCode");
    pwbRequest.setHeader(header);
    pwbRequest.setTransactionName("QUERY_SHORT_IMG_URL_REQ");
    IdentityInfo identityInfo = new IdentityInfo();
    identityInfo.setCorpCode("TESTFX");
    identityInfo.setUserName("admin");
    pwbRequest.setIdentityInfo(identityInfo);
    OrderRequest orderRequest =new OrderRequest();
    Order order = new Order();
    order.setOrderCode("5dca0112071f4e68ba598d03d449c4a7");
    orderRequest.setOrder(order);
    pwbRequest.setOrderRequest(orderRequest);
    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
  		if(null==rsp){
  			System.out.println("请求智游宝返回空");
  		}
		if(0==rsp.getCode()){
  			logger.info("操作成功");
  			logger.info("二维码地址:{}",rsp.getImg());
  		}else{
  			logger.info("操作失败");
  			logger.info(rsp.getDescription());
  		}
  	}
	
	
}