package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyAllPlayerInfo;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class FetchAllPlayerInfo {

    @Autowired
    PlayerInfoService service;

    @GetMapping("/api/get-fetch-all-player-info")
    public APIReplyAllPlayerInfo get() {
        var allPlayerInfo = this.service.getAllPlayerInfoToListForAPI();
        var replyData = new APIReplyAllPlayerInfo(allPlayerInfo);
        return replyData;
    }

}
