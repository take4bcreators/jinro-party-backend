package com.extensionlab.jinropartybackend.controller.get;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyAllVotePlayerData;
import com.extensionlab.jinropartybackend.model.entity.Votes;
import com.extensionlab.jinropartybackend.service.DataTransferService;
import com.extensionlab.jinropartybackend.service.VotesService;

@RestController
@CrossOrigin
public class FetchVoteResultController {

    @Autowired
    VotesService votesService;

    @Autowired
    DataTransferService dataTransferService;

    @GetMapping("/api/get-fetch-vote-result")
    public APIReplyAllVotePlayerData get() {
        List<Votes> allVoteList = this.votesService.getAllVoteList();
        var replyData = this.dataTransferService.toAPIReplyAllVotePlayerData(allVoteList);
        return replyData;
    }

}
