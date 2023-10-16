package com.extensionlab.jinropartybackend.handler;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.extensionlab.jinropartybackend.service.MainWebSocketService;

public class MainWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    MainWebSocketService mainWebSocketService;

    public MainWebSocketHandler() {
    }

    // 接続確立
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("debug: WebSocket接続確立");
        this.mainWebSocketService.registerSession(session);
    }

    // メッセージの受信
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String receiveText = message.getPayload();

        // Debug
        System.out.println("debug: WebSocketメッセージ受信");
        System.out.println("debug: メッセージ内容...");
        System.out.println(receiveText);

        switch (receiveText) {
            case "":
                break;
            default:
                break;
        }

        // 送信処理
        try {
            this.mainWebSocketService.sendMessageAll("OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 接続終了
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("debug: WebSocket接続終了");
        this.mainWebSocketService.deleteSession(session);
    }
}
