package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyAllPlayerData;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class FetchAllPlayerData {

    @Autowired
    PlayerInfoService service;

    @GetMapping("/api/get-fetch-all-player-data")
    public APIReplyAllPlayerData get() {
        var allPlayerData = this.service.getAllPlayerDataToListForAPI();
        var replyData = new APIReplyAllPlayerData(allPlayerData);
        return replyData;
    }

}
