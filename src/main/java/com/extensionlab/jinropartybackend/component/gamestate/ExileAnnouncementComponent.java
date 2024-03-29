package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameProgressService;
import com.extensionlab.jinropartybackend.service.GameStateService;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class ExileAnnouncementComponent extends GameStateComponent {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    @Autowired
    GameProgressService gameProgressService;

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
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        this.gameProgressService.updatePlayerStateForDropOutPlayer();
        if (this.gameProgressService.checkGameEnd(this.getThisGameState())) {
            this.gameProgressService.updateWinningTeam(this.getThisGameState());
            gameStateService.execChangeStateTask(GameState.GameEnd);
            return;
        }
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
