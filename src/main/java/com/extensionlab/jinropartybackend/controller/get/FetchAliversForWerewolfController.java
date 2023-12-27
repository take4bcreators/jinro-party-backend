package com.extensionlab.jinropartybackend.controller.get;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIMultiPlayerBasicData;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.service.DataTransferService;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class FetchAliversForWerewolfController {

    @Autowired
    PlayerInfoService playerInfoService;

    @Autowired
    DataTransferService dataTransferService;

    @GetMapping("/api/get-fetch-alivers-for-werewolf")
    public APIMultiPlayerBasicData get() {
        List<PlayerInfo> alivePlayerList = this.playerInfoService.getAlivePlayerListExcludeWerewolf();
        APIMultiPlayerBasicData replyData = this.dataTransferService.toAPIMultiPlayerBasicData(alivePlayerList);
        return replyData;
    }

}
