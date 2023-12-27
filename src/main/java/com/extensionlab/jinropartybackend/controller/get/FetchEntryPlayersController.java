package com.extensionlab.jinropartybackend.controller.get;

// import java.util.ArrayList;
// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIMultiPlayerBasicData;
// import com.extensionlab.jinropartybackend.model.api.APIReplyAllVotePlayerData;
// import com.extensionlab.jinropartybackend.model.api.APIReplyVotePlayerData;
// import com.extensionlab.jinropartybackend.model.entity.EntryPlayerInfo;
// import com.extensionlab.jinropartybackend.model.entity.Votes;
import com.extensionlab.jinropartybackend.service.DataTransferService;
import com.extensionlab.jinropartybackend.service.EntryPlayerInfoService;
// import com.extensionlab.jinropartybackend.service.VotesService;

@RestController
@CrossOrigin
public class FetchEntryPlayersController {

    @Autowired
    EntryPlayerInfoService entryPlayerInfoService;

    @Autowired
    DataTransferService dataTransferService;

    @GetMapping("/api/get-fetch-entry-players")
    public APIMultiPlayerBasicData get() {
        var data = this.entryPlayerInfoService.getAllEntryDataToList();
        var replyData = this.dataTransferService.toAPIMultiPlayerBasicData(data);
        return replyData;
    }

}
