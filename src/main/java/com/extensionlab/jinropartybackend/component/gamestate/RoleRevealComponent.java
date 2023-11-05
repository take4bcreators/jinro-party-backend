package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class RoleRevealComponent extends GameStateComponent {

    public RoleRevealComponent() {
        super(new GameStateSettings(
                "ST22",
                GameState.RoleReveal,
                0,
                GameState.FinalResult,
                GameState.Empty));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
    }

}
