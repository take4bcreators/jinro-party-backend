package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameDataService;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class RoleAssignmentComponent extends GameStateComponent {

    @Autowired
    GameDataService gameDataService;

    public RoleAssignmentComponent() {
        super(new GameStateSettings(
                "ST06",
                GameState.RoleAssignment,
                0,
                GameState.PlayerListDisplay,
                GameState.DayPhaseStart));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        this.gameDataService.resetCurrentTurn();
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
