package com.extensionlab.jinropartybackend.controller.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.model.api.APIReplyProcessResult;
import com.extensionlab.jinropartybackend.service.GameProgressService;

@RestController
@CrossOrigin
public class EndGameResetController {

    @Autowired
    GameProgressService gameProgressService;

    @GetMapping("/api/get-end-game-reset")
    public APIReplyProcessResult get() {
        boolean result = false;
        try {
            this.gameProgressService.resetGame();
            result = true;
        } catch (Exception e) {
            System.err.println(e);
        }
        var replyData = new APIReplyProcessResult(result);
        return replyData;
    }

}
