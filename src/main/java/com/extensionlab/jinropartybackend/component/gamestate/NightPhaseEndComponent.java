package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class NightPhaseEndComponent extends GameStateComponent {

    public NightPhaseEndComponent() {
        super(new GameStateSettings(
                "ST16",
                GameState.NightPhaseEnd,
                3000,
                GameState.NightPhase,
                GameState.MorningPhaseStart));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
