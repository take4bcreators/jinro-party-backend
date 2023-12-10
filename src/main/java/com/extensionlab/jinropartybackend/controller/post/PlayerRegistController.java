package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.api.APISendEntryPlayerData;
import com.extensionlab.jinropartybackend.service.GameProgressService;

@RestController
@CrossOrigin
public class PlayerRegistController {

    @Autowired
    GameProgressService gameProgressService;

    @PostMapping("/api/post-player-regist")
    public APIReplyProcessResult post(@ModelAttribute APISendEntryPlayerData postData) {
        String deviceId = postData.getDeviceId();
        String playerName = postData.getPlayerName();
        String playerIcon = postData.getPlayerIcon();
        boolean result = this.gameProgressService.registEntryPlayer(deviceId, playerName, playerIcon);
        var replyData = new APIReplyProcessResult(result);
        return replyData;
    }

}
