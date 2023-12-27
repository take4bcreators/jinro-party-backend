package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameProgressService;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class VotingEndComponent extends GameStateComponent {

    @Autowired
    GameProgressService gameProgressService;

    public VotingEndComponent() {
        super(new GameStateSettings(
                "ST11",
                GameState.VotingEnd,
                3000,
                GameState.Voting,
                GameState.VoteResult));
    }

    @Override
    public void runStartTask() {
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameProgressService.processUnvotedPlayers();
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
