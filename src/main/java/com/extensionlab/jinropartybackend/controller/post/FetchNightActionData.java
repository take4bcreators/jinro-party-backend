package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.api.APIReplyPlayerData;
import com.extensionlab.jinropartybackend.model.api.APISendDeviceId;
import com.extensionlab.jinropartybackend.model.entity.NightAction;
import com.extensionlab.jinropartybackend.service.NightActionService;

@RestController
@CrossOrigin
public class FetchNightActionData {

    @Autowired
    NightActionService service;

    @PostMapping("/api/post-fetch-night-action-data")
    public APIReplyPlayerData post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        NightAction result = this.service.findRecord(deviceId);
        var replyData = new APIReplyPlayerData(
                result.getReceiverDeviceId(),
                result.getReceiverPlayerName(),
                result.getReceiverPlayerIcon(),
                result.getReceiverPlayerRole(),
                PlayerTeam.Empty,
                PlayerState.Empty);
        return replyData;
    }

}
