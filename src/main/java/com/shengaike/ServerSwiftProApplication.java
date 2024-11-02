package com.shengaike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@MapperScan("com.shengaike.mapper")
// Rev 0001. WebSocket.
@EnableWebSocket
public class ServerSwiftProApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSwiftProApplication.class, args);
    }

}
