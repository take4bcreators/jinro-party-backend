package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.GameMode;
import com.extensionlab.jinropartybackend.model.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.APISendNewGame;
import com.extensionlab.jinropartybackend.service.GameDataService;

@RestController
@CrossOrigin
public class SaveNewGameController {

    @Autowired
    GameDataService service;

    @PostMapping("/api/post-save-new-game")
    public APIReplyProcessResult post(@ModelAttribute APISendNewGame postData) {
        GameMode gameMode = postData.getGameMode();
        System.out.println(gameMode);
        var replyData = new APIReplyProcessResult(false);
        try {
            service.upsertGameMode(gameMode);
            replyData.setResult(true);
        } catch (Exception e) {
            System.err.println(e);
            replyData.setResult(false);
        }
        return replyData;
    }
}
