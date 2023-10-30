package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class StateRoleAssignment extends GameStateBase {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    public StateRoleAssignment() {
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
    public void runEndTask() {
        this.mainWebSocketProcessService.gameScreenChange(this.getNexGameState());
    }

}
