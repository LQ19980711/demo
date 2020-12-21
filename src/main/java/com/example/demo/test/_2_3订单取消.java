package com.example.demo.test;


import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**订单取消**/
public class _2_3订单取消{
	private static final Logger logger = LoggerFactory.getLogger(_2_3订单取消.class);

	
	public static void main(String[] args) throws ParseException{

	PWBRequest pwbRequest = new PWBRequest();
    Header header = new Header();
    header.setRequestTime(new Date());
    header.setApplication("SendCode");
    pwbRequest.setHeader(header);
    pwbRequest.setTransactionName(TransactionNameConstant.SEND_CODE_CANCEL_NEW_REQ);//取消订单标识
    IdentityInfo identityInfo = new IdentityInfo();
    identityInfo.setCorpCode("TESTFX");
    identityInfo.setUserName("admin");
    pwbRequest.setIdentityInfo(identityInfo);
    OrderRequest orderRequest =new OrderRequest();
    Order order = new Order();
    order.setOrderCode("5d8ed0aac09542aca80bc073bfa11edc");//分销售订单号
    orderRequest.setOrder(order);
    pwbRequest.setOrderRequest(orderRequest);
    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
  		if(null==rsp){
  			System.out.println("请求智游宝返回空");
  		}
  		if(0==rsp.getCode()){
  			System.out.println("操作成功");
  			System.out.println("退票批次号:"+rsp.getRetreatBatchNo());
  		}else{
  			System.out.println("请求失败");
  		}
	}
	
	
}