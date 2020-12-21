package com.example.demo.test;

import java.text.ParseException;
import java.util.Date;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _2_11获取订单检票信息 {
	private static final Logger logger = LoggerFactory.getLogger(_2_11获取订单检票信息.class);

	public static void main(String[] args) throws ParseException{

	PWBRequest pwbRequest = new PWBRequest();
    Header header = new Header();
    header.setRequestTime(new Date());
    header.setApplication("SendCode");
    pwbRequest.setHeader(header);
    pwbRequest.setTransactionName("QUERY_SUB_ORDER_CHECK_RECORD_REQ");
    IdentityInfo identityInfo = new IdentityInfo();
    identityInfo.setCorpCode("TESTFX");
    identityInfo.setUserName("admin");
    pwbRequest.setIdentityInfo(identityInfo);
    OrderRequest orderRequest=new OrderRequest();
    Order order=new Order();
    order.setOrderCode("5dca0112071f4e68ba598d03d449c4a7");
    orderRequest.setOrder(order);
    pwbRequest.setOrderRequest(orderRequest);
    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
  		if(null==rsp){
  			System.out.println("请求智游宝返回空");
  		}
  		if(0==rsp.getCode()){
  			for (SubOrder subOrder : rsp.getSubOrders()) {
  				logger.info("总票数量{}",subOrder.getNeedCheckNum());
  				logger.info("已检数量{}",subOrder.getAlreadyCheckNum());
  				logger.info("退票数量{}",subOrder.getReturnNum());
  				logger.info("检票状态{}",subOrder.getCheckStatus());//un_check 未检票;checking 检票中 ;checked 已完结;
  				logger.info("分销商订单号{}",subOrder.getOrderCode());
			}
  		}else{
  			System.out.println("请求失败");
  		}
	}
	
	


}
