package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class NightPhaseComponent extends GameStateComponent {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    public NightPhaseComponent() {
        super(new GameStateSettings(
                "ST15",
                GameState.NightPhase,
                60000,
                GameState.NightPhaseStart,
                GameState.NightPhaseEnd));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
