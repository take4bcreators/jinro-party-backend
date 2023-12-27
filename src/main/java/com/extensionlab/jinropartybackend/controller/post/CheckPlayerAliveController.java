package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyCheckPlayerAlive;
import com.extensionlab.jinropartybackend.model.api.APISendDeviceId;
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
        boolean aliveStatus = service.checkPlayerAlive(deviceId);
        var replyData = new APIReplyCheckPlayerAlive(aliveStatus);
        return replyData;
    }

}
