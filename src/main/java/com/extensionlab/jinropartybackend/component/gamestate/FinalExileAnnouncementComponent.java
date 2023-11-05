package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class FinalExileAnnouncementComponent extends GameStateComponent {

    public FinalExileAnnouncementComponent() {
        super(new GameStateSettings(
                "ST19",
                GameState.FinalExileAnnouncement,
                3000,
                GameState.Empty,
                GameState.GameEnd));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
