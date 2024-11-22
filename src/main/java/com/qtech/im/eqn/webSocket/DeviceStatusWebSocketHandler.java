package com.qtech.im.eqn.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/11/19 09:53:35
 * desc   :
 */

@Component
public class DeviceStatusWebSocketHandler extends TextWebSocketHandler {
    private static final Logger logger = LoggerFactory.getLogger(DeviceStatusWebSocketHandler.class);

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        System.out.println("New WebSocket connection established");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.println("WebSocket connection closed");
    }

    public void sendDeviceStatus(String deviceStatusJson) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(deviceStatusJson));
            } catch (IOException e) {
                logger.error("Error sending device status message", e);
            }
        }
    }
}
