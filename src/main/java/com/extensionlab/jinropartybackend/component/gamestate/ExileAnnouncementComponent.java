package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameProgressUtilService;
import com.extensionlab.jinropartybackend.service.GameStateService;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class ExileAnnouncementComponent extends GameStateComponent {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    @Autowired
    GameProgressUtilService gameProgressUtilService;

    public ExileAnnouncementComponent() {
        super(new GameStateSettings(
                "ST13",
                GameState.ExileAnnouncement,
                10000,
                GameState.VoteResult,
                GameState.NightPhaseStart));
    }

    @Override
    public void runStartTask() {
        this.mainWebSocketProcessService.countdownTimerStart(this.getCountdownTime());
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        this.gameProgressUtilService.updatePlayerStateForDropOutPlayer();
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
