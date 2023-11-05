package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class VoteResultComponent extends GameStateComponent {

    public VoteResultComponent() {
        super(new GameStateSettings(
                "ST12",
                GameState.VoteResult,
                5000,
                GameState.VotingEnd,
                GameState.ExileAnnouncement));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
