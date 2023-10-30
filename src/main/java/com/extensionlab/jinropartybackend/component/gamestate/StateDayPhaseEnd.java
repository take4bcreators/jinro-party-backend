package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class StateDayPhaseEnd extends GameStateBase {

    public StateDayPhaseEnd() {
        super(new GameStateSettings(
                "ST09",
                GameState.DayPhaseEnd,
                0,
                GameState.DayPhase,
                GameState.Voting));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
