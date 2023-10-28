package com.extensionlab.jinropartybackend.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.extensionlab.jinropartybackend.enums.WebSocketGroup;
import com.extensionlab.jinropartybackend.service.GeneralWebSocketService;
import com.extensionlab.jinropartybackend.service.MainWebSocketService;

public class MainWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    GeneralWebSocketService generalWebSocketService;

    @Autowired
    MainWebSocketService mainWebSocketService;

    public MainWebSocketHandler() {
    }

    // 接続確立
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("debug: WebSocket接続確立: " + session.getId());
        this.generalWebSocketService.generateSessionsListIfNotExists(WebSocketGroup.Main);
        this.generalWebSocketService.registerSession(WebSocketGroup.Main, session);
    }

    // メッセージの受信
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String receiveText = message.getPayload();
        System.out.println("debug: WebSocketメッセージ受信:" + session.getId() + " ／内容...");
        System.out.println(receiveText);
        this.mainWebSocketService.routeReceivedData(receiveText);
    }

    // 接続終了
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("debug: WebSocket接続終了: " + session.getId());
        this.generalWebSocketService.deleteSession(WebSocketGroup.Main, session);
    }
}
