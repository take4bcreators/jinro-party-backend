package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameDataService;
import com.extensionlab.jinropartybackend.service.GameProgressService;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class VoteResultComponent extends GameStateComponent {

    @Autowired
    GameProgressService gameProgressService;

    @Autowired
    GameDataService gameDataService;

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
        if (!this.gameProgressService.isNeedRunoffVote()) {
            // 決選投票が不要な場合は次のシーンへ
            this.gameProgressService.updateDropOutTable();
            gameStateService.execChangeStateTask(this.getNexGameState());
            return;
        }
        int voteCount = this.gameDataService.getTurnVoteCount();
        int voteCountLimit = 2;
        if (voteCount < voteCountLimit) {
            // 1回は決選投票へ進む
            this.gameProgressService.prepareReVoteTables();
            gameStateService.execChangeStateTask(GameState.Voting);
            return;
        }
        // 2回目以降 はランダムに選ぶ決定する
        this.gameProgressService.randomlyDecideVote();
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

}
