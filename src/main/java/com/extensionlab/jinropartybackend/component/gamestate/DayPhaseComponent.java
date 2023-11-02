package com.extensionlab.jinropartybackend.component.gamestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.GameStateSettings;
import com.extensionlab.jinropartybackend.service.GameStateService;
import com.extensionlab.jinropartybackend.service.MainWebSocketProcessService;
import com.extensionlab.jinropartybackend.service.PlayerInfoService;

@Component
public class DayPhaseComponent extends GameStateComponent {

    @Autowired
    PlayerInfoService playerInfoService;

    @Autowired
    MainWebSocketProcessService mainWebSocketProcessService;

    public DayPhaseComponent() {
        super(new GameStateSettings(
                "ST08",
                GameState.DayPhase,
                0,
                GameState.DayPhaseStart,
                GameState.DayPhaseEnd));
    }

    @Override
    public void runInitTask() {
        int discussionTime = this.getDiscussionTime();
        this.setCountdownTime(discussionTime);
    }

    @Override
    public void runStartTask() {
        this.mainWebSocketProcessService.countdownTimerStart(this.getCountdownTime());
    }

    @Override
    public void runEndTask(GameStateService gameStateService) {
        gameStateService.execChangeStateTask(this.getNexGameState());
    }

    private int getDiscussionTime() {
        // 議論時間は、生存人数マイナス 2 分程度が目安
        // https://jinro-rule.kbn.one/game-master-script
        int oneMinutes = 60000;
        int alivePlayerCount = this.playerInfoService.getAlivePlayerCount();
        int discussionTime = (alivePlayerCount - 2) * oneMinutes;
        if (discussionTime < oneMinutes) {
            discussionTime = oneMinutes;
        }
        return discussionTime;
    }

}
