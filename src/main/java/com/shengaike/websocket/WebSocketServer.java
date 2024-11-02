package com.shengaike.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Rev 0001 : WebSocket.
 * description  : 服务端实现，方法的封装
 * class name   : WebSocketServer
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{applicationName}")
public class WebSocketServer {
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    // 应用名称
    private String applicationName;

    //虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
    private static final CopyOnWriteArraySet<WebSocketServer> webSockets = new CopyOnWriteArraySet<>();
    // 用来存在线连接用户信息
    private static final ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<>();


    /**
     * start connecting
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "applicationName") String applicationName) {
        try {
            this.session = session;
            this.applicationName = applicationName;
            webSockets.add(this);
            sessionPool.put(applicationName, session);
            log.info("[websocket message] There are new connections, the total number is." + webSockets.size());
            log.info("[Current Client List]:"+ sessionPool.keySet());
        } catch (Exception e) {
        }
    }

    /**
     * description : What to do after a connection is broken
     * method name : onClose
     * param       : []
     * return      : void
     */
    @OnClose
    public void onClose() {
        try {
            webSockets.remove(this);
            sessionPool.remove(this.applicationName);
            log.info("[websocket message] Connection disconnected, total number of:" + webSockets.size());
            log.info("[Current Client List]："+ sessionPool.keySet());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * description : 收到客户端消息的处理方法
     * method name : onMessage
     * param       : [message]
     * return      : void
     */
    @OnMessage
    public void onMessage(String message) {
        String[] strings = message.split("_!_");
        log.info("[websocket message] Receiving client message:" + message);
        this.sendApplicationMessage(strings[1],strings[0]);
    }

    /**
     * description : 错误处理
     * method name : onError
     * param       : [session, error]
     * return      : void
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("444");
        log.error("用户错误,原因:" + error.getMessage());
        error.printStackTrace();
    }


    /**
     * description : 广播消息 给所有注册的客户端发送消息
     * method name : sendBroadcastMessage
     * param       : [message]
     * return      : void
     */
    public void sendBroadcastMessage(String message) {
        log.info("【websocket消息】广播消息:" + message);
        for (WebSocketServer webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * description : Send a message to the specified client
     * method name : sendApplicationMessage
     * param       : [applicationName 客户端的应用名称, message 要发送的消息]
     * return      : void
     */
    public void sendApplicationMessage(String applicationName, String message) {
        Session session = sessionPool.get(applicationName);
        if (session != null && session.isOpen()) {
            try {
                log.info("Sending socket messages:" + message);
                String ss = "This is the server, I got your message:" + message;
                session.getAsyncRemote().sendText(ss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * description : 给多个客户端发送消息
     * method name : sendMassApplicationMessage
     * param       : [applicationNames 注册的客户端的应用名称, message 要发送的消息]
     * return      : void
     */
    public void sendMassApplicationMessage(String[] applicationNames, String message) {
        System.out.println("777");
        for (String userId : applicationNames) {
            Session session = sessionPool.get(userId);
            if (session != null && session.isOpen()) {
                try {
                    log.info("【websocket消息】 单点消息:" + message);
                    session.getAsyncRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
