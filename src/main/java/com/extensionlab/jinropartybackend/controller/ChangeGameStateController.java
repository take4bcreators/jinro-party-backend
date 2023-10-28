package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.APISendGameState;
import com.extensionlab.jinropartybackend.service.GameDataService;
import com.extensionlab.jinropartybackend.service.MainWebSocketService;

@RestController
@CrossOrigin
public class ChangeGameStateController {

    @Autowired
    GameDataService gameDataService;

    @Autowired
    MainWebSocketService mainWebSocketService;

    @PostMapping("/api/post-change-game-state")
    public APIReplyProcessResult post(@ModelAttribute APISendGameState postData) {
        GameState gameState = postData.getGameState();
        System.out.println(gameState);
        var result = new APIReplyProcessResult(false);
        try {
            gameDataService.updateGameState(gameState);
            mainWebSocketService.gameScreenChange(gameState);
            result.setResult(true);
        } catch (Exception e) {
            System.err.println(e);
            result.setResult(false);
        }
        return result;
    }

}
