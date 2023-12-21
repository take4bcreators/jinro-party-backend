package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.api.APISendNightActionData;
import com.extensionlab.jinropartybackend.service.GameProgressService;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@RestController
@CrossOrigin
public class ExecSeerActionController {

    @Autowired
    GameProgressService gameProgressService;

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    @PostMapping("/api/post-exec-seer-action")
    public APIReplyProcessResult post(@ModelAttribute APISendNightActionData postData) {
        String deviceId = postData.getDeviceId();
        String receiverDeviceId = postData.getReceiverDeviceId();
        boolean result = this.gameProgressService.execSeerAction(deviceId, receiverDeviceId);
        var replyData = new APIReplyProcessResult(result);
        if (replyData.isResult()) {
            this.mainWebSocketProcessService.returnIfNightActionUpdate();
        }
        return replyData;
    }

}
