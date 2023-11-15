package com.extensionlab.jinropartybackend.controller.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.service.EntryPlayerInfoService;

@RestController
@CrossOrigin
public class ExecPingController {

    @Autowired
    EntryPlayerInfoService service;

    @GetMapping("/api/get-exec-ping")
    public APIReplyProcessResult get() {
        var replyData = new APIReplyProcessResult(true);
        return replyData;
    }

}
