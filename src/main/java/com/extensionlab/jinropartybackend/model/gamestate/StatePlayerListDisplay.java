package com.extensionlab.jinropartybackend.model.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class StatePlayerListDisplay extends BaseGameState {

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
