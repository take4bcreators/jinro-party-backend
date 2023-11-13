package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.api.APIReplyPlayerData;
import com.extensionlab.jinropartybackend.model.api.APISendDeviceId;
import com.extensionlab.jinropartybackend.model.entity.SeerAction;
import com.extensionlab.jinropartybackend.service.SeerActionService;

@RestController
@CrossOrigin
public class FetchSeerData {

    @Autowired
    SeerActionService service;

    @PostMapping("/api/post-fetch-seer-data")
    public APIReplyPlayerData post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        SeerAction result = this.service.findRecord(deviceId);
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
