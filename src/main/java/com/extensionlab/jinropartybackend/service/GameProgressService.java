package com.extensionlab.jinropartybackend.service;

import java.util.Optional;

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
        Optional<BaseGameState> gameStateObject = this.getThisGameStateObject(gameState);
        if (gameStateObject.isEmpty()) {
            return;
        }
        this.startStateTask(gameStateObject.get());
    }

    private void startStateTask(BaseGameState gameStateObject) {
        gameStateObject.runInitTask();
        int time = gameStateObject.getCountdownTime();
        if (time > 0) {
            timerService.start(time, () -> {
                gameStateObject.runEndTask();
            });
        }
        gameStateObject.runStartTask();
    }

    public void pauseTimer() {
        this.timerService.pause();
    }

    public void resumeTimer() {
        this.timerService.resume();
    }

    private Optional<BaseGameState> getThisGameStateObject(GameState gameState) {
        // @note 新しく状態が追加されたらここにも追加する
        BaseGameState[] gameStateObjects = {
                statePlayerListDisplay,
                stateRoleAssignment,
                stateDayPhaseStart,
        };
        for (BaseGameState gameStateObject : gameStateObjects) {
            if (gameState.equals(gameStateObject.getThisGameState())) {
                return Optional.of(gameStateObject);
            }
        }
        System.out.println("warning: gameState is other");
        return Optional.empty();
    }

}
