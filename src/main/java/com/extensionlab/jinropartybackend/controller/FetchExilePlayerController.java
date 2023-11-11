package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.api.APIReplyPlayerData;
import com.extensionlab.jinropartybackend.model.entity.DropoutPlayerData;
import com.extensionlab.jinropartybackend.service.DropoutPlayerDataService;
import com.extensionlab.jinropartybackend.service.VotesService;

@RestController
@CrossOrigin
public class FetchExilePlayerController {

    @Autowired
    VotesService service;

    @Autowired
    DropoutPlayerDataService dropoutPlayerDataService;

    @GetMapping("/api/get-fetch-exile-player")
    public APIReplyPlayerData get() {
        DropoutPlayerData dropOutPlayer = this.dropoutPlayerDataService.getData();
        var apiReplyPlayerData = new APIReplyPlayerData(
                dropOutPlayer.getDeviceId(),
                dropOutPlayer.getPlayerName(),
                dropOutPlayer.getPlayerIcon(),
                PlayerRole.Empty,
                PlayerTeam.Empty,
                PlayerState.Alive);
        return apiReplyPlayerData;
    }

}
