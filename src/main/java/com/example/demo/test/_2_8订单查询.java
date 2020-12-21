package com.example.demo.test;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class _2_8订单查询 {

	public static void main(String[] args) throws ParseException{

	PWBRequest pwbRequest = new PWBRequest();
    Header header = new Header();
    header.setRequestTime(new Date());
    header.setApplication("SendCode");
    pwbRequest.setHeader(header);
    pwbRequest.setTransactionName("QUERY_ORDER_NEW_REQ");
    IdentityInfo identityInfo = new IdentityInfo();
    identityInfo.setCorpCode("TESTFX");
    identityInfo.setUserName("admin");
    pwbRequest.setIdentityInfo(identityInfo);
    OrderRequest orderRequest=new OrderRequest();
    Order order=new Order();
    order.setOrderCode("a917cb23b7af46f8b7d4c46846ec7930");
    orderRequest.setOrder(order);
    pwbRequest.setOrderRequest(orderRequest);
    pwbRequest.setOrderRequest(orderRequest);
    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
  		if(null==rsp){
  			System.out.println("请求智游宝返回空");
  		}
  		if(0==rsp.getCode()){
  			System.out.println("操作成功");
            System.out.println(rsp.getOrder().getAssistCheckNo());//辅助码
            System.out.println(rsp.getOrder().getOrderCode());//智游宝订单号
  		}else{
  			System.out.println("请求失败");
  		}
	
	}
	
	


}
