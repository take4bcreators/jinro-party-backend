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

        // 現在のゲーム状態のオブジェクトを取得
        Optional<GameStateComponent> gameStateCompWrapper = this.gameStateRoutingService
                .getThisGameStateComponent(gameState);
        if (gameStateCompWrapper.isEmpty()) {
            return;
        }
        GameStateComponent gameStateComponent = gameStateCompWrapper.get();
        System.out.println("This Game State: " + gameStateComponent.getThisGameState());

        // カウントダウン開始前に行うタスクを実行
        gameStateComponent.runInitTask();

        // カウントダウン時間を取得してカウントダウン開始
        int time = gameStateComponent.getCountdownTime();
        System.out.println("Time: " + time);
        if (time > 0) {
            timerService.start(gameState, time, () -> {
                gameStateComponent.runEndTask(gameStateService);
            });
        }

        // カウントダウン開始後に行うタスクを実行
        gameStateComponent.runStartTask();
    }

    public void pauseTimer() {
        this.timerService.pause();
    }

    public void resumeTimer() {
        this.timerService.resume();
    }

}
