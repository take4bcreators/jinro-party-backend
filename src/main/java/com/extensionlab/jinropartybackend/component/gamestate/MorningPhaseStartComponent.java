package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class MorningPhaseStartComponent extends GameStateComponent {

    public MorningPhaseStartComponent() {
        super(new GameStateSettings(
                "ST17",
                GameState.MorningPhaseStart,
                3000,
                GameState.NightPhaseEnd,
                GameState.NightActionResult));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
