package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameProgressUtilService;
import com.extensionlab.jinropartybackend.service.GameStateService;

@Component
public class VoteResultComponent extends GameStateComponent {

    @Autowired
    GameProgressUtilService gameProgressUtilService;

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
        if (!this.gameProgressUtilService.isNeedRunoffVote()) {
            // 決選投票が不要な場合は次のシーンへ
            this.gameProgressUtilService.updateDropOutTable();
            gameStateService.execChangeStateTask(this.getNexGameState());
            return;
        }
        // @remind ここに 2回目 はランダムに選ぶ処理を入れる

        // 決選投票へ
        this.gameProgressUtilService.prepareReVoteTables();
        gameStateService.execChangeStateTask(GameState.Voting);
    }

}
