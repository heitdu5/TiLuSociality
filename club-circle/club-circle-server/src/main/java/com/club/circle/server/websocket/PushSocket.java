package com.club.circle.server.websocket;

import com.club.circle.server.config.websocket.WebSocketServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tellsea
 * @date 2024−08−16
 */
@Slf4j
@ServerEndpoint(value = "/chicken/socket", configurator = WebSocketServerConfig.class)
@Component
public class PushSocket {

    /**
     * 记录当前在线连接数
     */
    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 存放所有当前在线的客户端
     */
    private static final Map<String,PushSocket> clients = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话
     */
    private Session session;


    /**
     * erp唯一标识
     */
    private String erp = "";

    public Session getSession(){
        return session;
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig conf) throws IOException {
        try {
            //获取用户信息
            Map<String, Object> userProperties = conf.getUserProperties();
            String erp = (String) userProperties.get("erp");
            this.session = session;
            this.erp = erp;
            if(clients.containsKey(erp)){
                clients.get(this.erp).session.close();
                clients.remove(this.erp);
                onlineCount.decrementAndGet();
            }
            clients.put(this.erp,this);
            onlineCount.incrementAndGet();
            log.info("有新连接加入：{}，当前在线人数为：{}", erp, onlineCount.get());
            sendMessage("连接成功", this.session);
        } catch (IOException e) {
            log.error("建立链接错误{}", e.getMessage(), e);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        try {
            if (clients.containsKey(erp)) {
                clients.get(erp).session.close();
                clients.remove(erp);
                onlineCount.decrementAndGet();
            }
            log.info("有一连接关闭：{}，当前在线人数为：{}", this.erp, onlineCount.get());
        } catch (Exception e) {
            log.error("连接关闭错误，错误原因{}", e.getMessage(), e);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端[{}]的消息:{}", this.erp, message);
        //心跳机制
        if (message.equals("ping")) {
            this.sendMessage("pong", session);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Socket:{},发生错误,错误原因{}", erp, error.getMessage(), error);
        try {
            session.close();
        } catch (Exception e) {
            log.error("onError.Exception{}", e.getMessage(), e);
        }
    }

    /**
     * 指定发送消息
     * @param message
     */
    public void sendMessage(String message,Session session){
        log.info("服务端给客户端[{}]发送消息:{}", this.erp, message);
        try {
            session.getBasicRemote().sendText(message);
        }catch (IOException e){
            log.error("{}发送消息发生异常，异常原因{}", this.erp, message);
        }
    }

    public void sendMessage(String message){
        for (Map.Entry<String, PushSocket> sessionEntry : clients.entrySet()) {
            String key = sessionEntry.getKey();
            PushSocket socket = sessionEntry.getValue();
            Session session = socket.session;
            log.info("服务端给客户端[{}]发送消息{}", erp, message);
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("{}发送消息发生异常，异常原因{}", this.erp, message);
            }
        }
    }

    public PushSocket getPushSocket(String userName) {
        return clients.get(userName);
    }

}



























