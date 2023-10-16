package com.extensionlab.jinropartybackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import com.extensionlab.jinropartybackend.handler.MainWebSocketHandler;

@Configuration
@EnableWebSocket
public class MainWebSocketConfig implements WebSocketConfigurer {

    private String WEB_SOCKET_END_POINT = "/api/ws";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(mainWebSocketHandler(), this.WEB_SOCKET_END_POINT)
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Bean
    WebSocketHandler mainWebSocketHandler() {
        return new MainWebSocketHandler();
    }
}
