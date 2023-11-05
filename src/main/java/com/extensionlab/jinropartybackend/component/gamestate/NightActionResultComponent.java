package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class NightActionResultComponent extends GameStateComponent {

    public NightActionResultComponent() {
        super(new GameStateSettings(
                "ST18",
                GameState.NightActionResult,
                5000,
                GameState.MorningPhaseStart,
                GameState.DayPhaseStart));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
