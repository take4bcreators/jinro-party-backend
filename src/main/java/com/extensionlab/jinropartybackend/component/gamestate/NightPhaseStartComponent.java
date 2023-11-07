package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class NightPhaseStartComponent extends GameStateComponent {

    public NightPhaseStartComponent() {
        super(new GameStateSettings(
                "ST14",
                GameState.NightPhaseStart,
                3000,
                GameState.ExileAnnouncement,
                GameState.NightPhase));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}