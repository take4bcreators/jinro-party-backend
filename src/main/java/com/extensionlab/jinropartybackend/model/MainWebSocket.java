package com.extensionlab.jinropartybackend.model;

import java.util.ArrayList;
import org.springframework.web.socket.WebSocketSession;
import lombok.Data;

@Data
public class MainWebSocket {
    private ArrayList<WebSocketSession> sessions;
}
