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
        super(GameState.PlayerListDisplay);
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask() {
        this.mainWebSocketProcessService.gameScreenChange(GameState.RoleAssignment);
    }

}
