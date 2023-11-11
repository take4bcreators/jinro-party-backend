package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameDataService;
import com.extensionlab.jinropartybackend.service.GameStateService;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;

@Component
public class VotingComponent extends GameStateComponent {

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    @Autowired
    GameDataService gameDataService;

    public VotingComponent() {
        super(new GameStateSettings(
                "ST10",
                GameState.Voting,
                15000,
                GameState.DayPhaseEnd,
                GameState.VotingEnd));
    }

    @Override
    public void runStartTask() {
        this.mainWebSocketProcessService.countdownTimerStart(this.getCountdownTime());
        this.gameDataService.incrementTurnVoteCount();
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
