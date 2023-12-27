package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameProgressService;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class PlayerListDisplayComponent extends GameStateComponent {

    @Autowired
    GameProgressService gameProgressService;

    public PlayerListDisplayComponent() {
        super(new GameStateSettings(
                "ST05",
                GameState.PlayerListDisplay,
                5000,
                GameState.Empty,
                GameState.RoleAssignment));
    }

    @Override
    public void runStartTask() {
        gameProgressService.assignPlayerRoleAndTeam();
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
