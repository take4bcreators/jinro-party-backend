package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.extensionlab.jinropartybackend.model.APIReplyGameState;
import com.extensionlab.jinropartybackend.service.GameDataService;

@RestController
@CrossOrigin
public class GetGameStateController {

    @Autowired
    GameDataService service;

    @GetMapping("/api/get-get-game-state")
    public APIReplyGameState get() {
        var replyData = new APIReplyGameState(service.getGameState());
        return replyData;
    }

}
