package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.APIReplyCheckPlayerAlive;
import com.extensionlab.jinropartybackend.model.APISendDeviceId;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class CheckPlayerAliveController {

    @Autowired
    PlayerInfoService service;

    @PostMapping("/api/post-check-player-alive")
    public APIReplyCheckPlayerAlive post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        System.out.println(deviceId);
        boolean isAlive = service.checkPlayerAlive(deviceId);
        var replyData = new APIReplyCheckPlayerAlive(isAlive);
        return replyData;
    }

}
