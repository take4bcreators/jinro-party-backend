package com.extensionlab.jinropartybackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyAllVotePlayerData;
import com.extensionlab.jinropartybackend.model.api.APIReplyVotePlayerData;
import com.extensionlab.jinropartybackend.model.entity.Votes;
import com.extensionlab.jinropartybackend.service.VotesService;

@RestController
@CrossOrigin
public class FetchVoteResultController {

    @Autowired
    VotesService service;

    @GetMapping("/api/get-fetch-vote-result")
    public APIReplyAllVotePlayerData get() {
        List<Votes> allVoteList = this.service.getAllVoteList();
        List<APIReplyVotePlayerData> apiReplyVotePlayerDatas = new ArrayList<>();
        for (Votes votes : allVoteList) {
            apiReplyVotePlayerDatas.add(new APIReplyVotePlayerData(
                    votes.getVoterDeviceId(),
                    votes.getVoterPlayerName(),
                    votes.getVoterPlayerIcon(),
                    votes.getReceiverDeviceId(),
                    votes.getReceiverPlayerName(),
                    votes.getReceiverPlayerIcon()));
        }
        var replyData = new APIReplyAllVotePlayerData(apiReplyVotePlayerDatas);
        return replyData;
    }

}
