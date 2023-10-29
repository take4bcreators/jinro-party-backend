package com.extensionlab.jinropartybackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.enums.GameState;
import com.extensionlab.jinropartybackend.model.gamestate.StateDayPhaseStart;
import com.extensionlab.jinropartybackend.model.gamestate.BaseGameState;
import com.extensionlab.jinropartybackend.model.gamestate.StatePlayerListDisplay;
import com.extensionlab.jinropartybackend.model.gamestate.StateRoleAssignment;

@Service
public class GameProgressService {

    @Autowired
    CountdownTimerService timerService;

    @Autowired
    StatePlayerListDisplay statePlayerListDisplay;

    @Autowired
    StateRoleAssignment stateRoleAssignment;

    @Autowired
    StateDayPhaseStart stateDayPhaseStart;

    public void startStateTask(GameState gameState) {
        System.out.println("startStateTask");
        BaseGameState baseGameState = null;
        // @note ゲーム状態分岐場所
        switch (gameState) {
            case PlayerListDisplay:
                baseGameState = statePlayerListDisplay;
                break;
            case RoleAssignment:
                baseGameState = stateRoleAssignment;
                break;
            case DayPhaseStart:
                baseGameState = stateDayPhaseStart;
                break;
            default:
                System.out.println("warning: gameState is other");
                break;
        }
        if (baseGameState == null) {
            return;
        }
        this.startStateTask(baseGameState);
    }

    private void startStateTask(BaseGameState baseGameState) {
        int time = baseGameState.getGameStateTime();
        if (time > 0) {
            timerService.start(time, () -> {
                baseGameState.runEndTask();
            });
        }
        System.out.println("runStartTask");
        baseGameState.runStartTask();
    }

    public void pauseTimer() {
        this.timerService.pause();
    }

    public void resumeTimer() {
        this.timerService.resume();
    }

}
