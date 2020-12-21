package com.example.demo.test;


import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

public class _2_6部分退票{
	private static final Logger logger = LoggerFactory.getLogger(_2_6部分退票.class);
	
	public static void main(String[] args) throws ParseException{
	PWBRequest pwbRequest = new PWBRequest();
    Header header = new Header();
    header.setRequestTime(new Date());
    header.setApplication("SendCode");
    pwbRequest.setHeader(header);
    pwbRequest.setTransactionName("RETURN_TICKET_NUM_NEW_REQ");
    IdentityInfo identityInfo = new IdentityInfo();
    identityInfo.setCorpCode("TESTFX");
    identityInfo.setUserName("admin");
    pwbRequest.setIdentityInfo(identityInfo);
    OrderRequest orderRequest =new OrderRequest();
    ReturnTicket returnTicket = new ReturnTicket();
    returnTicket.setOrderCode("5485eb756b1f439f9717098b199a5c73");
    returnTicket.setReturnNum(1);
    returnTicket.setThirdReturnCode(System.currentTimeMillis()+"");
    orderRequest.setReturnTicket(returnTicket);
    pwbRequest.setOrderRequest(orderRequest);
    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
  		if(null==rsp){
  			System.out.println("请求智游宝返回空");
  		}
  		if(0==rsp.getCode()){
  			System.out.println("操作成功");
  			logger.info("退票批次号：{}",rsp.getRetreatBatchNo());
  		}else{
  			System.out.println("请求失败");
  			System.out.println(rsp.getDescription());
  		}
	}
}