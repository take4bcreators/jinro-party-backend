package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class StatePlayerListDisplay extends GameStateBase {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    public StatePlayerListDisplay() {
        super(new GameStateSettings(
                "ST05",
                GameState.PlayerListDisplay,
                5000,
                GameState.Empty,
                GameState.RoleAssignment));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask() {
        this.mainWebSocketProcessService.gameScreenChange(this.getNexGameState());
    }

}
