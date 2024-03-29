package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.api.APISendEntryPlayerData;
import com.extensionlab.jinropartybackend.service.EntryPlayerInfoService;

@RestController
@CrossOrigin
public class PlayerTempRegistController {

    @Autowired
    EntryPlayerInfoService service;

    @PostMapping("/api/post-player-temp-regist")
    public APIReplyProcessResult post(@ModelAttribute APISendEntryPlayerData postData) {
        String deviceId = postData.getDeviceId();
        String playerName = postData.getPlayerName();
        String playerIcon = postData.getPlayerIcon();
        boolean result = false;
        try {
            service.upsertNotEntryData(deviceId, playerName, playerIcon);
            result = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        var replyData = new APIReplyProcessResult(result);
        return replyData;
    }

}
