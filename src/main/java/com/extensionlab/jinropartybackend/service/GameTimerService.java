package com.extensionlab.jinropartybackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extensionlab.jinropartybackend.component.gamestate.GameStateComponent;
import com.extensionlab.jinropartybackend.enums.GameState;

@Service
public class GameTimerService {

    @Autowired
    CountdownTimerService timerService;

    @Autowired
    GameStateRoutingService gameStateRoutingService;

    public void startStateTask(GameState gameState, GameStateService gameStateService) {
        System.out.println("  ---- startStateTask ---- ");
        Optional<GameStateComponent> gameStateCompWrapper = this.gameStateRoutingService
                .getThisGameStateComponent(gameState);
        if (gameStateCompWrapper.isEmpty()) {
            return;
        }
        GameStateComponent gameStateComponent = gameStateCompWrapper.get();
        System.out.println("This Game State: " + gameStateComponent.getThisGameState());
        gameStateComponent.runInitTask();
        int time = gameStateComponent.getCountdownTime();
        System.out.println("Time: " + time);
        if (time > 0) {
            timerService.start(time, () -> {
                gameStateComponent.runEndTask(gameStateService);
            });
        }
        gameStateComponent.runStartTask();
    }

    public void pauseTimer() {
        this.timerService.pause();
    }

    public void resumeTimer() {
        this.timerService.resume();
    }

}
