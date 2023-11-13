package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyExistsDeviceId;
import com.extensionlab.jinropartybackend.model.api.APISendDeviceId;
import com.extensionlab.jinropartybackend.service.NightActionService;

@RestController
@CrossOrigin
public class ExistsNightActionData {

    @Autowired
    NightActionService service;

    @PostMapping("/api/post-exists-night-action-data")
    public APIReplyExistsDeviceId post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        boolean result = this.service.existsRecord(deviceId);
        var replyData = new APIReplyExistsDeviceId(result);
        return replyData;
    }

}
