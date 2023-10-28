package com.extensionlab.jinropartybackend.service;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.extensionlab.jinropartybackend.enums.WebSocketGroup;
import com.extensionlab.jinropartybackend.model.WebSocketSessions;
import com.extensionlab.jinropartybackend.model.WebSocketSessionsList;

@Service
public class GeneralWebSocketService {

    private WebSocketSessionsList webSocketSessionsList;

    public GeneralWebSocketService() {
        this.webSocketSessionsList = new WebSocketSessionsList(new ArrayList<>());
    }

    private void addWebSocketSessionsList(WebSocketSessions webSocketSessions) {
        this.webSocketSessionsList.getSessionsList().add(webSocketSessions);
    }

    public void generateSessionsListIfNotExists(WebSocketGroup webSocketGroup) {
        ArrayList<WebSocketSession> sessions = this.getSessions(webSocketGroup);
        if (sessions == null) {
            var webSocketSessions = new WebSocketSessions(webSocketGroup, new ArrayList<>());
            this.addWebSocketSessionsList(webSocketSessions);
        }
    }

    private ArrayList<WebSocketSession> getSessions(WebSocketGroup webSocketGroup) {
        ArrayList<WebSocketSessions> sessionsList = this.webSocketSessionsList.getSessionsList();
        ArrayList<WebSocketSession> webSocketSessions = null;
        for (WebSocketSessions sessions : sessionsList) {
            if (sessions.getSessionsGroup().equals(webSocketGroup)) {
                webSocketSessions = sessions.getSessions();
                break;
            }
        }
        return webSocketSessions;
    }

    public void registerSession(WebSocketGroup webSocketGroup, WebSocketSession session) {
        ArrayList<WebSocketSession> sessions = this.getSessions(webSocketGroup);
        if (sessions.stream()
                .noneMatch(user -> user.getId().equals(session.getId()))) {
            sessions.add(session);
            System.out.println("debug: WebSocketセッション登録: " + session.getId());
        }
    }

    public void deleteSession(WebSocketGroup webSocketGroup, WebSocketSession session) {
        ArrayList<WebSocketSession> sessions = this.getSessions(webSocketGroup);
        sessions
                .stream()
                .filter(user -> user.getId().equals(session.getId()))
                .findFirst()
                .ifPresent(user -> {
                    sessions.remove(user);
                    System.out.println("debug: WebSocketセッション削除: " + session.getId());
                });
    }

    public void sendMessage(WebSocketSession session, String message) throws IOException {
        System.out.println("debug: WebSocketメッセージ送信内容...");
        System.out.println(message);
        var textMessage = new TextMessage(message);
        session.sendMessage(textMessage);
    }

    public void sendMessageAll(WebSocketGroup webSocketGroup, String message) throws IOException {
        System.out.println("debug: WebSocketメッセージ送信内容...");
        System.out.println(message);
        var textMessage = new TextMessage(message);
        ArrayList<WebSocketSession> sessions = this.getSessions(webSocketGroup);
        for (WebSocketSession session : sessions) {
            session.sendMessage(textMessage);
            System.out.println("debug: 送信先: " + session.getId());
        }
    }

}
