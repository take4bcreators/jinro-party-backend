package com.extensionlab.jinropartybackend.model;

import java.util.ArrayList;
import org.springframework.web.socket.WebSocketSession;
import com.extensionlab.jinropartybackend.enums.WebSocketGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketSessions {
    private WebSocketGroup sessionsGroup;
    private ArrayList<WebSocketSession> sessions;
}
