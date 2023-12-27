package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyExistsDeviceId;
import com.extensionlab.jinropartybackend.model.api.APISendDeviceId;
import com.extensionlab.jinropartybackend.service.WerewolfActionExecuterDataService;

@RestController
@CrossOrigin
public class CheckWerewolfExecuterController {

    @Autowired
    WerewolfActionExecuterDataService werewolfActionExecuterDataService;

    @PostMapping("/api/post-check-werewolf-executer")
    public APIReplyExistsDeviceId post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        boolean deviceIdExists = this.werewolfActionExecuterDataService.existsData(deviceId);
        var replyData = new APIReplyExistsDeviceId(deviceIdExists);
        return replyData;
    }

}
