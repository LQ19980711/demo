package com.example.demo.test;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class _2_1下单_票测试{
	private static final Logger logger = LoggerFactory.getLogger(_2_1下单_票测试.class);

	
	public static void main(String[] args) throws ParseException{
		//订单数据组装
		String orderCode =UUID.randomUUID().toString().replaceAll("-", "");
		PWBRequest pwbRequest = new PWBRequest();
	    Header header = new Header();
	    header.setRequestTime(new Date());
	    header.setApplication("SendCode");
	    pwbRequest.setHeader(header);
	    pwbRequest.setTransactionName(TransactionNameConstant.SEND_CODE_REQ);
	    IdentityInfo identityInfo = new IdentityInfo();
	    identityInfo.setCorpCode("TESTFX");
	    identityInfo.setUserName("admin");
	    pwbRequest.setIdentityInfo(identityInfo);
	    OrderRequest orderRequest =new OrderRequest();
	    Order order = new Order();
	    order.setLinkMobile("13000000000");
	    order.setCertificateNo("330106194901017554");
	    order.setLinkName("测试");
	    order.setOrderCode(orderCode);
	    order.setOrderPrice(new BigDecimal(1));
	    order.setPayMethod("vm");//备用金
	    List<TicketOrder> ticketOrders =new ArrayList<TicketOrder>();
	    TicketOrder ticketOrder = new TicketOrder();
	    ticketOrder.setGoodsCode("PST20160918013085"); 
	    ticketOrder.setGoodsName("单票");
	    DateFormat dd=new SimpleDateFormat("yyyy-MM-dd");
	    Date date;
		try {
			date = dd.parse("2017-12-14");
			ticketOrder.setOccDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Credential> credentials = new ArrayList<Credential>();
		Credential  credential = new Credential();
		credential.setName("测试");
		credential.setId("330106194901017554");
		credentials.add(credential);
		ticketOrder.setOrderCode(orderCode);
		ticketOrder.setPrice(new BigDecimal(1));
		ticketOrder.setQuantity(1);
		ticketOrder.setRemark("helloworld");
		ticketOrder.setTotalPrice(new BigDecimal(1));
		ticketOrders.add(ticketOrder);
	    order.setTicketOrders(ticketOrders);
	    orderRequest.setOrder(order);
	    pwbRequest.setOrderRequest(orderRequest);
	    
	    //请求智游宝
	    
	    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
		
	    //返回数据处理
	    if(null==rsp){
			System.out.println("请求智游宝返回空");
		}
		if(0==rsp.getCode()){
			logger.info("下单成功-->描述:{}",rsp.getDescription());
			//辅助码
			logger.info("辅助码:{}",rsp.getOrderResponse().getOrder().getAssistCheckNo());
			//智游宝订单号
			logger.info("智游宝订单号:{}",rsp.getOrderResponse().getOrder().getOrderCode());

		}else{
			logger.info("下单失败-->描述:{}",rsp.getDescription());
		}
	}
	
	
	
}
