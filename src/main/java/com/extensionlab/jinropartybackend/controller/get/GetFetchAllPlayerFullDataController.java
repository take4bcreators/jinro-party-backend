package com.extensionlab.jinropartybackend.controller.get;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIMultiPlayerFullData;
import com.extensionlab.jinropartybackend.model.api.APIPlayerFullData;
import com.extensionlab.jinropartybackend.model.entity.PlayerInfo;
import com.extensionlab.jinropartybackend.service.DataTransferService;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class GetFetchAllPlayerFullDataController {

    @Autowired
    PlayerInfoService playerInfoService;

    @Autowired
    DataTransferService dataTransferService;

    @GetMapping("/api/get-fetch-all-player-full-data")
    public APIMultiPlayerFullData get() {
        List<PlayerInfo> players = this.playerInfoService.getAllPlayerData();
        List<APIPlayerFullData> fullDataList = this.dataTransferService.toAPIPlayerFullDataList(players);
        var replyData = new APIMultiPlayerFullData(fullDataList);
        return replyData;
    }

}
