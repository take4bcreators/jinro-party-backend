package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class FinalResultComponent extends GameStateComponent {

    public FinalResultComponent() {
        super(new GameStateSettings(
                "ST21",
                GameState.FinalResult,
                5000,
                GameState.GameEnd,
                GameState.RoleReveal));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
