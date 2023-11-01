package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyPlayerData;
import com.extensionlab.jinropartybackend.model.api.APISendDeviceId;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class FetchPlayerData {

    @Autowired
    PlayerInfoService service;

    @PostMapping("/api/post-fetch-player-data")
    public APIReplyPlayerData post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        System.out.println(deviceId);
        var replyData = service.getPlayerDataForAPI(deviceId);
        return replyData;
    }

}
