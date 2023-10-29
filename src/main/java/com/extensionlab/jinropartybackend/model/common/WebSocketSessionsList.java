package com.extensionlab.jinropartybackend.model.common;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebSocketSessionsList {
    private ArrayList<WebSocketSessions> sessionsList;
}
