package com.example.demo.util.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @ClassName WebSocketConfig
 * @Description TODO
 * @Author Administrator
 * @Date 2020/6/22 0022 17:33
 * @Version 1.0
 **/

@Configuration
@EnableWebSocket
public class SpringWebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(),"/websocket/socketServer")
                .addInterceptors(new SpringWebSocketHandlerInterceptor()).setAllowedOrigins("*");
        registry.addHandler(webSocketHandler(), "/sockjs/socketServer").setAllowedOrigins("*")
                .addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
    }

    @Bean
    public TextWebSocketHandler webSocketHandler(){
        return new SpringWebSocketHandler();
    }
}
