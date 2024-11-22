package com.qtech.im.eqn.webSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * author :  gaozhilin
 * email  :  gaoolin@gmail.com
 * date   :  2024/11/19 09:50:34
 * desc   :
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new DeviceStatusWebSocketHandler(), "/ws/device-status")
                .setAllowedOrigins("*");
    }
}
