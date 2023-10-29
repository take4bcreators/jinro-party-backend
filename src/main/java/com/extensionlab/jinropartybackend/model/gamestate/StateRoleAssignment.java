package com.extensionlab.jinropartybackend.model.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class StateRoleAssignment extends BaseGameState {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    public StateRoleAssignment() {
        super(GameState.RoleAssignment);
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask() {
        this.mainWebSocketProcessService.gameScreenChange(GameState.DayPhaseStart);
    }

}
