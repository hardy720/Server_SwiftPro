package com.shengaike.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * date created : Created in 2024/3/18 16:57
 * description  : WebSocketConfig 主要解决使用了@ServerEndpoint注解的websocket endpoint不被springboot扫描到的问题
 * class name   : WebSocketConfig
 */
// Rev 0001. WebSocket.
@Configuration
public class WebSocketConfig {
    /**
     * 注入ServerEndpointExporter，
     * 这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
