package com.example.demo.service.jms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 消费者如下
 */
@Component
public class Consumer {

   private final static Logger logger = LoggerFactory
            .getLogger(Consumer.class);

    @JmsListener(destination = "queue1", containerFactory = "jmsQueueListener")
    @Transactional(propagation= Propagation.REQUIRED)
   // @SendTo("hehe")//返回到到另外一个队列进行处理
    public void receiveQueue(final Message message, Session session)
            throws JMSException {
        try {
            // 如果是文本消息
            boolean falg =true;
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                System.out.println(tm.getText());
                System.out.println("xiaoxi");
                if(falg){
                    falg=false;
                }

            }
            message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
        } catch (Exception e) {
            session.recover();// 此不可省略 重发信息使用
        }
       // session.commit();
    }
}
