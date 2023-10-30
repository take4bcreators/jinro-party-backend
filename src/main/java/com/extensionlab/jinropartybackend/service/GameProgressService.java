package com.extensionlab.jinropartybackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.component.gamestate.GameStateBase;
import com.extensionlab.jinropartybackend.enums.GameState;

@Service
public class GameProgressService {

    @Autowired
    CountdownTimerService timerService;

    @Autowired
    GameStateUtilService gameStateUtilService;

    public void startStateTask(GameState gameState, GameStateService gameStateService) {
        System.out.println("  ---- startStateTask ---- ");
        Optional<GameStateBase> gameStateObjectWrap = this.gameStateUtilService.getThisGameStateObject(gameState);
        if (gameStateObjectWrap.isEmpty()) {
            return;
        }
        GameStateBase gameStateObject = gameStateObjectWrap.get();
        System.out.println("This Game State: " + gameStateObject.getThisGameState());
        gameStateObject.runInitTask();
        int time = gameStateObject.getCountdownTime();
        System.out.println("Time: " + time);
        if (time > 0) {
            timerService.start(time, () -> {
                gameStateObject.runEndTask(gameStateService);
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

}
