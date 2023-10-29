package com.extensionlab.jinropartybackend.model.gameprogressstate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class PlayerListDisplay extends GameProgressState {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    public PlayerListDisplay() {
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
