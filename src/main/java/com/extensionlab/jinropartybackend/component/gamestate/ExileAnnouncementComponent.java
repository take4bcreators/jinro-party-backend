package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class ExileAnnouncementComponent extends GameStateComponent {

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
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
