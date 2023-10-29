package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.APISendDeviceId;
import com.extensionlab.jinropartybackend.service.GameDataService;
import com.extensionlab.jinropartybackend.service.GameProgressService;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class SelfRoleCheckedController {

    @Autowired
    PlayerInfoService playerInfoService;

    @Autowired
    GameDataService gameDataService;

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    @Autowired
    GameProgressService gameProgressService;

    @PostMapping("/api/post-self-role-checked")
    public APIReplyProcessResult post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        var replyData = new APIReplyProcessResult(false);
        try {
            this.playerInfoService.updateSelfRoleCheck(deviceId);
        } catch (Exception e) {
            System.err.println(e);
            replyData.setResult(false);
            return replyData;
        }
        replyData.setResult(true);
        if (this.playerInfoService.allPlayerChecked()) {
            var gameState = GameState.DayPhaseStart;
            try {
                gameDataService.updateGameState(gameState);
                mainWebSocketProcessService.gameScreenChange(gameState);
                gameProgressService.startStateTask(gameState);
                replyData.setResult(true);
            } catch (Exception e) {
                System.err.println(e);
                replyData.setResult(false);
            }
        }
        return replyData;
    }

}
