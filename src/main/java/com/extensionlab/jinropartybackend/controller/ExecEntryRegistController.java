package com.extensionlab.jinropartybackend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.model.EntryPlayerInfo;
// import com.extensionlab.jinropartybackend.model.PlayerInfo;
import com.extensionlab.jinropartybackend.service.EntryPlayerInfoService;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class ExecEntryRegistController {

    @Autowired
    EntryPlayerInfoService entryPlayerInfoService;

    @Autowired
    PlayerInfoService playerInfoService;

    @GetMapping("/api/get-exec-entry-regist")
    public APIReplyProcessResult get() {
        var replyData = new APIReplyProcessResult(false);

        ArrayList<EntryPlayerInfo> allEntryData = entryPlayerInfoService.getAllEntryDataToList();

        // ArrayList<PlayerInfo> allEntryData =
        // entryPlayerInfoService.getAllEntryDataToPlayerInfoList();
        if (allEntryData.size() == 0) {
            replyData.setResult(false);
            return replyData;
        }
        try {
            playerInfoService.deleteAll();
            playerInfoService.registryPlayerFromEntryList(allEntryData);
            // playerInfoService.registryPlayerInfoList(allEntryData);
            replyData.setResult(true);
        } catch (Exception e) {
            System.err.println(e);
            replyData.setResult(false);
            return replyData;
        }
        return replyData;
    }

}
