package com.extensionlab.jinropartybackend.controller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIMultiPlayerBasicData;
import com.extensionlab.jinropartybackend.model.api.APISendDeviceId;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.service.DataTransferService;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class FetchOtherAlivePlayersController {

    @Autowired
    PlayerInfoService playerInfoService;

    @Autowired
    DataTransferService dataTransferService;

    @PostMapping("/api/post-fetch-other-alive-players")
    public APIMultiPlayerBasicData post(@ModelAttribute APISendDeviceId postData) {
        String deviceId = postData.getDeviceId();
        List<PlayerInfo> alivePlayerList = this.playerInfoService.getAlivePlayerListExcludeBy(deviceId);
        var replyData = this.dataTransferService.toAPIMultiPlayerBasicData(alivePlayerList);
        return replyData;
    }

}
