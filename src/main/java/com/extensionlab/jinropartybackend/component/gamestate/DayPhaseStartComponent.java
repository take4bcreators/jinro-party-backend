package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class DayPhaseStartComponent extends GameStateComponent {

    public DayPhaseStartComponent() {
        super(new GameStateSettings(
                "ST07",
                GameState.DayPhaseStart,
                5000,
                GameState.RoleAssignment,
                GameState.DayPhase));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
