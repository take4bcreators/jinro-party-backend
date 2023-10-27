package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.APISendDeviceId;
import com.extensionlab.jinropartybackend.service.EntryPlayerInfoService;

@RestController
@CrossOrigin
public class PlayerRegistRemoveController {

    @Autowired
    EntryPlayerInfoService service;

    @PostMapping("/api/post-player-regist-remove")
    public APIReplyProcessResult post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        boolean result = false;
        try {
            service.deleteByDeviceId(deviceId);
            result = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        var replyData = new APIReplyProcessResult(result);
        return replyData;
    }

}
