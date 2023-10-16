package com.extensionlab.jinropartybackend.service;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.extensionlab.jinropartybackend.model.MainWebSocket;

@Service
public class MainWebSocketService {

    private MainWebSocket mainWebSocket;

    public MainWebSocketService() {
        this.mainWebSocket = new MainWebSocket();
        this.mainWebSocket.setSessions(new ArrayList<>());
    }

    public void registerSession(WebSocketSession session) {
        ArrayList<WebSocketSession> sessions = this.mainWebSocket.getSessions();
        if (sessions.stream()
                .noneMatch(user -> user.getId().equals(session.getId()))) {
            sessions.add(session);
            System.out.println("debug: WebSocketセッション登録: " + session.getId());
        }
    }

    public void deleteSession(WebSocketSession session) {
        ArrayList<WebSocketSession> sessions = this.mainWebSocket.getSessions();
        sessions
                .stream()
                .filter(user -> user.getId().equals(session.getId()))
                .findFirst()
                .ifPresent(user -> sessions.remove(user));
    }

    public void sendMessage(WebSocketSession session, String message) throws IOException {
        var textMessage = new TextMessage(message);
        session.sendMessage(textMessage);
    }

    public void sendMessageAll(String message) throws IOException {
        System.out.println("debug: WebSocketメッセージ送信");
        System.out.println("debug: 送信内容...");
        System.out.println(message);
        var textMessage = new TextMessage(message);
        ArrayList<WebSocketSession> sessions = this.mainWebSocket.getSessions();
        for (WebSocketSession session : sessions) {
            session.sendMessage(textMessage);
            System.out.println("debug: 送信先: " + session.getId());
        }
    }
}
