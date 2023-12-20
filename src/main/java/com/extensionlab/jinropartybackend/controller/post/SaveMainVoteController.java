package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.api.APISendVotePlayerData;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;
import com.extensionlab.jinropartybackend.service.VotesService;

@RestController
@CrossOrigin
public class SaveMainVoteController {

    @Autowired
    VotesService votesService;

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    @PostMapping("/api/post-save-main-vote")
    public APIReplyProcessResult post(@ModelAttribute APISendVotePlayerData postData) {
        var replyData = new APIReplyProcessResult(false);
        try {
            this.votesService.registryVotesFromAPI(postData);
            replyData.setResult(true);
        } catch (Exception e) {
            System.err.println(e);
            replyData.setResult(false);
        }
        if (replyData.isResult()) {
            this.mainWebSocketProcessService.returnIfVote();
        }
        return replyData;
    }
}
