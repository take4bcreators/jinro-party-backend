package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class StateDayPhaseStart extends GameStateBase {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    public StateDayPhaseStart() {
        super(new GameStateSettings(
                "ST07",
                GameState.DayPhaseStart,
                3000,
                GameState.RoleAssignment,
                GameState.DayPhase));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask() {
        this.mainWebSocketProcessService.gameScreenChange(this.getNexGameState());
    }

}
