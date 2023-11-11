package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameDataService;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class DayPhaseStartComponent extends GameStateComponent {

    @Autowired
    GameDataService gameDataService;

    public DayPhaseStartComponent() {
        super(new GameStateSettings(
                "ST07",
                GameState.DayPhaseStart,
                3000,
                GameState.RoleAssignment,
                GameState.DayPhase));
    }

    @Override
    public void runStartTask() {
        this.gameDataService.incrementCurrentTurn();
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
