package com.example.demo.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.util.zybUtil.ZybPackUtil;
import com.example.demo.util.zybdto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class _2_13退票情况查询 {
	private static final Logger logger = LoggerFactory.getLogger(_2_13退票情况查询.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PWBRequest pwbRequest = new PWBRequest();
	    Header header = new Header();
	    header.setRequestTime(new Date());
	    header.setApplication("SendCode");
	    pwbRequest.setHeader(header);
	    pwbRequest.setTransactionName(TransactionNameConstant.QUERY_RETREAT_STATUS_REQ);
	    IdentityInfo identityInfo = new IdentityInfo();
	    identityInfo.setCorpCode("TESTFX");
	    identityInfo.setUserName("admin");
	    pwbRequest.setIdentityInfo(identityInfo);
	    OrderRequest orderRequest =new OrderRequest();
	    Order order = new Order();
	    order.setRetreatBatchNo("dc7b421f50c44fd9a37eb88c151b2134");//退票批次号
	    orderRequest.setOrder(order);
	    pwbRequest.setOrderRequest(orderRequest);
	    PWBResponse rsp = ZybPackUtil.requestZyb(pwbRequest, "TESTFX", "http://zyb-zff.sendinfo.com.cn/boss/service/code.htm");
	  		if(null==rsp){
	  			logger.info("请求智游宝返回空");
	  		}
	  		if(0==rsp.getCode()){
	  			logger.info("操作成功===》{}",rsp.getDescription());
	  		}else{
	  			logger.info("操作失败===》{}",rsp.getDescription());
	  	}
	
	
	}

}
