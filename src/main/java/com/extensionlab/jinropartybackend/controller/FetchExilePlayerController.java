package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.api.APIReplyPlayerData;
import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;
import com.extensionlab.jinropartybackend.service.VotesService;

@RestController
@CrossOrigin
public class FetchExilePlayerController {

    @Autowired
    VotesService service;

    @GetMapping("/api/get-fetch-exile-player")
    public APIReplyPlayerData get() {
        VoteReceivers maxCountReceiver = this.service.getMaxCountReceiver();
        var apiReplyPlayerData = new APIReplyPlayerData(
                maxCountReceiver.getDeviceId(),
                maxCountReceiver.getPlayerName(),
                maxCountReceiver.getPlayerIcon(),
                PlayerRole.Empty,
                PlayerTeam.Empty,
                PlayerState.Alive);
        return apiReplyPlayerData;
    }

}
