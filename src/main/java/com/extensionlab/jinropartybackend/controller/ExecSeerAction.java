package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.model.api.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.api.APISendSeerActionData;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;
import com.extensionlab.jinropartybackend.service.NightActionService;

@RestController
@CrossOrigin
public class ExecSeerAction {

    @Autowired
    NightActionService nightActionService;

    @Autowired
    PlayerInfoService playerInfoService;

    @PostMapping("/api/post-exec-seer-action")
    public APIReplyProcessResult post(@ModelAttribute APISendSeerActionData postData) {
        String deviceId = postData.getSeerDeviceId();
        String receiverDeviceId = postData.getReceiverDeviceId();
        PlayerInfo receiver = this.playerInfoService.getPlayerInfo(receiverDeviceId);
        var replyData = new APIReplyProcessResult(false);
        try {
            this.nightActionService.registryNightAction(
                    deviceId,
                    PlayerRole.Seer,
                    receiver.getDeviceId(),
                    receiver.getPlayerName(),
                    receiver.getPlayerIcon(),
                    receiver.getPlayerRole());
            replyData.setResult(true);
        } catch (Exception e) {
            System.err.println(e);
            replyData.setResult(false);
        }
        return replyData;
    }

}
