package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyExistsDeviceId;
import com.extensionlab.jinropartybackend.model.api.APISendDeviceId;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class ExistsDeviceIdController {

    @Autowired
    PlayerInfoService service;

    @PostMapping("/api/post-exists-device-id")
    public APIReplyExistsDeviceId post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        System.out.println(deviceId);
        boolean deviceIdExists = service.existsDeviceId(deviceId);
        var replyData = new APIReplyExistsDeviceId(deviceIdExists);
        return replyData;
    }

}
