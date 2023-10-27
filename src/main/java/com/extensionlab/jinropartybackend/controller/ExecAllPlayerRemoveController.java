package com.extensionlab.jinropartybackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@RestController
@CrossOrigin
public class ExecAllPlayerRemoveController {

    @Autowired
    PlayerInfoService service;

    @GetMapping("/api/get-exec-all-player-remove")
    public APIReplyProcessResult get() {
        boolean result = false;
        try {
            service.deleteAll();
            result = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        var replyData = new APIReplyProcessResult(result);
        return replyData;
    }

}
