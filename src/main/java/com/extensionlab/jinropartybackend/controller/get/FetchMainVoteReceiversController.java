package com.extensionlab.jinropartybackend.controller.get;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.PlayerRole;
import com.extensionlab.jinropartybackend.enums.PlayerState;
import com.extensionlab.jinropartybackend.enums.PlayerTeam;
import com.extensionlab.jinropartybackend.model.api.APIReplyAllPlayerData;
import com.extensionlab.jinropartybackend.model.api.APIReplyPlayerData;
import com.extensionlab.jinropartybackend.model.entity.VoteReceivers;
import com.extensionlab.jinropartybackend.service.VoteReceiversService;

@RestController
@CrossOrigin
public class FetchMainVoteReceiversController {

    @Autowired
    VoteReceiversService voteReceiversService;

    @GetMapping("/api/get-fetch-main-vote-receivers")
    public APIReplyAllPlayerData get() {
        List<VoteReceivers> voteReceiversList = this.voteReceiversService.getAllVoteReceiversList();
        List<APIReplyPlayerData> apiReplyPlayerDataList = new ArrayList<>();
        for (VoteReceivers voteReceivers : voteReceiversList) {
            apiReplyPlayerDataList.add(new APIReplyPlayerData(
                    voteReceivers.getDeviceId(),
                    voteReceivers.getPlayerName(),
                    voteReceivers.getPlayerIcon(),
                    PlayerRole.Empty,
                    PlayerTeam.Empty,
                    PlayerState.Alive));
        }
        var replyData = new APIReplyAllPlayerData(apiReplyPlayerDataList);
        return replyData;
    }

}
