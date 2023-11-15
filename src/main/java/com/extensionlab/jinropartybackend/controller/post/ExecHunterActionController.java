package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.api.APISendNightActionData;
import com.extensionlab.jinropartybackend.service.GameProgressService;

@RestController
@CrossOrigin
public class ExecHunterActionController {

    @Autowired
    GameProgressService gameProgressService;

    @PostMapping("/api/post-exec-hunter-action")
    public APIReplyProcessResult post(@ModelAttribute APISendNightActionData postData) {
        String deviceId = postData.getDeviceId();
        String receiverDeviceId = postData.getReceiverDeviceId();
        boolean result = this.gameProgressService.execHunterAction(deviceId, receiverDeviceId);
        var replyData = new APIReplyProcessResult(result);
        return replyData;
    }

}
