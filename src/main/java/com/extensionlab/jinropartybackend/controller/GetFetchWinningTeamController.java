package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.api.APIWinningTeam;
import com.extensionlab.jinropartybackend.service.GameDataService;

@RestController
@CrossOrigin
public class GetFetchWinningTeamController {

    @Autowired
    GameDataService gameDataService;

    @GetMapping("/api/get-fetch-winning-team")
    public APIWinningTeam get() {
        PlayerTeam winningTeam = this.gameDataService.getWinningTeam();
        var replyData = new APIWinningTeam(winningTeam);
        return replyData;
    }

}
