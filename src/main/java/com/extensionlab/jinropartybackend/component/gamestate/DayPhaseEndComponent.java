package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class DayPhaseEndComponent extends GameStateComponent {

    public DayPhaseEndComponent() {
        super(new GameStateSettings(
                "ST09",
                GameState.DayPhaseEnd,
                3000,
                GameState.DayPhase,
                GameState.Voting));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        // @note for DEBUG
        // gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
