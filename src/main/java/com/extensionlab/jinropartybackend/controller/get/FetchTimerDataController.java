package com.extensionlab.jinropartybackend.controller.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.enums.TimerState;
import com.extensionlab.jinropartybackend.model.api.APITimerData;
import com.extensionlab.jinropartybackend.service.CountdownTimerService;

@RestController
@CrossOrigin
public class FetchTimerDataController {

    @Autowired
    CountdownTimerService countdownTimerService;

    @GetMapping("/api/get-fetch-timer-data")
    public APITimerData get() {
        long timeCountMSec = this.countdownTimerService.getCurrentTimeCountMSec();
        TimerState timerState = this.countdownTimerService.getCurrentTimerState();
        GameState gameState = this.countdownTimerService.getCurrentTimerGameState();
        var replyData = new APITimerData(timeCountMSec, timerState, gameState);
        return replyData;
    }

}
