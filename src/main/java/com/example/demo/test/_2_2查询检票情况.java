package com.example.demo.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;

public class _2_2查询检票情况 {
	private static final Logger logger = LoggerFactory.getLogger(_2_2查询检票情况.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PWBRequest pwbRequest = new PWBRequest();
	    Header header = new Header();
	    header.setRequestTime(new Date());
	    header.setApplication("SendCode");
	    pwbRequest.setHeader(header);
	    pwbRequest.setTransactionName(TransactionNameConstant.CHECK_STATUS_QUERY_REQ);
	    IdentityInfo identityInfo = new IdentityInfo();
	    identityInfo.setCorpCode("TESTFX");
	    identityInfo.setUserName("testfx");
	    pwbRequest.setIdentityInfo(identityInfo);
	    OrderRequest orderRequest =new OrderRequest();
	    Order order = new Order();
	    order.setOrderCode("5dca0112071f4e68ba598d03d449c4a7"); //第三方主订单号
	    orderRequest.setOrder(order);
	    pwbRequest.setOrderRequest(orderRequest);
	    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
	  		if(null==rsp){
	  			System.out.println("请求智游宝返回空");
	  		}
	  		if(0==rsp.getCode()){
	  			System.out.println("操作成功");
	  			for (SubOrder subOrder : rsp.getSubOrders()) {
	  				logger.info("需检票数量:{}",subOrder.getNeedCheckNum());
	  				logger.info("已检票数量:{}",subOrder.getAlreadyCheckNum());
	  				logger.info("退票数量:{}",subOrder.getReturnNum());
	  				logger.info("订单类型:{}",subOrder.getOrderType());
	  				logger.info("检票状态:{}",subOrder.getCheckStatus());
	  				logger.info("分销商订单号:{}",subOrder.getOrderCode());
	  				logger.info("最后一次检票时间:{}",subOrder.getLastCheckTime());

				}
	  		}else{
	  			System.out.println("请求失败");
	  		}
}

}
