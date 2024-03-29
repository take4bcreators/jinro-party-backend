package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameDataService;
import com.extensionlab.jinropartybackend.service.GameProgressService;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class NightPhaseEndComponent extends GameStateComponent {

    @Autowired
    GameDataService gameDataService;

    @Autowired
    GameProgressService gameProgressService;

    public NightPhaseEndComponent() {
        super(new GameStateSettings(
                "ST16",
                GameState.NightPhaseEnd,
                3000,
                GameState.NightPhase,
                GameState.MorningPhaseStart));
    }

    @Override
    public void runStartTask() {
        // 夜アクション後処理
        this.gameProgressService.execAfterNightActionTask();
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
        this.gameDataService.resetTurnVoteCount();
    }

}
