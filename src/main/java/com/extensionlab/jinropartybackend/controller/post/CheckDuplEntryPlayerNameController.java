package com.extensionlab.jinropartybackend.controller.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyDuplicationResult;
import com.extensionlab.jinropartybackend.model.api.APISendEntryPlayerData;
import com.extensionlab.jinropartybackend.service.EntryPlayerInfoService;

@RestController
@CrossOrigin
public class CheckDuplEntryPlayerNameController {

    @Autowired
    EntryPlayerInfoService service;

    @PostMapping("/api/post-check-dupl-entry-player-name")
    public APIReplyDuplicationResult post(@ModelAttribute APISendEntryPlayerData postData) {
        String playerName = postData.getPlayerName();
        System.out.println(playerName);
        boolean isDuplicate = service.existsByPlayerName(playerName);
        var replyData = new APIReplyDuplicationResult(isDuplicate);
        return replyData;
    }

}
